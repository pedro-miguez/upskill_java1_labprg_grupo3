package api;

import dto.ErroDTO;
import network.*;
import utils.Response;
import xml.XmlHandler;

public class UsersAPIAdapter {

    private String contextKey;

    public UsersAPIAdapter(String appKey) {
        this.contextKey = getContext(appKey);
    }

    private static String getContext(String appKey) {
        String url = "/context/app_key=" + appKey;
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        return url;//temporario
    }

    public static Response registerUserWithRoles(String url) {
        Response response = null;
        //final String body = XmlHandler.serializeUserDTO2XML(userDTO);
        HttpRequest httpRequest = new HttpRequest(HttpRequestType.POST, url);
        HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
        switch (httpResponse.getStatus()) {
            case HttpStatusCode.Created:
                response = new Response(HttpStatusCode.Created, null);
                break;
            case HttpStatusCode.Conflict:
                ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                break;
        }
        return response;
    }
}
