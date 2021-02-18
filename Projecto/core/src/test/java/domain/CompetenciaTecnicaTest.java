
package domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class CompetenciaTecnicaTest {

    @Test
    public void testCreateCompetenciaTecnicaValida() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        String expected = "JAV-01";
        String result = ct.getCodigoUnico().toString();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCompetenciaTecnicaSemDescricao() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                " ", "Programação muito boa em Java", new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCompetenciaTecnicaSemDescricaoDetalhada() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "PROG ", " ", new ArrayList<>());
    }

    @Test
    public void testEqualsTrue() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());
    }

}
