package domain;
import static org.junit.Assert.*;
import org.junit.Test;

public class TelefoneTest {
    @Test
    public void testTelefoneValido()
    {
        Telefone tlf = new Telefone(222110123);

        int expected = 222110123;
        int result = tlf.getTelefone();

        assertEquals(expected, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTelefoneInvalido() {
        new Telefone(000123654);

    }
}