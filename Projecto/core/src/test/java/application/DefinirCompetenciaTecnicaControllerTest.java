package application;

import static org.junit.Assert.*;

import domain.AreaAtividade;
import domain.CodigoUnico;
import org.junit.Test;

public class DefinirCompetenciaTecnicaControllerTest {

    @Test
    public void testDefinirCompetenciaTecnicaValida (){

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos", "Programação de variados jogos em JAVA");

        DefinirCompetenciaTecnicaController controller = new DefinirCompetenciaTecnicaController();
        assertTrue(controller.definirCompetenciaTecnica("abc-12", areaAtividade, "Canalizacao",
                "Tratar dos canos"));


    }


}