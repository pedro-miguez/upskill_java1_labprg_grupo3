package application;

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for some of the critical functions
 * that the Singleton class Platform (the class where the app runs around) hosts methods to save or load data via files,
 * or establish/reset the connection to the users API among others.
 */
public class PlataformaController {

    /**
     * Gets list of tasks categories
     *
     * @return the list of tasks categories
     */
    public ArrayList<CategoriaTarefa> getCategoriasTarefa() {
        return Plataforma.getInstance().getRepoCategoriaTarefa().listarCategoriasTarefa();
    }

    /**
     * Gets the list of activity areas.
     *
     * @return the list of activity areas.
     */
    public ArrayList<AreaAtividade> getAreasAtividade() {
        return Plataforma.getInstance().getRepoAreaAtiv().listarAreaAtividade();
    }

    /**
     * Gets mastery required
     *
     * @return the required mastery
     */
    public ArrayList<GrauProficiencia> getGrausProficiencia() {
        return new ArrayList<>(Arrays.asList(GrauProficiencia.values()));
    }

    /**
     * Gets the technical competence by area of activity.
     *
     * @param areaAtividade as area of activity.
     * @return the technical competence by area of activity.
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) {
        return Plataforma.getInstance().getRepoCompTec().getCompetenciasTecnicasByAreaAtividade(areaAtividade);
    }

    /**
     * Reset user api method.
     */
    public void resetUserAPI() {
        Plataforma.getInstance().resetUserAPI();
    }

    /**
     * Gets the complete representation of an activity area object by its unique code.
     *
     * @param codigoUnico as unique code.
     * @return the the complete representation of an activity area object by its unique code.
     */
    public String getAreaAtividadeToStringCompletoByCodigoUnico(String codigoUnico) {
        return Plataforma.getInstance().getRepoAreaAtiv().getAreaAtividadeByCodUnico(new CodigoUnico(codigoUnico)).toStringCompleto();
    }

    /**
     * Gets the complete representation of a task category object by its name.
     *
     * @param nome as name
     * @return the complete representation of a task category object by its name.
     */
    public String getCategoriaTarefaToStringCompletoByNome(String nome) {
        return Plataforma.getInstance().getRepoCategoriaTarefa().getCategoriaTarefaByDescricao(nome).toStringCompleto();
    }

    /**
     * Gets the complete representation of a collaborator object by its email.
     *
     * @param email as email
     * @return the complete representation of a collaborator object by its email.
     */
    public String getColaboradorToStringCompletoByEmail(String email) {
        return Plataforma.getInstance().getRepoColab().getColaboradorByEmail(new Email(email)).toString();
    }

    /**
     * Gets the complete representation of a technical competence object by its unique code.
     *
     * @param codigoUnico as unique code
     * @return the the complete representation of a technical competence object by its unique code.
     */
    public String getCompetenciaTecnicaToStringCompletoByCodigoUnico(String codigoUnico) {
        return Plataforma.getInstance().getRepoCompTec().getCompetenciaTecnicaByCodUnico(new CodigoUnico(codigoUnico)).toString();
    }

    /**
     * Gets the complete representation of an organization object by its email.
     *
     * @param email as email
     * @return the complete representation of an organization object by its email.
     */
    public String getOrganizacaoToStringCompletoByEmail(String email) {
        return Plataforma.getInstance().getRepoOrg().getOrganizacaoByEmail(new Email(email)).toString();
    }

    /**
     * Gets the complete representation of a task object by its unique code.
     *
     * @param codigoUnico as unique code
     * @return the complete representation of a task object by its unique code.
     */
    public String getTarefaToStringCompletoByCodigoUnico(String codigoUnico) {
        return Plataforma.getInstance().getRepoTarefa().getTarefaByCodigoUnico(new CodigoUnico(codigoUnico)).toString();
    }
}
