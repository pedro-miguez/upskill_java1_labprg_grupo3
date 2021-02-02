package application;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Plataforma;
import persistence.RepositorioAreaAtividade;

public class DefinirAreaAtividadeController {

    public boolean definirAreaAtividade(String codigoUnico, String descricao, String descricaoDetalhada) {

        AreaAtividade areaAtividade = new AreaAtividade(new CodigoUnico(codigoUnico), descricao, descricaoDetalhada);

        return Plataforma.getInstance().getRepoAreaAtiv().addAreaAtividade(areaAtividade);
    }

}
