package application;

import api.UsersAPIAdapter;
import network.HttpConnection;
import network.HttpRequest;
import network.HttpRequestType;
import network.HttpResponse;
import network.HttpStatusCode;
import org.json.JSONObject;
import utils.Response;

import java.io.Serializable;

public class UsersAPI implements Serializable {
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
        String session = uapia.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getString("email");
    }

    public boolean registerUserWithRoles(String username, String email, String password, String rolenames) {
        return uapia.registerUserWithRoles(username, email, password, rolenames);
    }

    public boolean registerUser(String username, String email, String password) {
        return uapia.registerUser(username, email, password);
    }
}