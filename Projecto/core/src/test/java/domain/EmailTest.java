package domain;

import org.junit.Test;
import junit.framework.*;
import domain.Email;
import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void testCreateEmailValido() {

        Email email = new Email("vitor@gmail.com");

        String expected = "vitor@gmail.com";
        String result = email.getEmail();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createEmailInvalido() {

        Email email = new Email("vitorgmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoSemArroba() {

        Email email = new Email("vitorgmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidSemPonto() {

        Email email = new Email("vitor@gmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoSoComUmaLetraAseguirAoPonto() {

        Email email = new Email("vitor@gmail.c");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoVazio() {

        Email email = new Email("");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoCoUk() {

        Email email = new Email("vitor@gmail.co.ukapoie");
    }
}
