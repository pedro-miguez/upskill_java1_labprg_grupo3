package persistence;

import domain.CodigoUnico;
import domain.Tarefa;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioTarefa implements Serializable {

    private static RepositorioTarefa instance;

    public ArrayList<Tarefa> tarefasRegistadas;

    private RepositorioTarefa() {
        tarefasRegistadas = new ArrayList<>();
    }

    public static RepositorioTarefa getInstance() {
        if (instance == null) {
            instance = new RepositorioTarefa();
        }
        return instance;
    }

    public boolean addTarefa(Tarefa tarefa) {
        if (this.tarefasRegistadas.contains(tarefa)) {
            return false;
        } else {
            this.tarefasRegistadas.add(tarefa);
            return true;
        }
    }

    public Tarefa getTarefaByCodigoUnico(CodigoUnico codigoUnico) {
        for (Tarefa tarefa : tarefasRegistadas) {
            if (tarefa.getCodigoUnico() != null && tarefa.getCodigoUnico().equals(codigoUnico)) {
                return tarefa;
            }
        }
        throw new IllegalArgumentException("Não existe nenhuma tarefa com esse código único.");
    }

    public ArrayList<Tarefa> listarTarefas() {
        return new ArrayList<>(this.tarefasRegistadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositorioTarefa)) return false;

        RepositorioTarefa that = (RepositorioTarefa) o;

        return tarefasRegistadas.equals(that.tarefasRegistadas);
    }

}
