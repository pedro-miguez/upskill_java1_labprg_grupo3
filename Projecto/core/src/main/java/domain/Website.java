package domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Website implements Serializable {

    private String website;

    public Website(String website) {
        setWebsite(website);
    }

    public String getWebsite() {
        return website;
    }

    private void setWebsite(String website) {
        String regex = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z]+.[a-zA-Z]*.[a-z0-9]+)\\.(([a-z]){2,3})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(website);
        if (matcher.matches()){
            this.website = website;
        } else {
            throw new IllegalArgumentException("O website introduzido é inválido.");
        }
    }

}
