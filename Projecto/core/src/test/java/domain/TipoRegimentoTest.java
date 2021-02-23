package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TipoRegimentoTest {

    @Test
    public void testCreateTipoRegimentoValido() {

        TipoRegimento tipoRegimento = new TipoRegimento("Designacao", " regras");

        String expected = "Designacao";

        String result = tipoRegimento.getDesignacao();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateTipoRegimentoInvalido() {

        TipoRegimento tipoRegimento = new TipoRegimento("", " regras");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTipoRegimentoInvalido2() {

        TipoRegimento tipoRegimento = new TipoRegimento("Designacao", " ");
    }
}