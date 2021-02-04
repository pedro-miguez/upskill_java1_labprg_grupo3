package persistence;

import domain.CodigoUnico;
import domain.Colaborador;
import domain.Organizacao;
import domain.Tarefa;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * The type RepositorioTarefa.
 */
public class RepositorioTarefa implements Serializable {

    private static RepositorioTarefa instance;

    public ArrayList<Tarefa> tarefasRegistadas;

    /**
     * Tarefas que irão ser adicionadas (registadas) no repositório.
     */
    private RepositorioTarefa() {
        tarefasRegistadas = new ArrayList<>();
    }

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioTarefa getInstance() {
        if (instance == null) {
            instance = new RepositorioTarefa();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se uma tarefa existe no repositório,
     * caso contrário é adicionada ao mesmo.
     * @param tarefa
     * @return 
     */
    public boolean addTarefa(Tarefa tarefa) {
        if (this.tarefasRegistadas.contains(tarefa)) {
            return false;
        } else {
            this.tarefasRegistadas.add(tarefa);
            return true;
        }
    }

    /**
     * Método para obtenção de uma tarefa através do seu código único.
     * @param codigoUnico
     * @return 
     */
    public Tarefa getTarefaByCodigoUnico(CodigoUnico codigoUnico) {
        for (Tarefa tarefa : tarefasRegistadas) {
            if (tarefa.getCodigoUnico() != null && tarefa.getCodigoUnico().equals(codigoUnico)) {
                return tarefa;
            }
        }
        throw new IllegalArgumentException("Não existe nenhuma tarefa com esse código único.");
    }

    public ArrayList<Tarefa> getTarefasOrganizacao (Organizacao organizacao) {
        ArrayList<Tarefa> tarefasOrganizacao = new ArrayList<>();

        for (Tarefa t : tarefasRegistadas) {
            if (t.getOrganizacao().equals(organizacao)) {
                tarefasOrganizacao.add(t);
            }
        }
        return tarefasOrganizacao;
    }

    /**
     * Método para listar (registar) tarefas.
     * @return 
     */
    public ArrayList<Tarefa> listarTarefas() {
        return new ArrayList<>(this.tarefasRegistadas);
    }

    /**
     * Método para verificar se dois objectos (neste caso, tarefas) são
     * iguais.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositorioTarefa)) return false;

        RepositorioTarefa that = (RepositorioTarefa) o;

        return tarefasRegistadas.equals(that.tarefasRegistadas);
    }

}
