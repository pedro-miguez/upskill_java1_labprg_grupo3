package domain;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class responsible for testing the user.
 * 
 * @author Grupo 3
 */
public class UserTest {

    //Tests a valid user.
    @Test
    public void testCreateUserValido() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        String expected = "qwerty";
        String result = user.getPassword();

        assertEquals(expected, result);
    }

    //Tests a invalid user.
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido1() {
        User user = new User("", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);
    }

    //Tests a invalid user.
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido2() {
        User user = new User("testmanpedro", "", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);
    }

    //Tests a invalid user.
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido3() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedrogmail.com"), Role.ADMINISTRATIVO);
    }
}