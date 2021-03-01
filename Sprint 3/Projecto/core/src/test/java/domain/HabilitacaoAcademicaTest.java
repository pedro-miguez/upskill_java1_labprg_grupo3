package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class HabilitacaoAcademicaTest {

    @Test
    public void testCreateHabilitacaoAcademicaValida() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "Contabilidade", "ISCAP", 19.9);

        String expected = "ISCAP";
        String result = ha.getNomeInstituicao();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaGrauInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("", "Contabilidade", "ISCAP", 19.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaDesignacaoCursoInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "", "ISCAP", 19.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaNomeInstituicaoInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "Contabilidade", "", 19.9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaMediaCursoInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "Contabilidade", "ISCAP", 9.4);
    }
}