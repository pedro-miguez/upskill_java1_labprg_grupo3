package grupo3.sprint_api.dto;

/**
 * This represents a "clone" of the model (domain) class 'Session'.
 * (Because model classes cannot be exposed to outside)
 * This will be used for serialization and deserialization of the class Session.
 */
public class SessionDTO {

    private UserDTO user;

    private String logindate;

    /**
     * Represents an empty constructor.
     */
    public SessionDTO(){};

    /**
     * Gets an User.
     *
     * @return user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets an User.
     *
     * @param user
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * Gets the date of the login.
     *
     * @return logindate
     */
    public String getLogindate() {
        return logindate;
    }

    /**
     * Sets the date of the login.
     *
     * @param logindate
     */
    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }
}
