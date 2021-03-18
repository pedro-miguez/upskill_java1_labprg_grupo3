
package domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Class responsible for testing the technical competence.
 * 
 * @author Grupo 3
 */
public class CompetenciaTecnicaTest {

    //Tests a valid technical competence.
    @Test
    public void testCreateCompetenciaTecnicaValida() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        String expected = "JAV-01";
        String result = ct.getCodigoUnico().toString();

        assertEquals(expected, result);
    }

    //Tests a technical competence without an description.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCompetenciaTecnicaSemDescricao() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                " ", "Programação muito boa em Java", new ArrayList<>());
    }

    //Tests a technical competence without an detailed description.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCompetenciaTecnicaSemDescricaoDetalhada() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "PROG ", " ", new ArrayList<>());
    }

    //It tests whether there are equal technical skills.
    @Test
    public void testEqualsTrue() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());
    }

}
