package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the task category.
 * 
 * @author Grupo 3
 */
public class CategoriaTarefaTest {

    //Tests a valid task category.
    @Test
    public void testCreateCategoriaTarefaValida(){

        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos", "Programação de variados jogos em JAVA");

        CategoriaTarefa ct = new CategoriaTarefa(areaAtividade, "Programacao", competenciasTecnicas);

        String expected = "Descrição: Programacao; Area de Actividade: Programação de Jogos";

        String result = ct.toString();

        assertEquals(expected, result);
    }

    //Tests a invalid activity area.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAreaAtividadeInvalida() {
        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação de Jogos", "Programação de variados jogos em JAVA");

        CategoriaTarefa ct = new CategoriaTarefa(areaAtividade, "", competenciasTecnicas);

    }

}