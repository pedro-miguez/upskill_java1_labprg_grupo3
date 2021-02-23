
package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReconhecimentoCTTest {
    @Test
    public void testReconhecimentoCTValido() {
        /*Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999),
                new Email("colab@org.com"), new NIF(214852581), new ArrayList<>());*/

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct, new GrauProficiencia(1, "Baixo"),
                new Data (2021, 02, 17));

        String expected = "2021/02/17";
        String result = recoCT.getDataReconhecimento().toAnoMesDiaString();

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReconhecimentoCTComDataInvalida() {
        /*Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999),
                new Email("colab@org.com"), new NIF(214852581), new ArrayList<>());*/

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct, new GrauProficiencia(1, "Baixo"),
                new Data (2021, 02, 33));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReconhecimentoCTComGrauProficienciaInvalido() {
        /*Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999),
                new Email("colab@org.com"), new NIF(214852581), new ArrayList<>());*/

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct, new GrauProficiencia(1, ""),
                new Data (2021, 02, 17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReconhecimentoCTComGrauProficienciaInvalido2() {
        /*Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999),
                new Email("colab@org.com"), new NIF(214852581), new ArrayList<>());*/

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct, new GrauProficiencia(0, "Baixo"),
                new Data (2021, 02, 17));
    }

    @Test
    public void testEqualsTrue() {
        /*Freelancer freelancer = new Freelancer("Bob Marley", new Telefone(999999999),
                new Email("colab@org.com"), new NIF(214852581), new ArrayList<>());*/

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"),
                "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java", new ArrayList<>());

        ReconhecimentoCT recoCT = new ReconhecimentoCT(ct, new GrauProficiencia(1, "Baixo"),
                new Data (2021, 02, 17));
    }

}
