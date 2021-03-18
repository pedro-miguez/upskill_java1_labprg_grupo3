package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class responsible for testing the email.
 * 
 * @author Grupo 3
 */
public class EmailTest {

    //Tests a valid email.
    @Test
    public void testCreateEmailValido() {
        Email email = new Email("vitor@gmail.com");

        String expected = "vitor@gmail.com";
        String result = email.getEmail();

        assertEquals(expected, result);
    }

    //Tests a invalid email.
    @Test(expected = IllegalArgumentException.class)
    public void createEmailInvalido() {
        new Email("vitorgmail.com");
    }

    //Tests a invalid email without @.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoSemArroba() {
        new Email("vitorgmailcom");
    }

    //Tests a invalid email without .
    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidSemPonto() {
        new Email("vitor@gmailcom");
    }

    //Tests a invalid email with only one letter after the point.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoSoComUmaLetraAseguirAoPonto() {
        new Email("vitor@gmail.c");
    }

    //Tests a invalid empty email.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoVazio() {
        new Email("");
    }
    
    //Tests a invalid email with co.uk...
    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoCoUk() {
        new Email("vitor@gmail.co.ukapoie");
    }
}

