package application;

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PlataformaController {

    public ArrayList<CategoriaTarefa> getCategoriasTarefa() {
        return Plataforma.getInstance().getRepoCategoriaTarefa().listarCategoriasTarefa();
    }

    public ArrayList<AreaAtividade> getAreasAtividade() {
        return Plataforma.getInstance().getRepoAreaAtiv().listarAreaAtividade();
    }

    public ArrayList<GrauProficiencia> getGrausProficiencia() {
        return new ArrayList<>(Arrays.asList(GrauProficiencia.values()));
    }

    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) {
        return Plataforma.getInstance().getRepoCompTec().getCompetenciasTecnicasByAreaAtividade(areaAtividade);
    }

    public void resetUserAPI() {
        Plataforma.getInstance().resetUserAPI();
    }
}
