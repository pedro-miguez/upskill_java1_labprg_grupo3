package persistence;


import domain.AreaAtividade;
import domain.CodigoUnico;
import exceptions.CodigoNaoAssociadoException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepositorioAreaAtividadeTest {

    @Test
    public void testGetInstance() {
        RepositorioAreaAtividade result = RepositorioAreaAtividade.getInstance();

        RepositorioAreaAtividade expected = RepositorioAreaAtividade.getInstance();

        assertEquals(result, expected);
    }

    @Test
    public void testAddAreaAtividadeValida() {
        CodigoUnico codigoUnico = new CodigoUnico("AAA-00");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Area Atividade", "Area de Atividade");

        int expected = RepositorioAreaAtividade.getInstance().listarAreaAtividade().size() + 1;

        RepositorioAreaAtividade.getInstance().addAreaAtividade(areaAtividade);

        int result = RepositorioAreaAtividade.getInstance().listarAreaAtividade().size();

        assertEquals(expected, result);

    }

    @Test
    public void testAddAreaAtividadeDuplicada() {
        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação Jogos", "Programação Jogos em java");

        AreaAtividade areaAtividade1 = new AreaAtividade(codigoUnico, "Programação Jogos", "Programação Jogos em java");

        RepositorioAreaAtividade.getInstance().addAreaAtividade(areaAtividade);

        assertFalse(RepositorioAreaAtividade.getInstance().addAreaAtividade(areaAtividade1));
    }

    @Test
    public void testGetAreaAtividadeByCodUnicoValido() {

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação Jogos", "Programação Jogos em java");

        RepositorioAreaAtividade.getInstance().addAreaAtividade(areaAtividade);

        AreaAtividade areaAtividade1 = RepositorioAreaAtividade.getInstance().getAreaAtividadeByCodUnico(codigoUnico);

        assertEquals(areaAtividade,areaAtividade1);

    }

    @Test(expected = CodigoNaoAssociadoException.class)
    public void testGetAreaAtividadeByCodUnicoInvalido() {

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");

        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programação Jogos", "Programação Jogos em java");

        RepositorioAreaAtividade.getInstance().addAreaAtividade(areaAtividade);

        CodigoUnico codigoUnico1 = new CodigoUnico("PRG-02");

        RepositorioAreaAtividade.getInstance().getAreaAtividadeByCodUnico(codigoUnico1);


    }

}