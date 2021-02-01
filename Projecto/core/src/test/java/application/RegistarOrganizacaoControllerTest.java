package application;

import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistarOrganizacaoControllerTest {


    @Test
    public void testRegistarOrganizacaoValida() {
        RegistarOrganizacaoController controller = new RegistarOrganizacaoController();
        assertTrue(controller.registarOrganizacao("org", 123123123, "www.org.com", 999999999,
                "org@org.com", "Rua da Povoa 23", "Porto", "4200-432",
                "nome", 999999999, "colab@org.com"));
    }

}