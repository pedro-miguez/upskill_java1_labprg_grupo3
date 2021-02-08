package application;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegistarColaboradorControllerTest {

    @Test
    public void testRegistarColaboradorValido() {

        RegistarColaboradorController controller = new RegistarColaboradorController();

        int expected = Plataforma.getInstance().getRepoColab().listarColaboradores().size() + 2;

        Organizacao org = new Organizacao("JulinhoBoss", new NIF(224050555),
                new Website("www.whostheboss.com"), new Telefone(221256354),
                new Email("julinho@thegreatestofalltime.com"), new EnderecoPostal
                ("Pia", "Monte", "4500-600"));

        Colaborador c = new Colaborador("Vitor", new Telefone(911074355),
                new Email("vitor@gmail.com"), org, Funcao.GESTOR);

        org.setGestor(c);

        Plataforma.getInstance().getRepoOrg().addOrganizacao(org);
        Plataforma.getInstance().getRepoColab().addColaborador(c);

        controller.registarColaborador("Mario", 911074355,
                "victor@gmail.com", "vitor@gmail.com");

        int result = Plataforma.getInstance().getRepoColab().listarColaboradores().size();

        assertEquals(expected, result);
    }
}