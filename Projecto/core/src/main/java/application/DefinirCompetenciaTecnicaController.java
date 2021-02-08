package application;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.CompetenciaTecnica;
import domain.Plataforma;
import persistence.RepositorioCompetenciaTecnica;

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for setting new
 * technical competences.
 */
public class DefinirCompetenciaTecnicaController {

    /**
     * Setting new technical competence boolean.
     *
     * @param codigoUnico   as unique code
     * @param areaAtividade as activity area
     * @param descricao     as description
     * @param descDetalhada as detailed description
     * @return the boolean
     */
    public boolean definirCompetenciaTecnica(String codigoUnico, AreaAtividade areaAtividade,
                                             String descricao, String descDetalhada) {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioCompetenciaTecnica repo = plataforma.getRepoCompTec();

        CompetenciaTecnica competenciaTecnica = repo.criarCompetenciaTecnica(codigoUnico, areaAtividade, descricao, descDetalhada);

        return repo.addCompetenciaTecnica(competenciaTecnica);
    }

}