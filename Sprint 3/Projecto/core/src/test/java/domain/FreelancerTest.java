package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FreelancerTest {

    @Test
    public void testCreateFreelancerValido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());

        String expected = "Bob Marley";
        String result = freelancer.getNome();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerNomeInvalido() {

        Freelancer freelancer = new Freelancer("", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerTelefoneInvalido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(1999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerEmailInvalido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colaborg.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerNifInvalido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852), new ArrayList<>(), new ArrayList<>());
    }
}