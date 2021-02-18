
package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColaboradorTest {

    @Test
    public void testCreateColaboradorValido() {
        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), "pedreiro");

        String expected = "nome";
        String result = colaborador.getNome();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorNomeInvalido() {
        new Colaborador("", new Telefone(999999999), new Email("colab@org.com"), "pedreiro" );

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorTelefoneInvalido() {
        new Colaborador("nome", new Telefone(1999999999), new Email("colab@org.com"), "pedreiro");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorEmailInvalido() {
        new Colaborador("nome", new Telefone(999999999), new Email("colaborg.com"), "pedreiro");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorFuncaoInvalida() {
        new Colaborador("nome", new Telefone(999999999), new Email("colaborg.com"), " ");
    }

}
