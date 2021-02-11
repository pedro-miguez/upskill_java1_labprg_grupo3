package application;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DefinirTarefaControllerTest {

    @Test
    public void definirTarefaValida(){
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org);

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<>());

        Plataforma.getInstance().getRepoCategoriaTarefa().addCategoriaTarefa(categoriaTarefa);
        Plataforma.getInstance().getRepoOrg().addOrganizacao(org);
        Plataforma.getInstance().getRepoColab().addColaborador(colaborador);

        DefinirTarefaController controller = new DefinirTarefaController();

        assertTrue(controller.definirTarefa("TAR-01", "Programação Jogos", "Precisa-se progamador para jogos",
                "O programador tem de ter conhecimentos em JAVA e SQL", 100, 1500.00f, categoriaTarefa, "colab@org.com"));

    }

    @Test (expected = IllegalArgumentException.class)
    public void definirTarefaComCodigoUnicoInvalido(){

        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));

        Colaborador colaborador = new Colaborador("nome", new Telefone(999999999), new Email("colab@org.com"), org);

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<>());

        Plataforma.getInstance().getRepoCategoriaTarefa().addCategoriaTarefa(categoriaTarefa);
        Plataforma.getInstance().getRepoOrg().addOrganizacao(org);
        Plataforma.getInstance().getRepoColab().addColaborador(colaborador);
        DefinirTarefaController controller = new DefinirTarefaController();

        assertTrue(controller.definirTarefa("TARR-01", "Programação Jogos",
                "Precisa-se progamador para jogos",
                "O programador tem de ter conhecimentos em JAVA e SQL", 100,
                1500.00f, categoriaTarefa,"colab@org.com" ));
    }

}