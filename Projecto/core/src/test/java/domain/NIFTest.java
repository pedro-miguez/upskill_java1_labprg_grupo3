package domain;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class responsible for testing the NIF.
 * 
 * @author Grupo 3
 */
public class NIFTest {

    //Tests a valid nif.
    @Test
    public void testCreateNIFValido() {
        NIF nif = new NIF(123456789);

        int expected = 123456789;
        int result = nif.getNif();

        assertEquals(expected, result);
    }

    //Tests a invalid nif.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNIFInvalido() {
        new NIF(1234567891);

    }

}