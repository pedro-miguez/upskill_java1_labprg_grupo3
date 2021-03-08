package grupo3.sprint_api.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testCreateUserValido() {

        User user = new User("Jay", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", "Abrir covas"));

        String expected = "abc123";
        String result = user.getPassword();

        assertEquals(expected, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido1() {
        User user = new User("", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", "Abrir covas"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido2() {
        User user = new User("Jay", "", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", "Abrir covas"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido3() {
        User user = new User("Jay", "abc123", new Email (""),
                new Role ("Coveiro", "Abrir covas"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido4() {
        User user = new User("Jay", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("", "Abrir covas"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserInvalido5() {
        User user = new User("Jay", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", ""));
    }
}