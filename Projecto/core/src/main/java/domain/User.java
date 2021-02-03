package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * The type User.
 */
public class User implements Serializable {
    private String username;
    private String password;
    private Email email;
    private Role role;

    /**
     * Construtor de um utilizador com os seguintes parâmetros:
     * @param username
     * @param password
     * @param email
     * @param role 
     */
    public User (String username, String password, Email email, Role role) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
    }

    /**
     * Método para obtenção de um username.
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Valida um username.
     * @param username 
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Método para obtenção de uma password.
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Valida uma password.
     * @param password 
     */
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método para obtenção de um email.
     * @return 
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Valida um email.
     * @param email 
     */
    private void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Método para obtenção de um role.
     * @return 
     */
    public Role getRole() {
        return role;
    }

    /**
     * Valida um role.
     * @param role 
     */
    private void setRole(Role role) {
        this.role = role;
    }

    /**
     * Método para verificar se dois objetos (utilizadores) são iguais.
     * @param o
     * @return 
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

}
