package domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class NIFTest {

    @Test
    public void testCreateNIFValido() {
        NIF nif = new NIF(123456789);

        int expected = 123456789;
        int result = nif.getNif();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNIFInvalido() {
        new NIF(1234567891);

    }

}