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

    public String getAreaAtividadeToStringCompletoByCodigoUnico(String codigoUnico) {
        return Plataforma.getInstance().getRepoAreaAtiv().getAreaAtividadeByCodUnico(new CodigoUnico(codigoUnico)).toStringCompleto();
    }

    public String getCategoriaTarefaToStringCompletoByNome(String nome) {
        return Plataforma.getInstance().getRepoCategoriaTarefa().getCategoriaTarefaByDescricao(nome).toStringCompleto();
    }

    public String getColaboradorToStringCompletoByEmail(String email) {
        return Plataforma.getInstance().getRepoColab().getColaboradorByEmail(new Email(email)).toString();
    }

    public String getCompetenciaTecnicaToStringCompletoByCodigoUnico(String codigoUnico) {
        return Plataforma.getInstance().getRepoCompTec().getCompetenciaTecnicaByCodUnico(new CodigoUnico(codigoUnico)).toString();
    }

    public String getOrganizacaoToStringCompletoByEmail(String email) {
        return Plataforma.getInstance().getRepoOrg().getOrganizacaoByEmail(new Email(email)).toString();
    }

    public String getTarefaToStringCompletoByCodigoUnico(String codigoUnico) {
        return Plataforma.getInstance().getRepoTarefa().getTarefaByCodigoUnico(new CodigoUnico(codigoUnico)).toString();
    }
}
