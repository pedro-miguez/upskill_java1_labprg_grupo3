package persistence;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Colaborador;
import domain.CompetenciaTecnica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCompetenciaTecnica implements Serializable {

    private static RepositorioCompetenciaTecnica instance;

    private List<CompetenciaTecnica> competenciasTecnicas;

    private RepositorioCompetenciaTecnica(){
        competenciasTecnicas = new ArrayList<>();
    }

    public static RepositorioCompetenciaTecnica getInstance(){
        if (instance == null){
            instance = new RepositorioCompetenciaTecnica();
        }
        return instance;
    }

    public boolean addCompetenciaTecnica(CompetenciaTecnica competenciaTecnica){
        if(this.competenciasTecnicas.contains(competenciaTecnica)){
            return false;
        } else {
            this.competenciasTecnicas.add(competenciaTecnica);
            return true;
        }
    }

    public CompetenciaTecnica getCompetenciaTecnicaByCodUnico(CodigoUnico codigoUnico){
        for (CompetenciaTecnica ct: competenciasTecnicas) {
            if(ct.getCodigoUnico() != null && ct.getCodigoUnico().equals(codigoUnico)) {
                return ct;
            }
        }
        throw new IllegalArgumentException("Não existe competência técnica com esse código único.");
    }

    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) {
        ArrayList<CompetenciaTecnica> competenciasAreaAtividade = new ArrayList<>();

        for (CompetenciaTecnica ct : competenciasTecnicas) {
            if (ct.getAreaAtividade().equals(areaAtividade)) {
                competenciasAreaAtividade.add(ct);
            }
        }

        return competenciasAreaAtividade;
    }

    public ArrayList<CompetenciaTecnica> listarCompetenciasTecnicas(){
        return  new ArrayList<>(this.competenciasTecnicas);
    }
}
