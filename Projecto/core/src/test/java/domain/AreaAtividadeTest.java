package domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class responsible for testing the Activity Area.
 * 
 * @author Grupo 3
 */
public class AreaAtividadeTest {

    //Tests a valid created activity area.
    @Test
    public void testCreateAreaAtividadeValida() {

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos", "Programação de variados jogos em JAVA");

        String expected = "Programação de Jogos";

        String result = areaAtividade.getDescricao();

        assertEquals(expected, result);
    }

    //Tests a invalid created activity area.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAreaAtividadeInvalida() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, " ", "Programação de variados jogos em JAVA");

    }

    //Tests a invalid created activity area.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAreaAtividadeInvalida1() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação Jogos", " ");

    }

}