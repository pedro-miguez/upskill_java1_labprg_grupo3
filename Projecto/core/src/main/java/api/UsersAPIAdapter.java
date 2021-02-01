package api;

import dto.ErroDTO;
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
            Response response = null;
            HttpRequest httpRequest = new HttpRequest(HttpRequestType.GET, url);
            HttpResponse httpResponse = HttpConnection.makeRequest(httpRequest);
            switch (httpResponse.getStatus()) {
                case HttpStatusCode.OK:
                    response = new Response(HttpStatusCode.OK, httpResponse.getBody());
                    break;
                case HttpStatusCode.Conflict:
                    ErroDTO erroDTO = XmlHandler.deSerializeXML2ErroDTO(httpResponse.getBody());
                    response = new Response(HttpStatusCode.Conflict, erroDTO.getMensagemErro());
                    break;
            }
            JSONObject bodyJSON = new JSONObject(httpResponse.getBody().replaceAll( "\\[|\\]", ""));
            app_context = bodyJSON.getString("app_context");

            //app_context = "{7E19F342-A903-4C3B-806A-CF771120B9D0}";
        }
        return app_context;
    }

    public boolean login(String user_id, String password) {
        return true;
    }

    public boolean logout() {
        return true;
    }

    public boolean registerUser(String username, String email, String password) {
        return true;
    }

    public boolean registerUserWithRoles(String username, String email, String password, String rolenames) {
        return true;
    }

    public String getSession() {
        return "[{\"username\": \"anc\",\"email\": \"anc@isep.ipp.pt\",\"logindate\": \"2021-01-23T18:33:27.000Z\"}]";
    }
}
