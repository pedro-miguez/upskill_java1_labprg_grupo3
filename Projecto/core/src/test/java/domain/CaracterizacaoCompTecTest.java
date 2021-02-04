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

        boolean obrigatorio=true;

        GrauProficiencia grauProficiencia = GrauProficiencia.ALTO;

        ***************************************************************
        //new caracterizacao comp test

        String expected = "Código Unico: JAV-01; Area de Actividade: Código Unico: PRG-01; Descrição: Programação de Jogos; " +
                "Descrição Detalhada: Programação de variados jogos em JAVA; Descrição: Programação Java;" +
                " Descrição Detalhada: Programação muito boa em Java";
        String result = ct.toString();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida1() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "", "Programação muito boa em Java");

        boolean obrigatorio=true;

        GrauProficiencia grauProficiencia = GrauProficiencia.ALTO;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaracterizacaoCompTecTestInvalida2() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "");

        boolean obrigatorio=true;

        GrauProficiencia grauProficiencia = GrauProficiencia.ALTO;
    }

}