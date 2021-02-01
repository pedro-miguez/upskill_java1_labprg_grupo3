package domain;

public class User {
    private String username;
    private String password;
    private Email email;

    public User (String username, String password, Email email) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
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
}
