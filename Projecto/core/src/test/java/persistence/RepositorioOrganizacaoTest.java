package persistence;
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
        //Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
          //      new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        int expected = RepositorioOrganizacao.getInstance().listarOrganizacoes().size() + 1;

        //RepositorioOrganizacao.getInstance().addOrganizacao(org);

        int result = RepositorioOrganizacao.getInstance().listarOrganizacoes().size();

        assertEquals(expected, result);
    }

    @Test
    public void testAddOrganizacaoInvalida() {
        //faz sentido?
    }

    @Test
    public void testAddGestorValido() {
        //Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                //new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), Funcao.GESTOR);



    }

    @Test
    public void testAddGestorInvalido() {

    }

    @Test
    public void testgetOrganizacaoByGestorValido() {

    }

    @Test
    public void testgetOrganizacaoByGestorInvalido() {

    }

    @Test
    public void testlistarOrganizacoesValido() {

    }

    @Test
    public void testlistarOrganizacoesInvalido() {

    }



    @Test
    public void testRegistaGestorComoUtilizadorValido() {

    }

    @Test
    public void testRegistaGestorComoUtilizadorInvalido() {

    }
}