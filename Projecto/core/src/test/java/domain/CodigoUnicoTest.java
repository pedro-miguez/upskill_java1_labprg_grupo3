package domain;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodigoUnicoTest {

    @Test
    public void testCreateCodigoUnicoValido() {


        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        String expected = "PRG-01";

        String result = codigoUnico.getCodigoUnico();

        assertEquals(expected, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido() {
        CodigoUnico codigoUnico = new CodigoUnico("PRGG-01");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido1() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-011");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido2() {
        CodigoUnico codigoUnico = new CodigoUnico("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido3() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-0*");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido4() {
        CodigoUnico codigoUnico = new CodigoUnico("P*G-01");
    }

}