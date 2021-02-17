package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrauProficienciaTest {

    @Test
    public void testCriarGrauProficienciaValido(){

        GrauProficiencia grau = new GrauProficiencia(1, "Baixo");

        int expected = 1;

        int result = grau.getNivel();

        assertEquals(expected, result);

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCriarGrauProficienciaNivelInvalido(){

        GrauProficiencia grau = new GrauProficiencia(-1, "Baixo");

    }

    @Test (expected = IllegalArgumentException.class)
    public void testCriarGrauProficienciaDesignacaoInvalido(){

        GrauProficiencia grau = new GrauProficiencia(1, " ");

    }

}