package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class DefinirAreaAtividadeControllerTest {

    @Test
    public void testDefinirAreaAtividadeValida(){
        DefinirAreaAtividadeController controller = new DefinirAreaAtividadeController();

        assertTrue(controller.definirAreaAtividade("PRG-05", "Programação JAVA", "Desenvolvimento de apps em JAVA"));
    }

}