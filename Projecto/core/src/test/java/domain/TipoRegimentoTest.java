
package domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class TipoRegimentoTest {

    @Test
    public void testCreateTipoRegimentoValido() {

        TipoRegimento tipoRegimento = new SeriacaoAutomaticaObrigatoria("Designacao", " regras");

        String expected = "Designacao";

        String result = tipoRegimento.getDesignacao();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTipoRegimentoInvalido() {

        TipoRegimento tipoRegimento = new SeriacaoAutomaticaObrigatoria("", " regras");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTipoRegimentoInvalido2() {

        TipoRegimento tipoRegimento = new SeriacaoAutomaticaObrigatoria("Designacao", " ");
    }
}

