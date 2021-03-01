package application;

import domain.*;
import persistence.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Current class is the one responsible to connect the GUI with the methods
 * responsible for some of the critical functions that the Singleton class
 * Platform (the class where the app runs around) hosts methods to save or load
 * data via files, or establish/reset the connection to the users API among others.
 */
public class ServiceController {

    /**
     * Gets list of tasks categories
     *
     * @return the list of tasks categories
     */
    public ArrayList<CategoriaTarefa> getCategoriasTarefa() throws SQLException {
        return RepositorioCategoriaTarefa.getInstance().listarCategoriasTarefa();
    }

    /**
     * Gets the list of activity areas.
     *
     * @return the list of activity areas.
     */
    public ArrayList<AreaAtividade> getAreasAtividade() throws SQLException {
        return RepositorioAreaAtividade.getInstance().listarAreasAtividade();
    }

    /**
     * Gets mastery required
     *
     * @return the required mastery
     */
    public ArrayList<GrauProficiencia> getGrausProficiencia(CompetenciaTecnica competenciaTecnica) {
        return new ArrayList<GrauProficiencia>((competenciaTecnica.getGraus()));
    }

    /**
     * Gets the technical competence by area of activity.
     *
     * @param areaAtividade as area of activity.
     * @return the technical competence by area of activity.
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) throws SQLException {
        return RepositorioCompetenciaTecnica.getInstance().getCompetenciasTecnicasByAreaAtividade(areaAtividade);
    }

    /**
     * Reset user api method.
     */
    public void resetUserAPI() throws SQLException {
        Plataforma.getInstance().resetUserAPI();
    }

    /**
     * Gets the complete representation of an activity area object by its unique code.
     *
     * @param codigoUnico as unique code.
     * @return the the complete representation of an activity area object by its unique code.
     */
    public String getAreaAtividadeToStringCompletoByCodigoUnico(String codigoUnico) {
        return RepositorioAreaAtividade.getInstance().getAreaAtividadeByCodUnico(new CodigoUnico(codigoUnico)).toStringCompleto();
    }

    /**
     * Gets the complete representation of a task category object by its name.
     *
     * @param nome as name
     * @return the complete representation of a task category object by its name.
     */
    public String getCategoriaTarefaToStringCompletoByNome(String nome, AreaAtividade areaAtividade) {
        return RepositorioCategoriaTarefa.getInstance().getCategoriaTarefaByDescricaoAndAreaAtividade(nome, areaAtividade).toStringCompleto();
    }

    /**
     * Gets the complete representation of a freelancer object by his email.
     *
     * @param email as email
     * @return the complete representation of a freelancer object by his email.
     */
    public String getFreelancerToStringCompletoByEmail(String email) throws SQLException {
        return RepositorioFreelancer.getInstance().getFreelancerByEmail(new Email(email)).toString();
    }

    /**
     * Gets the complete representation of a collaborator object by its email.
     *
     * @param email as email
     * @return the complete representation of a collaborator object by its email.
     */
    public String getColaboradorToStringCompletoByEmail(String email) throws SQLException {
        return RepositorioColaborador.getInstance().getColaboradorByEmail(new Email(email)).toString();
    }

    /**
     * Gets the complete representation of a technical competence object by its unique code.
     *
     * @param codigoUnico as unique code
     * @return the the complete representation of a technical competence object by its unique code.
     */
    public String getCompetenciaTecnicaToStringCompletoByCodigoUnico(String codigoUnico, AreaAtividade areaAtividade) {
        return RepositorioCompetenciaTecnica.getInstance().getCompetenciaTecnicaByCodUnico(new CodigoUnico(codigoUnico), areaAtividade).toString();
    }

    /**
     * Gets the complete representation of an organization object by its email.
     *
     * @param email as email
     * @return the complete representation of an organization object by its email.
     */
    public String getOrganizacaoToStringCompletoByEmail(String email) throws SQLException {
        return RepositorioOrganizacao.getInstance().getOrganizacaoByEmail(new Email(email)).toString();
    }

    /**
     * Gets the complete representation of a task object by its unique code.
     *
     * @param codigoUnico as unique code
     * @return the complete representation of a task object by its unique code.
     */
    public String getTarefaToStringCompletoByCodigoUnico(String codigoUnico, String emailColaborador) {
        return RepositorioTarefa.getInstance().getTarefaByCodigoUnico(new CodigoUnico(codigoUnico), emailColaborador).toString();
    }

    /**
     * Gets the tasks by organization.
     *
     * @param emailColaborador as email of collaborator
     * @return the tasks by organization.
     */
    public ArrayList<Tarefa> getTarefasOrganizacao(String emailColaborador) {
        Organizacao org = RepositorioOrganizacao.getInstance().getOrganizacaoByEmail(new Email(emailColaborador));

        return RepositorioTarefa.getInstance().getTarefasOrganizacao(org);
    }

    /**
     * Gets list of technical skills
     *
     * @return the list of technical skills
     */
    public ArrayList<CompetenciaTecnica> getAllCompetenciasTecnicas() {
        return RepositorioCompetenciaTecnica.getInstance().listarCompetenciasTecnicas();
    }

    /**
     * Gets list of regiment types
     *
     * @return the list of regiment types
     */
    public ArrayList<TipoRegimento> getTiposRegimento() throws SQLException {
        return RepositorioAnuncio.getInstance().getTiposRegimento();
    }

    /**
     * Gets the complete representation of a advertisement object by its task.
     *
     * @param tarefa as task
     * @return the complete representation of a advertisement object by its task.
     */
    public String getAnunciotoStringCompletoByTarefa(Tarefa tarefa) {
        return RepositorioAnuncio.getInstance().getAnuncioByTarefa(tarefa).toString();
    }

    /**
     * Gets the complete representation of a application object by its advertisement
     * of a freelancer.
     * 
     * @param anuncio
     * @param emailFreelancer
     * @return the complete representation of a application object by its advertisement
     * of a freelancer.
     */
    public String getCandidaturatoStringCompletoByAnuncioFreelancer(Anuncio anuncio, String emailFreelancer) {
        return RepositorioCandidatura.getInstance().getCandidaturaByAnuncioFreelancer(anuncio, emailFreelancer).toString();
    }

    /**
     * Obtains from the list 'Anuncio' all the advertisements for serialization.
     * 
     * @param emailColaborador
     * @return RepositorioAnuncio.getInstance().getAllAnunciosSeriacao
     */
    public ArrayList<Anuncio> getAllAnunciosSeriacao(String emailColaborador) {
        return RepositorioAnuncio.getInstance().getAllAnunciosSeriacao(emailColaborador);
    }

}
