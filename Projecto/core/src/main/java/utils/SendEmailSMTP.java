
package utils;

import application.AuthenticationController;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmailSMTP {

    private AuthenticationController authenticationController = new AuthenticationController();
    // for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "upskilljavaturma1grupo3@gmail.com";
    private static final String PASSWORD = "Upskilljavaturma1grupo3*";


    private static final String EMAIL_FROM = "upskilljavaturma1grupo3@gmail.com";
    private static final String EMAIL_TO = "upskilljavaturma1grupo3@gmail.com";

    private static final String EMAIL_TO_CC = "";
    
    private static final String EMAIL_SUBJECT = "Hello, this is your password to access T4J";

    public static void SendEmail(String username, String password){

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25

        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);


        try {

            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            // to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            // cc

            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));


            // subject
            msg.setSubject(EMAIL_SUBJECT);

            // content
            msg.setText("Hi, there. You have recently been registered at our platform. This is your username: " + username + " and your password: " + password);

            msg.setSentDate(new Date());

            // Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}


