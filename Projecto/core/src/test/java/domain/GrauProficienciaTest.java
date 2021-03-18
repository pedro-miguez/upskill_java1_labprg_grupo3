package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the degree of proficiency.
 * 
 * @author Grupo 3
 */
public class GrauProficienciaTest {

    //Tests a valid degree of proficiency.
    @Test
    public void testCriarGrauProficienciaValido(){

        GrauProficiencia grau = new GrauProficiencia(1, "Baixo");

        int expected = 1;

        int result = grau.getNivel();

        assertEquals(expected, result);

    }

    //Tests a invalid degree of proficiency level.
    @Test (expected = IllegalArgumentException.class)
    public void testCriarGrauProficienciaNivelInvalido(){

        GrauProficiencia grau = new GrauProficiencia(-1, "Baixo");

    }

    //Tests a invalid degree of proficiency designation.
    @Test (expected = IllegalArgumentException.class)
    public void testCriarGrauProficienciaDesignacaoInvalido(){

        GrauProficiencia grau = new GrauProficiencia(1, " ");

    }

}