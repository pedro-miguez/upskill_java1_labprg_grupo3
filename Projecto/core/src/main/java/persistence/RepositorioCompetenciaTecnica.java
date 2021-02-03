package persistence;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Colaborador;
import domain.CompetenciaTecnica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The type RepositorioCompetenciaTecnica.
 */
public class RepositorioCompetenciaTecnica implements Serializable {

    private static RepositorioCompetenciaTecnica instance;

    private List<CompetenciaTecnica> competenciasTecnicas;

    /**
     * Competências técnicas que irão ser adicionadas no repositório.
     */
    private RepositorioCompetenciaTecnica(){
        competenciasTecnicas = new ArrayList<>();
    }

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioCompetenciaTecnica getInstance(){
        if (instance == null){
            instance = new RepositorioCompetenciaTecnica();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se uma competência técnica existe no repositório,
     * caso contrário é adicionada ao mesmo.
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
     * Método para obtenção de uma competência técnica através do seu código único.
     * @param codigoUnico
     * @return 
     */
    public CompetenciaTecnica getCompetenciaTecnicaByCodUnico(CodigoUnico codigoUnico){
        for (CompetenciaTecnica ct: competenciasTecnicas) {
            if(ct.getCodigoUnico() != null && ct.getCodigoUnico().equals(codigoUnico)) {
                return ct;
            }
        }
        throw new IllegalArgumentException("Não existe competência técnica com esse código único.");
    }

    /**
     * Método para obter na lista competências técnicas pela sua área de atividade.
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

    /**
     * Método para listar competências técnicas.
     * @return 
     */
    public ArrayList<CompetenciaTecnica> listarCompetenciasTecnicas(){
        return  new ArrayList<>(this.competenciasTecnicas);
    }


}
