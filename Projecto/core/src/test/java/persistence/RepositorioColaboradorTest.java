package persistence;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositorioColaboradorTest extends TestCase {

    @Test
    public void testGetInstance() {
        RepositorioColaborador result = RepositorioColaborador.getInstance();

        RepositorioColaborador expected = RepositorioColaborador.getInstance();

        assertEquals(result, expected);
    }

}