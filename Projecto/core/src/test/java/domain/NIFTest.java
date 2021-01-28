package domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NIFTest {

    @Test
    void testCreateNIFValido() {
    
    NIF nif = new NIF(123456789);
        
    int expected = 123456789;
    
    int result = nif.getNif();
    
    assertEquals(expected, result);
}
    
}