package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Current class implements the tools to create new websites (as Website). 
 * It has implementations to get a string representation through the toString() 
 * method of the object.
 */
public class Website implements Serializable {

    private String website;

    /**
     * Instantiates a new Website with set parameter.
     *
     * @param website the website
     */
    public Website(String website) {
        setWebsite(website);
    }


    /**
     * Gets the website.
     *
     * @return the website.
     */
    public String getWebsite() {
        return website;
    }

    private void setWebsite(String website) {
        String regex = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:\\/?#\\[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(website);
        if (matcher.matches()){
            this.website = website;
        } else {
            throw new IllegalArgumentException("O website introduzido é inválido.");
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("%s", website);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     *
     * @param   o   the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Website)) return false;

        Website website1 = (Website) o;

        return getWebsite().equals(website1.getWebsite());
    }

}
