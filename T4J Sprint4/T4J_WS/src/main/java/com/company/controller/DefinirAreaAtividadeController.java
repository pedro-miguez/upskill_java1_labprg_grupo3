package application;

import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Plataforma;
import persistence.RepositorioAreaAtividade;

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for setting new
 * activity areas.
 */
public class DefinirAreaAtividadeController {

    /**
     * Setting new area of activity boolean.
     *
     * @param codigoUnico        as unique code
     * @param descricao          as description
     * @param descricaoDetalhada as detailed description
     * @return the boolean
     */
    public boolean definirAreaAtividade(String codigoUnico, String descricao, String descricaoDetalhada) {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioAreaAtividade repo = plataforma.getRepoAreaAtiv();

        AreaAtividade areaAtividade = repo.criarAreaAtividade(codigoUnico, descricao, descricaoDetalhada);

        return repo.addAreaAtividade(areaAtividade);
    }
}
