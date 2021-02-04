package application;

import domain.AreaAtividade;
import domain.CaracterizacaoCompTec;
import domain.CodigoUnico;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DefinirCategoriaTarefaControllerTest {

    @Test
    public void testDefinirCategoriaTarefaValida() {

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");
        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programacao de Jogos", "Programacao de variados jogos em JAVA");
        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();
        DefinirCategoriaTarefaController controller = new DefinirCategoriaTarefaController();

        assertTrue(controller.definirCategoriaTarefa(areaAtividade, "Canalizacao", competenciasTecnicas));
    }
}