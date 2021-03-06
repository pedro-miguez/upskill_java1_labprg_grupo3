package domain;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class responsible for testing the phone number.
 * 
 * @author Grupo 3
 */
public class TelefoneTest {
    
    //Tests a valid phone number.
    @Test
    public void testTelefoneValido()
    {
        Telefone tlf = new Telefone(222110123);

        int expected = 222110123;
        int result = tlf.getTelefone();

        assertEquals(expected, result);
    }
    
    //Tests a invalid phone number.
    @Test(expected = IllegalArgumentException.class)
    public void testTelefoneInvalido() {
        new Telefone(000123654);

    }
}