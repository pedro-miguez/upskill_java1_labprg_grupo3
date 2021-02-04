package persistence;

import static org.junit.Assert.*;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import org.junit.Test;

public class RepositorioCompetenciaTecnicaTest {

    @Test
    public void testGetInstance() {
        RepositorioCompetenciaTecnica result = RepositorioCompetenciaTecnica.getInstance();

        RepositorioCompetenciaTecnica expected = RepositorioCompetenciaTecnica.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testAddCompetenciaTecnicaValida() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-03"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-05"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        int expected = RepositorioCompetenciaTecnica.getInstance().listarCompetenciasTecnicas().size() + 1;

        RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(ct);

        int result = RepositorioCompetenciaTecnica.getInstance().listarCompetenciasTecnicas().size();

        assertEquals(expected, result);
    }

    @Test
    public void testAddCompetenciaTecnicaInvalida() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(ct);

        CompetenciaTecnica ct2 = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        assertFalse(RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(ct2));
    }

    @Test
    public void testGetCompetenciaTecnicaByCodigoUnicoValido() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-01"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-01"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(ct);

        assertEquals(ct, RepositorioCompetenciaTecnica.getInstance().getCompetenciaTecnicaByCodUnico(new CodigoUnico("JAV-01")));
    }

    @Test (expected = CodigoNaoAssociadoException.class)
    public void testGetCompetenciaTecnicaByCodigoUnicoInvalido() {
        RepositorioCompetenciaTecnica.getInstance().getCompetenciaTecnicaByCodUnico(new CodigoUnico("JAV-03"));
    }

    @Test
    public void testGetCCompetenciasTecnicasByAreaAtividadeValido() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("PRG-02"), "Programação de Jogos", "Programação de variados jogos em JAVA");

        CompetenciaTecnica ct = new CompetenciaTecnica(new CodigoUnico("JAV-05"), areaAtividade,
                "Programação Java", "Programação muito boa em Java");

        RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(ct);

        int expected = 1;
        int result = RepositorioCompetenciaTecnica.getInstance().getCompetenciasTecnicasByAreaAtividade(areaAtividade).size();

        assertEquals(expected, result);
    }

}