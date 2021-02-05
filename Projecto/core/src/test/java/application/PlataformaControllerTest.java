package application;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Plataforma;
import org.junit.Test;

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



}