package application;

import domain.CaracterizacaoCompTec;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DefinirCategoriaTarefaControllerTest {

    @Test
    public void testDefinirCategoriaTarefaValida() {

        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();

        DefinirCategoriaTarefaController controller = new DefinirCategoriaTarefaController();

        assertTrue(controller.definirCategoriaTarefa("def-34", "Canalizacao",
                "resistencia pressao", true, ));
    }
}