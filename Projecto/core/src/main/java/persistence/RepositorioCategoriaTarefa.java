package persistence;

import domain.AreaAtividade;
import domain.CategoriaTarefa;
import domain.CodigoUnico;
import exceptions.NomeNaoAssociadoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * task categories.
 */
public class RepositorioCategoriaTarefa implements Serializable {

    private static RepositorioCategoriaTarefa instance;

    private List<CategoriaTarefa> categoriasTarefas;

    /**
     * Task categories that will be added to the repository.
     */
    private RepositorioCategoriaTarefa(){
        categoriasTarefas = new ArrayList<>();
    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioCategoriaTarefa getInstance(){
        if (instance == null){
            instance = new RepositorioCategoriaTarefa();
        }
        return instance;
    }

    /**
     * Boolean method that checks if an task category exists in the repository, 
     * otherwise it is added to it.
     * @param categoriaTarefa
     * @return 
     */
    public boolean addCategoriaTarefa(CategoriaTarefa categoriaTarefa){
        if(this.categoriasTarefas.contains(categoriaTarefa)){
            return false;
        } else {
            this.categoriasTarefas.add(categoriaTarefa);
            return true;
        }
    }

    /**
     * Method for obtaining a task category through its description.
     * @param descricao
     * @return 
     */
    public CategoriaTarefa getCategoriaTarefaByDescricao(String descricao){
        for (CategoriaTarefa catt : categoriasTarefas) {
            if(catt.getDescricao() != null && catt.getDescricao().equals(descricao)) {
                return catt;
            }
        } throw new NomeNaoAssociadoException("Não existe nenhuma categoria de tarefa com esse nome.");
    }

    /**
     * Method for listing task categories.
     * @return 
     */
    public ArrayList<CategoriaTarefa> listarCategoriasTarefa(){
        return  new ArrayList<>(this.categoriasTarefas);
    }

}
