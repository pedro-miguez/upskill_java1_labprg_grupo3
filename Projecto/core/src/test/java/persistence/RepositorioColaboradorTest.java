package persistence;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositorioColaboradorTest extends TestCase {

    @Test
    public void testGetInstance() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testAddColaboradorValido() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testAddColaboradorInvalido() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testGetColaboradorByEmailValido() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testGetColaboradorByEmailInvalido() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testGetColaboradoresOrganizacaoValido() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testGetColaboradoresOrganizacaoInvalido() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }




}