package persistence;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;
import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositorioOrganizacaoTest {


    @Test
    public void testGetInstance() {
        RepositorioOrganizacao result = RepositorioOrganizacao.getInstance();

        RepositorioOrganizacao expected = RepositorioOrganizacao.getInstance();

        assertEquals(result, expected);
    }


    @Test
    public void testAddOrganizacaoValida() {
        Organizacao org = new Organizacao("organ123", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        int expected = RepositorioOrganizacao.getInstance().listarOrganizacoes().size() + 1;

        RepositorioOrganizacao.getInstance().addOrganizacao(org);

        int result = RepositorioOrganizacao.getInstance().listarOrganizacoes().size();

        assertEquals(expected, result);
    }

    @Test
    public void testAddOrganizacaoDuplicada() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Organizacao org2 = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        RepositorioOrganizacao.getInstance().addOrganizacao(org);

        assertFalse(RepositorioOrganizacao.getInstance().addOrganizacao(org2));
    }

    @Test
    public void testAddGestorValido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador gestor = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        RepositorioOrganizacao.getInstance().addGestor(gestor, org);

        assertEquals(org.getGestor(), gestor);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddGestorInvalido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org);

        RepositorioOrganizacao.getInstance().addGestor(colaborador, org);
    }

    @Test
    public void testgetOrganizacaoByGestorValido() {
        Organizacao org = new Organizacao("org123", new NIF(111222333), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador gestor = new Colaborador("gestor", new Telefone(112223223), new Email("colab123@org.com"), org, Funcao.GESTOR);

        RepositorioOrganizacao.getInstance().addOrganizacao(org);

        RepositorioOrganizacao.getInstance().addGestor(gestor, org);

        Organizacao org2 = RepositorioOrganizacao.getInstance().getOrganizacaoByGestor(gestor);

        assertEquals(org, org2 );

    }

    @Test (expected = GestorNaoRelacionadoANenhumaOrgException.class)
    public void testGetOrganizacaoByGestorInvalido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador gestor = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        RepositorioOrganizacao.getInstance().addOrganizacao(org);
        RepositorioOrganizacao.getInstance().addGestor(gestor, org);

        Colaborador gestor2 = new Colaborador("nome2", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        RepositorioOrganizacao.getInstance().getOrganizacaoByGestor(gestor2);

    }

}