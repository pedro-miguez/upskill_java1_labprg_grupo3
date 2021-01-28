package domain;

//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.AddressException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//
///**
// * A class to provide stronger validation of email addresses. devdaily.com, no
// * rights reserved. :)
// *
// */
//public class Email {
//
//    public Email(Email email) {
//    }
//    
//    public static boolean isValidEmailAddress(String email) {
//        // a null string is invalid
//        if (email == null) {
//            return false;
//        }
//
//        // a string without a "@" is an invalid email address
//        if (email.indexOf("@") < 0) {
//            return false;
//        }
//
//        // a string without a "."  is an invalid email address
//        if (email.indexOf(".") < 0) {
//            return false;
//        }
//        
//
//        if (lastEmailFieldTwoCharsOrMore(email) == false) {
//            return false;
//        }
//
//        try {
//            
//            Email email = new Email(email);
//            return true;
//        } catch (EmailException ae) {
//            // log exception
//            return false;
//        }
//    }
//
//    /**
//     * Returns true if the last email field (i.e., the country code, or
//     * something like .com, .biz, .cc, etc.) is two chars or more in length,
//     * which it really must be to be legal.
//     */
//    private static boolean lastEmailFieldTwoCharsOrMore(String email) {
//        if (email == null) {
//            return false;
//        }
//        StringTokenizer st = new StringTokenizer(email, ".");
//        String lastToken = null;
//        while (st.hasMoreTokens()) {
//            lastToken = st.nextToken();
//        }
//
//        if (lastToken.length() >= 2) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//}

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
