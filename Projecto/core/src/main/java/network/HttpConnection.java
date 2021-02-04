package network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Current class has the tools to establish a connection and set up a request with the webservice.
 */
public class HttpConnection {

    private static String readBody(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String read = br.readLine();
            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void writeBody(OutputStream writer, String body) {
        try {
            byte[] dataBytes = body.getBytes("UTF-8");
            writer.write(dataBytes);
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets up the connection and the requests to the webservice and returns the response.
     *
     * @param httpRequest the webservice request
     * @return the webservice response
     */
    public static HttpResponse makeRequest(HttpRequest httpRequest) {
        HttpURLConnection httpConn = null;
        int resCode = -1;
        String body = "";
        try {
            URL url = new URL(httpRequest.getUrl());
            URLConnection urlConn = url.openConnection();
            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            httpConn = (HttpURLConnection) urlConn;
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestProperty("Content-Type", "application/xml");
            switch (httpRequest.getType()) {
                case GET:
                    httpConn.setRequestMethod("GET");
                    httpConn.setDoInput(true);
                    break;
                case POST:
                    httpConn.setRequestMethod("POST");
                    httpConn.setDoOutput(true);
                    //writeBody(httpConn.getOutputStream(), httpRequest.getBody());
                    break;
                case PUT:
                    httpConn.setRequestMethod("PUT");
                    httpConn.setDoOutput(true);
                    //writeBody(httpConn.getOutputStream(), httpRequest.getBody());
                    break;
                case DELETE:
                    httpConn.setRequestMethod("DELETE");
                    break;
            }
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            body = readBody(httpConn.getInputStream());
        } catch (MalformedURLException e) {
            //this is for normalize the error events according to the way is handled by the WS
            resCode = HttpStatusCode.Conflict;
        } catch (IOException e) {
            //this is for normalize the error events according to the way is handled by the WS
            resCode = HttpStatusCode.Conflict;
        }
        HttpResponse httpResponse = new HttpResponse(resCode, body);
        return httpResponse;
    }
}
