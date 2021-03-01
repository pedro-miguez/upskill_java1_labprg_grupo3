package domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

    @Test
    public void testCreateUserValido() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        String expected = "qwerty";
        String result = user.getPassword();

        assertEquals(expected, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido1() {
        User user = new User("", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido2() {
        User user = new User("testmanpedro", "", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido3() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedrogmail.com"), Role.ADMINISTRATIVO);
    }
}