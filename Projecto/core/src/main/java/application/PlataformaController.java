package application;

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Current class is the one responsible to connect the GUI with the methods responsible for setting new
 *  tasks.
 */
public class PlataformaController {

    /**
     * Gets categorias tarefa.
     *
     * @return the categorias tarefa
     */
    public ArrayList<CategoriaTarefa> getCategoriasTarefa() {
        return Plataforma.getInstance().getRepoCategoriaTarefa().listarCategoriasTarefa();
    }

    /**
     * Gets areas atividade.
     *
     * @return the areas atividade
     */
    public ArrayList<AreaAtividade> getAreasAtividade() {
        return Plataforma.getInstance().getRepoAreaAtiv().listarAreaAtividade();
    }

    /**
     * Gets graus proficiencia.
     *
     * @return the graus proficiencia
     */
    public ArrayList<GrauProficiencia> getGrausProficiencia() {
        return new ArrayList<>(Arrays.asList(GrauProficiencia.values()));
    }

    /**
     * Gets competencias tecnicas by area atividade.
     *
     * @param areaAtividade the area atividade
     * @return the competencias tecnicas by area atividade
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) {
        return Plataforma.getInstance().getRepoCompTec().getCompetenciasTecnicasByAreaAtividade(areaAtividade);
    }

    /**
     * Reset user api.
     */
    public void resetUserAPI() {
        Plataforma.getInstance().resetUserAPI();
    }
}
