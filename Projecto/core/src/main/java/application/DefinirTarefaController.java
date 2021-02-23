package application;

import domain.*;
import persistence.RepositorioOrganizacao;
import persistence.RepositorioTarefa;

import java.sql.SQLException;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for setting new tasks.
 */
public class DefinirTarefaController {

    /**
     * Setting new task boolean.
     *
     * @param codigoUnico       as unique code
     * @param designacao        as description
     * @param descricaoInformal as informal description
     * @param descricaoTecnica  as technical description
     * @param duracaoHoras      as estimated time to perform task in hours
     * @param custo             as expense
     * @param categoriaTarefa   as task cathegory
     * @param colaboradorEmail  as colaborator email
     * @return the boolean
     */
    public boolean definirTarefa(String codigoUnico, String designacao, String descricaoInformal, 
                                String descricaoTecnica, int duracaoHoras, float custo, 
                                CategoriaTarefa categoriaTarefa, 
                                String colaboradorEmail) throws SQLException {


        RepositorioOrganizacao repositorioOrganizacao = RepositorioOrganizacao.getInstance();
        RepositorioTarefa repositorioTarefa = RepositorioTarefa.getInstance();



        Organizacao org = repositorioOrganizacao.getOrganizacaoByEmail(new Email(colaboradorEmail));

        Tarefa tarefa = repositorioTarefa.criarTarefa(codigoUnico, designacao, descricaoInformal,
                descricaoTecnica, duracaoHoras, custo, categoriaTarefa, org);

        return repositorioTarefa.insertTarefa(tarefa, colaboradorEmail);

    }


}
