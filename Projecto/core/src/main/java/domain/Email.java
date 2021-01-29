package domain;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Email {

    private String email;

    public Email(String email) {
        setEmail(email);
    }

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

}
