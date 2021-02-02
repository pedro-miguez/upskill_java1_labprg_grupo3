package application;

import static org.junit.Assert.*;
import org.junit.Test;

public class DefinirCompetenciaTecnicaControllerTest {

    @Test
    public void testDefinirCompetenciaTecnicaValida{

        DefinirCompetenciaTecnicaController controller = new DefinirCompetenciaTecnicaController();
        assertTrue(controller.DefinirCompetenciaTecnica("abc-12", "def-34", "Canalizacao",
                "Tratar dos canos"));


    }


}