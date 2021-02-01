package domain;

public class User {
    private String username;
    private String password;
    private Email email;
    private Role role;

    public User (String username, String password, Email email, Role role) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    private void setRole(Role role) {
        this.role = role;
    }
}
