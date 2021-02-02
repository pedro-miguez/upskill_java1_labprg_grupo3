package api;

import network.*;
import org.json.JSONObject;
import utils.Response;
import xml.XmlHandler;

public class UsersAPIAdapter {
    private String app_context;
    private String app_key;

    public UsersAPIAdapter(String app_key) {
        this.app_key = app_key;
    }

    public String getContext() {
        if (app_context == null || app_context.equals("")) {
            String url = "/context?app_key=" + app_key;
            HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
            HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
            switch (httpResponse.getStatus()) {
                case HttpStatusCode.OK:
                    break;
                case HttpStatusCode.Conflict:

                    break;
            }
            JSONObject bodyJSON = new JSONObject(httpResponse.getBody().replaceAll( "\\[|\\]", ""));
            app_context = bodyJSON.getString("app_context");
        }
        return app_context;
    }

    public boolean login(String user_id, String password) {
        String url = "/login?app_context=" + getContext() + "?username=" + user_id + "?password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public boolean logout() {
        String url = "/logout?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public boolean registerUser(String username, String email, String password) {
        String url = "/registerUser?app_context=" + getContext() + "?username=" + username + "?email=" + email
                + "?password=" + password;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.Created:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public boolean registerUserWithRoles(String username, String email, String password, String rolenames) {
        String url = "/registerUserWithRole?app_context=" + getContext() + "?username=" + username + "?email=" + email
                + "?password=" + password + "?role=" + rolenames;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.Created:
                return true;
            case HttpStatusCode.Conflict:
                return false;
        }
        return false;
    }

    public String getSession() {
        String url = "/session?app_context=" + getContext();
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.OK:
                break;
            case HttpStatusCode.Conflict:
                break;
        }
        return httpResponse.getBody().replaceAll( "\\[|\\]", "");
    }
}
