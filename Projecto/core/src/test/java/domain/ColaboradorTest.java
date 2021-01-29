package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColaboradorTest {

    @Test
    public void testCreateColaboradorValido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org);

        String expected = "nome";
        String result = colaborador.getNome();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorNomeInvalido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        new Colaborador("", new Telefone(999999999), new Email("colab@org.com"), org);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorTelefoneInvalido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        new Colaborador("nome", new Telefone(1999999999), new Email("colab@org.com"), org);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorEmailInvalido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        new Colaborador("nome", new Telefone(999999999), new Email("colaborg.com"), org);
    }

}