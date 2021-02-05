package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterizacaoCompTecTest {

    @Test
    public void testCaracterizacaoCompTecTestValida() {

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, true, GrauProficiencia.ALTO);

        boolean expected = true;
        boolean result = cct.isObrigatorio();

        assertEquals(expected, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida1() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "", "Programação muito boa em Java");

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, true, GrauProficiencia.ALTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida2() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "");

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, true, GrauProficiencia.ALTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida3() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "");

        CaracterizacaoCompTec cct = new CaracterizacaoCompTec(ct, false, GrauProficiencia.ALTO);
    }
}