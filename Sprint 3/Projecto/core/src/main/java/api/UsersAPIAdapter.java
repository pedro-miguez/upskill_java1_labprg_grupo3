package api;

import network.*;
import org.json.JSONObject;
import utils.Response;

import java.io.Serializable;

/**
 * Current class is the one responsible to connect and make requests between the app and the to the web service.
 */
public class UsersAPIAdapter implements Serializable {
    private String app_context;
    private String app_key;

    /**
     * Instantiates a new Users api adapter.
     *
     * @param app_key as app key
     */
    public UsersAPIAdapter(String app_key) {
        this.app_key = app_key;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
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

    /**
     * Login boolean.
     *
     * @param user_id  as user id
     * @param password as password
     * @return the boolean
     */
    public boolean login(String user_id, String password) {
        String url = "/login?app_context=" + getContext() + "&user_id=" + user_id + "&password=" + password;
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

    /**
     * Logout boolean.
     *
     * @return the boolean
     */
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

    /**
     * Register user boolean.
     *
     * @param username as username
     * @param email    as email
     * @param password as password
     * @return the boolean
     */
    public boolean registerUser(String username, String email, String password) {
        String url = "/registerUser?app_context=" + getContext() + "&username=" + username + "&email=" + email
                + "&password=" + password;
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

    /**
     * Register user with roles boolean.
     *
     * @param username  as username
     * @param email     as email
     * @param password  as password
     * @param rolenames as rolenames
     * @return the boolean
     */
    public boolean registerUserWithRoles(String username, String email, String password, String rolenames) {
        String url = "/registerUserWithRoles?app_context=" + getContext() + "&username=" + username + "&email=" + email
                + "&password=" + password + "&rolenames=" + rolenames;
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

    /**
     * Gets session.
     *
     * @return the session
     */
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
