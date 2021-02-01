package api;

import dto.ErroDTO;
import network.HttpConnection;
import network.HttpRequest;
import network.HttpRequestType;
import network.HttpResponse;
import network.HttpStatusCode;
import utils.Response;
import xml.XmlHandler;

public class UsersAPI {
    UsersAPIAdapter uapia;

    public UsersAPI() {
        String app_key = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";
        uapia = new UsersAPIAdapter(app_key);
    }

    public boolean login(String user_id, String password) {
        return uapia.login(user_id, password);
    }

    public boolean logout() {
        return uapia.logout();
    }

    public String getContext() {
        return uapia.getContext();
    }

    public String getEmail() {
        //getSession devolve algo como:
//        [{
//   "username": "anc",
//   "email": "anc@isep.ipp.pt",
//   "rolename": null,
//   "logindate": "2021-01-23T18:33:27.000Z"
//}]
        String session = uapia.getSession(); //Obtemos uma Json string com informação sobre a sessão.

        return "";
    }

    public boolean registerUserWithRoles(String username, String email, String password, String rolenames) {
        return uapia.registerUserWithRoles(username, email, password, rolenames);
    }
}