package grupo3.sprint_api.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

    @Test
    public void testCreateSessionValido() {

        Context context = new Context("IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06", true);

        User user = new User("Jay", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", "Abrir covas"));

        Session session = new Session(user, context, new Data(2020,05,21));

        String expected = "IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06";
        String result = context.getContext();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSessionInvalido1() {
        Context context = new Context("", true);

        User user = new User("Jay", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", "Abrir covas"));

        Session session = new Session(user, context, new Data(2020,05,21));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSessionInvalido2() {
        Context context = new Context("IBD0DEHBDID62EB1EAZBEoA95E3cB5BD5135d01F0FqE6eDDoD4yDEX05RFEF19q9BY04KBE03A919hAFM06", true);

        User user = new User("Jay", "abc123", new Email ("jaythebest@gmail.com"),
                new Role ("Coveiro", "Abrir covas"));

        Session session = new Session(user, context, new Data(2024,05,21));
    }
}