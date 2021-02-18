package domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class HabilitacaoAcademicaTest {

    public HabilitacaoAcademica(String nome, Telefone telefone, Email email, NIF nif) {
        setGrau(grau);
        setDesignacaoCurso(designacaoCurso);
        setNomeInstituicao(nomeInstituicao);
        setMediaCurso(mediaCurso);
    }

    @Test
    public void testHabilitacaoAcademicaValida() {

        Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999), new Email("colab@org.com"),
                new NIF(214852581));

        HabilitacaoAcademica ha = new HabilitacaoAcademica(" ")

        String expected = "Bob Marley";
        String result = freelancer.getNome();

        assertEquals(expected, result);
    }

}