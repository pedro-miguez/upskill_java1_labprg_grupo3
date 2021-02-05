package persistence;

import domain.CategoriaTarefa;
import domain.CodigoUnico;
import domain.Organizacao;
import domain.Tarefa;
import exceptions.CodigoNaoAssociadoException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class responsible for creating a repository to store information about
 * Task.
 */
public class RepositorioTarefa implements Serializable {

    private static RepositorioTarefa instance;

    public ArrayList<Tarefa> tarefasRegistadas;

    /**
     * Tasks that will be added (registered) in the repository.
     */
    private RepositorioTarefa() {
        tarefasRegistadas = new ArrayList<>();
    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioTarefa getInstance() {
        if (instance == null) {
            instance = new RepositorioTarefa();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a task exists in the repository, otherwise 
     * it is added to it.
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
     * Method for obtaining a task using its unique code.
     * @param codigoUnico
     * @return 
     */
    public Tarefa getTarefaByCodigoUnico(CodigoUnico codigoUnico) {
        for (Tarefa tarefa : tarefasRegistadas) {
            if (tarefa.getCodigoUnico() != null && tarefa.getCodigoUnico().equals(codigoUnico)) {
                return tarefa;
            }
        }
        throw new CodigoNaoAssociadoException("Não existe nenhuma tarefa com esse código único.");
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

    public Tarefa criarTarefa(String codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                              int duracaoHoras, float custo, CategoriaTarefa categoriaTarefa, Organizacao org) {
        return new Tarefa(new CodigoUnico(codigoUnico), designacao, descricaoInformal, descricaoTecnica, duracaoHoras, custo
                , categoriaTarefa, org);
    }

    /**
     * Method for listing (registering) tasks.
     * @return 
     */
    public ArrayList<Tarefa> listarTarefas() {
        return new ArrayList<>(this.tarefasRegistadas);
    }

    /**
     * Method to check if two objects (in this case, tasks) are equals.
     * 
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
