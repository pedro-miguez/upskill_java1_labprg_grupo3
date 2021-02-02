package application;

import domain.*;

import persistence.RepositorioAreaAtividade;
import persistence.RepositorioCompetenciaTecnica;

public class DefinirCompetenciaTecnicaController {

    public boolean DefinirCompetenciaTecnica(String codigoUnico, String codigoUnicoAreaAtividade,
                                             String descricao, String descDetalhada) {

        CompetenciaTecnica competenciaTecnica = new CompetenciaTecnica(new CodigoUnico(codigoUnico),
                Plataforma.getInstance().getRepoAreaAtiv().getAreaAtividadeByCodUnico(new CodigoUnico(codigoUnicoAreaAtividade)),
                descricao, descDetalhada);

        return Plataforma.getInstance().getRepoCompTec().addCompetenciaTecnica(competenciaTecnica);
    }
}