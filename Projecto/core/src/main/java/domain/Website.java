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
        if (website.matches("^(http:\\/\\/|https:\\/\\/)?(www).?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")){
            this.website = website;
        } else {
            throw new IllegalArgumentException("O website introduzido é inválido.");
        }
    }
}
