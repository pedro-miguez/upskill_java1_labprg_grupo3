package persistence;

import exceptions.EmailNaoAssociadoException;
import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositorioColaboradorTest {

    @Test
    public void testGetInstance() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testAddColaboradorValido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Colaborador gestor = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        int expected = RepositorioColaborador.getInstance().listarColaboradores().size() + 1;

        RepositorioColaborador.getInstance().addColaborador(gestor);

        int result = RepositorioColaborador.getInstance().listarColaboradores().size();

        assertEquals(expected, result);
    }

    @Test
    public void testAddColaboradorInvalido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Colaborador gestor = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        RepositorioColaborador.getInstance().addColaborador(gestor);

        Colaborador gestor2 = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        assertFalse(RepositorioColaborador.getInstance().addColaborador(gestor2));
    }

    @Test
    public void testGetColaboradorByEmailValido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Colaborador gestor = new Colaborador("nome1", new Telefone(999999991), new Email("colab1@org.com"), org, Funcao.GESTOR);

        RepositorioColaborador.getInstance().addColaborador(gestor);

        assertEquals(gestor, RepositorioColaborador.getInstance().getColaboradorByEmail(new Email("colab1@org.com")));
    }

    @Test (expected = EmailNaoAssociadoException.class)
    public void testGetColaboradorByEmailInvalido() {
        RepositorioColaborador.getInstance().getColaboradorByEmail(new Email("colabs@org.com"));
    }

    @Test
    public void testGetColaboradoresOrganizacaoValido() {
        Organizacao org = new Organizacao("org2", new NIF(111111111), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Colaborador gestor = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        RepositorioColaborador.getInstance().addColaborador(gestor);

        int expected = 1;
        int result = RepositorioColaborador.getInstance().getColaboradoresOrganizacao(org).size();

        assertEquals(expected, result);
    }
}