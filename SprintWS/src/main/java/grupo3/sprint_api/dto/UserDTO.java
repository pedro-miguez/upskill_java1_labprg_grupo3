/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.sprint_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This represents a "clone" of the model (domain) class 'User'.
 * (Because model classes cannot be exposed to outside)
 * This will be used for serialization and deserialization of the class User
 * using the Jackson library.
 *
 * @author Grupo 3
 */
public class UserDTO {

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private RoleDTO role;

    /**
     * Represents an empty constructor.
     */
    public UserDTO() {
    }

    /**
     * Gets the Username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the Username.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the role.
     *
     * @return role
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * Sets the role.
     * 
     * @param role
     */
    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
