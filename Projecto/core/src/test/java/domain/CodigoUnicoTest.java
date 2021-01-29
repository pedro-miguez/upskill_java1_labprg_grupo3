package domain;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodigoUnicoTest {

    @Test
    public void createCodigoUnicoValido(){

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        String expected = "PRG-01";

        String result = codigoUnico.getCodigoUnico();

        assertEquals(expected, result);

    }

    @Test
    public void createCodigoUnicoInvalido(){

    }

    @Test
    public void createCodigoUnicoInvalido1(){

    }

    @Test
    public void createCodigoUnicoInvalido2(){

    }
    @Test
    public void createCodigoUnicoInvalido3(){

    }

}