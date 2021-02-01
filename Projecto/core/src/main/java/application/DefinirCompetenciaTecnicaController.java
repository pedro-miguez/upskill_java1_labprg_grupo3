package application;

import domain.*;

import persistence.RepositorioAreaAtividade;
import persistence.RepositorioCompetenciaTecnica;

public class DefinirCompetenciaTecnicaController {

    public boolean DefinirCompetenciaTecnica(String codigoUnico, String codigoUnicoAreaAtividade,
                                             String descricao, String descDetalhada) {

        CompetenciaTecnica competenciaTecnica = new CompetenciaTecnica(new CodigoUnico(codigoUnico),
                RepositorioAreaAtividade.getInstance().getAreaAtividadeByCodUnico(new CodigoUnico(codigoUnicoAreaAtividade)),
                descricao, descDetalhada);

        return RepositorioCompetenciaTecnica.getInstance().addCompetenciaTecnica(competenciaTecnica);
    }
}