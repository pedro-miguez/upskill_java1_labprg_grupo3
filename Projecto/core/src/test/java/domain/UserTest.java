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


}