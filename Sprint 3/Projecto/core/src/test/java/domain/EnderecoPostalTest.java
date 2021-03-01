package domain;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnderecoPostalTest {

    @Test
    public void testCreateEnderecoPostalcomCodigoPostalValido(){
        EnderecoPostal endereco = new EnderecoPostal ("Av. dos Extremos 68", "Braga, Portugal", "4700-136");

        String expected = "4700-136";

        String result = endereco.getCodPostal();

        assertEquals(expected, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateEnderecoPostalcomCodigoPostalInvalido(){

        EnderecoPostal endereco = new EnderecoPostal ("Av. dos Extremos 68", "Braga, Portugal", "47005-136");

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateEnderecoPostalcomCodigoPostalVazio(){

        EnderecoPostal endereco = new EnderecoPostal ("Av. dos Extremos 68", "Braga, Portugal", " ");

    }

}