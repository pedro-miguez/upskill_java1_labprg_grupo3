package utils;

import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMail {

    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
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

       Message message = prepareMessage(session, myAccountEmail, recepient);

       Transport.send(message);
       System.out.println("Message sent succesfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Password for T4J");
            message.setText("Hey there, \n Here is your password to be able to log in in to T4J: ");
        } catch (Exception ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
