package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the freelancer.
 * 
 * @author Grupo 3
 */
public class FreelancerTest {

    //Tests a valid freelancer.
    @Test
    public void testCreateFreelancerValido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());

        String expected = "Bob Marley";
        String result = freelancer.getNome();

        assertEquals(expected, result);
    }

    //Tests a invalid freelancer name.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerNomeInvalido() {

        Freelancer freelancer = new Freelancer("", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());
    }

    //Tests a invalid freelancer phone number.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerTelefoneInvalido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(1999999999), new Email("colab@org.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());
    }

    //Tests a invalid freelancer email.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerEmailInvalido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colaborg.com"),
                new NIF(214852581), new ArrayList<>(), new ArrayList<>());
    }

    //Tests a invalid freelancer nif.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateFreelancerNifInvalido() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852), new ArrayList<>(), new ArrayList<>());
    }
}