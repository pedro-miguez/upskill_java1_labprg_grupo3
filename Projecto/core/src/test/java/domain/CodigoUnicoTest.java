package domain;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class responsible for testing the unique code.
 * 
 * @author Grupo 3
 */
public class CodigoUnicoTest {

    //Tests a valid unique code.
    @Test
    public void testCreateCodigoUnicoValido() {


        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        String expected = "PRG-01";

        String result = codigoUnico.getCodigoUnico();

        assertEquals(expected, result);

    }

    //Tests a invalid unique code.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido() {
        CodigoUnico codigoUnico = new CodigoUnico("PRGG-01");
    }

    //Tests a invalid unique code.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido1() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-011");
    }

    //Tests a invalid unique code.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido2() {
        CodigoUnico codigoUnico = new CodigoUnico("");
    }

    //Tests a invalid unique code.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido3() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-0*");
    }

    //Tests a invalid unique code.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCodigoUnicoInvalido4() {
        CodigoUnico codigoUnico = new CodigoUnico("P*G-01");
    }

}