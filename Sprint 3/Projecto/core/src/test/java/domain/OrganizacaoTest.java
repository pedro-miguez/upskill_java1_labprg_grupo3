
package domain;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganizacaoTest {

    @Test
    public void testCreateOrganizacaoValido() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        String expected = "org";

        assertEquals(expected, org.getNome());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateOrganizacaoNomeInvalido() {
        new Organizacao("", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
    }

}
