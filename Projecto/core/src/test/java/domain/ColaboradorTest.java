
package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the collaborator.
 * 
 * @author Grupo 3
 */
public class ColaboradorTest {

    //Tests a valid collaborator.
    @Test
    public void testCreateColaboradorValido() {
        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), "pedreiro");

        String expected = "nome";
        String result = colaborador.getNome();

        assertEquals(expected, result);
    }

    //Tests a invalid collaborator name.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorNomeInvalido() {
        new Colaborador("", new Telefone(999999999), new Email("colab@org.com"), "pedreiro" );

    }

    //Tests a invalid collaborator phone number.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorTelefoneInvalido() {
        new Colaborador("nome", new Telefone(1999999999), new Email("colab@org.com"), "pedreiro");
    }

    //Tests a invalid collaborator email.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorEmailInvalido() {
        new Colaborador("nome", new Telefone(999999999), new Email("colaborg.com"), "pedreiro");
    }

    //Tests a invalid collaborator function.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateColaboradorFuncaoInvalida() {
        new Colaborador("nome", new Telefone(999999999), new Email("colaborg.com"), " ");
    }

}
