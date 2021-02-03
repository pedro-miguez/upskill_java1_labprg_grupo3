package application;

import domain.*;

public class DefinirTarefaController {

    public boolean definirTarefa(String codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                                 int duracaoHoras, float custo, CategoriaTarefa categoriaTarefa, String colaboradorEmail){

        Colaborador collab = Plataforma.getInstance().getRepoColab().getColaboradorByEmail(new Email(colaboradorEmail));

        Organizacao org = collab.getOrganizacao();

        Tarefa tarefa = new Tarefa(new CodigoUnico(codigoUnico), designacao, descricaoInformal, descricaoTecnica, duracaoHoras, custo
                , categoriaTarefa, org);

        return Plataforma.getInstance().getRepoTarefa().addTarefa(tarefa);
    }


}
