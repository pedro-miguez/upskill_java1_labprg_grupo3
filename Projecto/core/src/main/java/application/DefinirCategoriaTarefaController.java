package application;

import domain.*;
import persistence.RepositorioCategoriaTarefa;

import java.sql.SQLException;
import java.util.List;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for setting new task categories.
 */
public class DefinirCategoriaTarefaController {

    /**
     * Setting new task category boolean.
     *
     * @param areaAtividade        as area atividade
     * @param descricao            as descricao
     * @param competenciasTecnicas as competencias tecnicas
     * @return the boolean
     */
    public boolean definirCategoriaTarefa(AreaAtividade areaAtividade, String descricao,
                                          List<CaracterizacaoCompTec> competenciasTecnicas) 
                                                            throws SQLException {


        RepositorioCategoriaTarefa repo = RepositorioCategoriaTarefa.getInstance();

        CategoriaTarefa categoriaTarefa = repo.criarCategoriaTarefa(areaAtividade, 
                                                                    descricao, 
                                                                    competenciasTecnicas);

        return repo.insertCategoriaTarefa(categoriaTarefa);
    }

}
