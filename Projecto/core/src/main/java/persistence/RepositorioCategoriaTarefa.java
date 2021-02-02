package persistence;

import domain.AreaAtividade;
import domain.CategoriaTarefa;
import domain.CodigoUnico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaTarefa implements Serializable {

    private static RepositorioCategoriaTarefa instance;

    private List<CategoriaTarefa> categoriasTarefas;

    private RepositorioCategoriaTarefa(){
        categoriasTarefas = new ArrayList<>();
    }

    public static RepositorioCategoriaTarefa getInstance(){
        if (instance == null){
            instance = new RepositorioCategoriaTarefa();
        }
        return instance;
    }

    public boolean addCategoriaTarefa(CategoriaTarefa categoriaTarefa){
        if(this.categoriasTarefas.contains(categoriaTarefa)){
            return false;
        } else {
            this.categoriasTarefas.add(categoriaTarefa);
            return true;
        }
    }

    public CategoriaTarefa getCategoriaTarefaByDescricao(String descricao){
        for (CategoriaTarefa catt : categoriasTarefas) {
            if(catt.getDescricao() != null && catt.getDescricao().equals(descricao)) {
                return catt;
            }
        } throw new IllegalArgumentException("Não existe nenhuma categoria de tarefa com esse nome.");
    }

    public ArrayList<CategoriaTarefa> listarCategoriasTarefa(){
        return  new ArrayList<>(this.categoriasTarefas);
    }

}
