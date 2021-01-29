package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Website {

    private String website;

    public Website(String website) {
        setWebsite(website);
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        String regex = "(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{2,3}\\.([a-z]+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(website);
        if (matcher.matches()){
            this.website = website;
        } else {
            throw new IllegalArgumentException("O website introduzido é inválido.");
        }
    }

}
