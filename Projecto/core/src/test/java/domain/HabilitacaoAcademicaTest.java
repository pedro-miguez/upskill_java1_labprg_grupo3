package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the academic qualification.
 * 
 * @author Grupo 3
 */
public class HabilitacaoAcademicaTest {

    //Tests a valid academic qualification.
    @Test
    public void testCreateHabilitacaoAcademicaValida() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "Contabilidade", "ISCAP", 19.9);

        String expected = "ISCAP";
        String result = ha.getNomeInstituicao();

        assertEquals(expected, result);
    }

    //Tests a invalid academic qualification degree.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaGrauInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("", "Contabilidade", "ISCAP", 19.9);
    }

    //Tests a invalid course description.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaDesignacaoCursoInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "", "ISCAP", 19.9);
    }

    //Tests a invalid Institution Name.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaNomeInstituicaoInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "Contabilidade", "", 19.9);
    }

    //Tests a invalid course average.
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHabilitacaoAcademicaMediaCursoInvalido() {

        HabilitacaoAcademica ha = new HabilitacaoAcademica("Mestre", "Contabilidade", "ISCAP", 9.4);
    }
}