package grupo3.sprint_api.dto;

public class LoginDTO {

    private String username;

    private String password;

    /**
     * Represents an empty constructor.
     */
    public LoginDTO(){};

    /**
     * Gets an Username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets an Username.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets an password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets an password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
