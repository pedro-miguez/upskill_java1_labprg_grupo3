/*
package utils;

import javax.activation.*;
import javax.jms.Session;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {

    public static void sendMail(String recipient) {

        Properties properties = new Properties();

        properties.put("mail.stmp.auth", "true");
        properties.put("mail.stmp.starttls.enable", "true");
        properties.put("mail.stmp.host", "smtp.gmail.com");
        properties.put("mail.stmp.port", "587");

        String myAccountEmail = "upskilljavaturma1grupo3.gmail.com";
        String password = "Upskilljavaturma1grupo3*";

        // Get the default Session object.
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

       Message message = prepareMessage();
    }

    private static Mesasge prepareMessage(Session session){
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message
    }
}*/
