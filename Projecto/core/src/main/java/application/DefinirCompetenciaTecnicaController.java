package application;

import domain.*;

import persistence.RepositorioAreaAtividade;
import persistence.RepositorioCompetenciaTecnica;

public class DefinirCompetenciaTecnicaController {

    public boolean definirCompetenciaTecnica(String codigoUnico, AreaAtividade areaAtividade,
                                             String descricao, String descDetalhada) {

        CompetenciaTecnica competenciaTecnica = new CompetenciaTecnica(new CodigoUnico(codigoUnico),
                areaAtividade,
                descricao, descDetalhada);

        return Plataforma.getInstance().getRepoCompTec().addCompetenciaTecnica(competenciaTecnica);
    }
}