package application;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DefinirTarefaControllerTest {

    @Test
    public void definirTarefaValida(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        Plataforma.getInstance().getRepoCategoriaTarefa().addCategoriaTarefa(categoriaTarefa);

        DefinirTarefaController controller = new DefinirTarefaController();

        assertTrue(controller.definirTarefa("TAR-01", "Programação Jogos", "Precisa-se progamador para jogos",
                "O programador tem de ter conhecimentos em JAVA e SQL", 100, 1500.00f, "Programação"));

    }

    @Test (expected = IllegalArgumentException.class)
    public void definirTarefaComCodigoUnicoInvalido(){

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos",
                "Programação de variados jogos em JAVA");

        CategoriaTarefa categoriaTarefa = new CategoriaTarefa(areaAtividade, "Programação", new ArrayList<CaracterizacaoCompTec>());

        DefinirTarefaController controller = new DefinirTarefaController();

        assertTrue(controller.definirTarefa("TARR-01", "Programação Jogos", "Precisa-se progamador para jogos",
                "O programador tem de ter conhecimentos em JAVA e SQL", 100, 1500.00f, "Programação"));

    }

}