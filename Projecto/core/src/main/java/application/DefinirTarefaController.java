package application;

import domain.CategoriaTarefa;
import domain.CodigoUnico;
import domain.Plataforma;
import domain.Tarefa;

public class DefinirTarefaController {

    public boolean definirTarefa(String codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                                 int duracaoHoras, float custo, String descricaoCategoria){

        Tarefa tarefa = new Tarefa(new CodigoUnico(codigoUnico), designacao, descricaoInformal, descricaoTecnica, duracaoHoras, custo
                ,Plataforma.getInstance().getRepoCategoriaTarefa().getCategoriaTarefaByDescricao(descricaoCategoria));

        return Plataforma.getInstance().getRepoTarefa().addTarefa(tarefa);
    }


}
