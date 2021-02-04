package persistence;

import static org.junit.Assert.*;

import domain.*;
import exceptions.EmailNaoAssociadoException;
import exceptions.NomeNaoAssociadoException;
import org.junit.Test;

public class RepositorioUtilizadorTest {

    @Test
    public void testGetInstance() {
        RepositorioUtilizador result = RepositorioUtilizador.getInstance();

        RepositorioUtilizador expected = RepositorioUtilizador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testAddUtilizadorValido() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        int expected = RepositorioUtilizador.getInstance().listarUtilizadores().size() + 1;

        RepositorioUtilizador.getInstance().addUtilizador(user);

        int result = RepositorioUtilizador.getInstance().listarUtilizadores().size() + 1;

        assertEquals(expected, result);
    }

    @Test
    public void testAddUtilizadorInvalido() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        RepositorioUtilizador.getInstance().addUtilizador(user);

        User user2 = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        assertFalse(RepositorioUtilizador.getInstance().addUtilizador(user2));
    }

    @Test
    public void testGetUtilizadorByEmailValido() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        RepositorioUtilizador.getInstance().addUtilizador(user);

        assertEquals(user, RepositorioUtilizador.getInstance().getUserByEmail(new Email("testmanpedro@gmail.com")));
    }

    @Test (expected = EmailNaoAssociadoException.class)
    public void testGetUtilizadorByEmailInvalido() {
        RepositorioUtilizador.getInstance().getUserByEmail(new Email("testmanpedro1@gmail.com"));
    }

    @Test
    public void testGetUtilizadorByNameValido() {
        User user = new User("testmanpedro", "qwerty", new Email("testmanpedro@gmail.com"), Role.ADMINISTRATIVO);

        RepositorioUtilizador.getInstance().addUtilizador(user);

        assertEquals(user, RepositorioUtilizador.getInstance().getUserByUsername("testmanpedro"));
    }

    @Test (expected = NomeNaoAssociadoException.class)
    public void testGetUtilizadorByNameInvalido() {
        RepositorioUtilizador.getInstance().getUserByUsername("123");
    }
}