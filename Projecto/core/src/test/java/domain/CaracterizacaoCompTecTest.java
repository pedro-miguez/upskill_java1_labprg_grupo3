
package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the characterization of technical competence.
 * 
 * @author Grupo 3
 */
public class CaracterizacaoCompTecTest {

    //Tests a valid characterization of technical competence.
    @Test
    public void testCaracterizacaoCompTecTestValida() {

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, true, new GrauProficiencia(1, "Baixo"));

        boolean expected = true;
        boolean result = cct.isObrigatorio();

        assertEquals(expected, result);

    }

    //Tests a invalid characterization of technical competence.
    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida1() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "", "Programação muito boa em Java", new ArrayList<>());

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, true, new GrauProficiencia(3, "Alto"));
    }

    //Tests a invalid characterization of technical competence.
    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida2() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "", new ArrayList<>());

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, true, new GrauProficiencia(3, "Alto"));
    }

    //Tests a invalid characterization of technical competence.
    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida3() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "", new ArrayList<>());

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, false, new GrauProficiencia(3, "Alto"));
    }
}
