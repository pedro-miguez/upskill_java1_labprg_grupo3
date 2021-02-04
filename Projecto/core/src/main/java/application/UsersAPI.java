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

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for creating interfaces
 * between the model and webservice (usersAPIAdapter).
 */
public class UsersAPI implements Serializable {

    private UsersAPIAdapter uapia;

    /**
     * Instantiates a new User api.
     */
    public UsersAPI() {
        String app_key = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";
        uapia = new UsersAPIAdapter(app_key);
    }

    /**
     * Login boolean.
     *
     * @param user_id  as user id
     * @param password as password
     * @return the boolean
     */
    public boolean login(String user_id, String password) {
        return uapia.login(user_id, password);
    }

    /**
     * Logout boolean.
     *
     * @return the boolean
     */
    public boolean logout() {
        return uapia.logout();
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public String getContext() {
        return uapia.getContext();
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        String session = uapia.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getString("email");
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        String session = uapia.getSession();
        JSONObject bodyJSON = new JSONObject(session);
        return bodyJSON.getString("rolenames");
    }

    /**
     * Register user with roles boolean.
     *
     * @param username  the username
     * @param email     the email
     * @param password  the password
     * @param rolenames the rolenames
     * @return the boolean
     */
    public boolean registerUserWithRoles(String username, String email, String password, String rolenames) {
        return uapia.registerUserWithRoles(username, email, password, rolenames);
    }

    /**
     * Register user boolean.
     *
     * @param username the username
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public boolean registerUser(String username, String email, String password) {
        return uapia.registerUser(username, email, password);
    }
}