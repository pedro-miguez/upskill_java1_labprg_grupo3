package domain;


import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Current class implements the tools to create new emails (as Email) following certain rules how an email should be.
 * It has implementations to get a string representation through the toString() method of the object.
 *
 */
public class Email implements Serializable {

    private String email;

    /**
     * Instantiates a new Email with set parameter.
     *
     * @param email the email
     */
    public Email(String email) {
        setEmail(email);
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            
        if (matcher.matches()){
           this.email = email;
        }else{
            throw new IllegalArgumentException("Email invalido.");
        }
    }

    /**
     * Compares this object with specified object for order.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(getEmail(), email1.getEmail());
    }

    /**
     * Returns a string representation of the object.
     *
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return email;
    }
}
