package grupo3.sprint_api.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        new Email("vitorgmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoSemArroba() {
        new Email("vitorgmailcom");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidSemPonto() {
        new Email("vitor@gmailcom");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoSoComUmaLetraAseguirAoPonto() {
        new Email("vitor@gmail.c");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoVazio() {
        new Email("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmailInvalidoCoUk() {
        new Email("vitor@gmail.co.ukapoie");
    }
}