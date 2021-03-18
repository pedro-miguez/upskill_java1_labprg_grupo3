package domain;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the Postal code.
 * 
 * @author Grupo 3
 */
public class EnderecoPostalTest {

    //Tests a address with a valid postal code.
    @Test
    public void testCreateEnderecoPostalcomCodigoPostalValido(){
        EnderecoPostal endereco = new EnderecoPostal ("Av. dos Extremos 68", "Braga, Portugal", "4700-136");

        String expected = "4700-136";

        String result = endereco.getCodPostal();

        assertEquals(expected, result);
    }

    //Tests a address with a invalid postal code.
    @Test (expected = IllegalArgumentException.class)
    public void testCreateEnderecoPostalcomCodigoPostalInvalido(){

        EnderecoPostal endereco = new EnderecoPostal ("Av. dos Extremos 68", "Braga, Portugal", "47005-136");

    }

    //Tests a address with a empty postal code.
    @Test (expected = IllegalArgumentException.class)
    public void testCreateEnderecoPostalcomCodigoPostalVazio(){

        EnderecoPostal endereco = new EnderecoPostal ("Av. dos Extremos 68", "Braga, Portugal", " ");

    }

}