package application;

import domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlataformaControllerTest {
    private PlataformaController plataformaController = new PlataformaController();

    @Test
    public void testGetAreaAtividadeToStringCompletoByCodigoUnicoValido() {
        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico("AAA-99"), "Area Atividadez", "Area de Atividadezz");
        Plataforma.getInstance().getRepoAreaAtiv().addAreaAtividade(areaAtividade);

        String expected = areaAtividade.toStringCompleto();
        String result = plataformaController.getAreaAtividadeToStringCompletoByCodigoUnico("AAA-99");

        assertEquals(expected, result);
    }

    /*@Test
    public void testGetCategoriasTarefa(){

        CodigoUnico codigoUnico = new CodigoUnico("PRG-01");
        AreaAtividade areaAtividade = new AreaAtividade(codigoUnico, "Programacao de Jogos", "Programacao de variados jogos em JAVA");
        List<CaracterizacaoCompTec> competenciasTecnicas = new ArrayList<>();
        DefinirCategoriaTarefaController controller = new DefinirCategoriaTarefaController();
        controller.definirCategoriaTarefa(areaAtividade, "Canalizacao", competenciasTecnicas);

        CategoriaTarefa catTar = new CategoriaTarefa(areaAtividade, "Programacao",competenciasTecnicas);


        List<CategoriaTarefa> ct = new ArrayList<>();
                ct.add(0,catTar);

        Plataforma.getInstance().getRepoCategoriaTarefa().listarCategoriasTarefa();
        PlataformaController c = new PlataformaController();

        assertTrue(controller.getCategoriasTarefa(catTar));

        criar catTar
                adicionar
                        get

    }*/


}