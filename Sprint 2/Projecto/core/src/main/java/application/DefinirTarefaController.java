package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioTarefa;

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for setting new
 * tasks.
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
    public boolean definirTarefa(String codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                                 int duracaoHoras, float custo, CategoriaTarefa categoriaTarefa, String colaboradorEmail){

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioColaborador repositorioColaborador = plataforma.getRepoColab();
        RepositorioTarefa repositorioTarefa = plataforma.getRepoTarefa();

        Colaborador collab = repositorioColaborador.getColaboradorByEmail(new Email(colaboradorEmail));

        Organizacao org = collab.getOrganizacao();

        Tarefa tarefa = repositorioTarefa.criarTarefa(codigoUnico, designacao, descricaoInformal,
                descricaoTecnica, duracaoHoras, custo, categoriaTarefa, org);

        return repositorioTarefa.addTarefa(tarefa);
    }


}
