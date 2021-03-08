package grupo3.sprint_api.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    public void testCreateRoleValido() {

        Role role = new Role("Gestor", "Gerir equipa");


        String expected = "Gestor";
        String result = role.getRolename();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRoleInvalido1() {
        Role role = new Role("", "Gerir equipa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRoleInvalido2() {
        Role role = new Role("Gestor", "");
    }
}