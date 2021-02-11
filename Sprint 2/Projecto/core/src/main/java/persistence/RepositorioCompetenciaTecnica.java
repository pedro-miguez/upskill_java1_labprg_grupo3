package persistence;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Colaborador;
import domain.CompetenciaTecnica;
import exceptions.CodigoNaoAssociadoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * technical competence.
 */
public class RepositorioCompetenciaTecnica implements Serializable {

    private static RepositorioCompetenciaTecnica instance;

    private List<CompetenciaTecnica> competenciasTecnicas;

    /**
     * Technical skills that will be added to the repository.
     */
    private RepositorioCompetenciaTecnica(){
        competenciasTecnicas = new ArrayList<>();
    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioCompetenciaTecnica getInstance(){
        if (instance == null){
            instance = new RepositorioCompetenciaTecnica();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a technical competence exists in the repository, 
     * otherwise it is added to it.
     * @param competenciaTecnica
     * @return 
     */
    public boolean addCompetenciaTecnica(CompetenciaTecnica competenciaTecnica){
        if(this.competenciasTecnicas.contains(competenciaTecnica)){
            return false;
        } else {
            this.competenciasTecnicas.add(competenciaTecnica);
            return true;
        }
    }

    /**
     * Method for obtaining technical competence through its unique code.
     * @param codigoUnico
     * @return 
     */
    public CompetenciaTecnica getCompetenciaTecnicaByCodUnico(CodigoUnico codigoUnico){
        for (CompetenciaTecnica ct: competenciasTecnicas) {
            if(ct.getCodigoUnico() != null && ct.getCodigoUnico().equals(codigoUnico)) {
                return ct;
            }
        }
        throw new CodigoNaoAssociadoException("Não existe competência técnica com esse código único.");
    }

    /**
     * Method for obtaining technical skills on the list by their area of ​​activity.
     * @param areaAtividade
     * @return 
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) {
        ArrayList<CompetenciaTecnica> competenciasAreaAtividade = new ArrayList<>();

        for (CompetenciaTecnica ct : competenciasTecnicas) {
            if (ct.getAreaAtividade().equals(areaAtividade)) {
                competenciasAreaAtividade.add(ct);
            }
        }

        return competenciasAreaAtividade;
    }

    public CompetenciaTecnica criarCompetenciaTecnica(String codigoUnico, AreaAtividade areaAtividade,
                                                      String descricao, String descDetalhada) {
        return new CompetenciaTecnica(new CodigoUnico(codigoUnico),
                areaAtividade,
                descricao, descDetalhada);
    }

    /**
     * Method for listing technical skills.
     * @return 
     */
    public ArrayList<CompetenciaTecnica> listarCompetenciasTecnicas(){
        return  new ArrayList<>(this.competenciasTecnicas);
    }


}
