package grupo3.sprint_api.domain;

import java.io.Serializable;

/**
 * Current class enables to create a new User object which hosts 
 * some of the critical functions available in the app, such as getting or
 * setting parameters like username, password, email and the role.
 */
public class User implements Serializable {
    private String username;
    private String password;
    private Email email;
    private Role role;

    /**
     * User builder with the following parameters:
     *
     * @param username
     * @param password
     * @param email
     * @param role 
     */
    public User(String username, String password, Email email, Role role) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
    }

    /**
     * Instantiates a new User with set parameters.
     *
     * @param username
     * @param password
     * @param email
     */
    public User(String username, String password, Email email) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }

    /**
     * Method for obtaining a username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Validates a username.
     *
     * @param username 
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for obtaining a password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Validates a password.

     * @param password 
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for obtaining an email.

     * @return email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Validates an email.
     *
     * @param email 
     */
    private void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Method for obtaining a role.
     *
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Validates a role.
     *
     * @param role 
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Method to check if two objects (users) are the same.
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) &&
                getPassword().equals(user.getPassword()) &&
                getEmail().equals(user.getEmail()) &&
                getRole() == user.getRole();
    }

    
    /**
     * Returns a string representation with very concise but precise information 
     * about the object and its attributes.
     *
     * @return a string representation of the object (user).
     */
    @Override
    public String toString() {
        return "User/Utilizador{" +
                "username='" + username + '\'' +
                ", email=" + email +
                ", role=" + role +
                '}';
    }
}
