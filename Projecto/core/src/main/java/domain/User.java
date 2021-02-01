package domain;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getEmail(), getRole());
    }
}
