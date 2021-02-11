package application;

import domain.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistarOrganizacaoControllerTest {


    @Test
    public void testRegistarOrganizacaoValida() {
        RegistarOrganizacaoController controller = new RegistarOrganizacaoController();
        int expected = Plataforma.getInstance().getRepoOrg().listarOrganizacoes().size() + 1 +
                Plataforma.getInstance().getRepoColab().listarColaboradores().size() + 1;

        controller.registarOrganizacao("organiz99", 123123123, "www.org.com", 999999999,
                "org@org.com", "Rua da Povoa 23", "Porto", "4200-432",
                "gestorOrganiz", 987654123, "colab@org.com");
        int result = Plataforma.getInstance().getRepoOrg().listarOrganizacoes().size() +
                Plataforma.getInstance().getRepoColab().listarColaboradores().size();

        assertEquals(expected, result);

    }

}