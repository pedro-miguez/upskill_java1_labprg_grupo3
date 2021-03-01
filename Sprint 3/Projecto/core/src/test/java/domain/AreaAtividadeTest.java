package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class AreaAtividadeTest {

    @Test
    public void testCreateAreaAtividadeValida() {

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos", "Programação de variados jogos em JAVA");

        String expected = "Programação de Jogos";

        String result = areaAtividade.getDescricao();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAreaAtividadeInvalida() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, " ", "Programação de variados jogos em JAVA");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAreaAtividadeInvalida1() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação Jogos", " ");

    }

}