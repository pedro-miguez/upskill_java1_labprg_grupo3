package grupo3.sprint_api.dto;

/**
 * This represents a "clone" of the model (domain) class 'Email'.
 * (Because model classes cannot be exposed to outside)
 * This will be used for serialization and deserialization of the class Email.
 */
public class EmailDTO {

    private String email;

    /**
     * Represents an empty constructor.
     */
    public EmailDTO() {};

    /**
     * Sets an email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets an email.
     *
     * @return email
     */
    public String getEmail() {
        return this.email;
    }
}
