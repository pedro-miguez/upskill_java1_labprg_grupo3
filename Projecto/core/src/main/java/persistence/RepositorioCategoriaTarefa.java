package persistence;

import domain.AreaAtividade;
import domain.CategoriaTarefa;
import domain.CodigoUnico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The type RepositorioCategoriaTarefa.
 */
public class RepositorioCategoriaTarefa implements Serializable {

    private static RepositorioCategoriaTarefa instance;

    private List<CategoriaTarefa> categoriasTarefas;

    /**
     * Categorias de tarefas que irão ser adicionadas no repositório.
     */
    private RepositorioCategoriaTarefa(){
        categoriasTarefas = new ArrayList<>();
    }

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioCategoriaTarefa getInstance(){
        if (instance == null){
            instance = new RepositorioCategoriaTarefa();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se uma categoria de tarefa existe no repositório,
     * caso contrário é adicionada ao mesmo.
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
     * Método para obtenção de uma categoria de tarefa através da sua descrição.
     * @param descricao
     * @return 
     */
    public CategoriaTarefa getCategoriaTarefaByDescricao(String descricao){
        for (CategoriaTarefa catt : categoriasTarefas) {
            if(catt.getDescricao() != null && catt.getDescricao().equals(descricao)) {
                return catt;
            }
        } throw new IllegalArgumentException("Não existe nenhuma categoria de tarefa com esse nome.");
    }

    /**
     * Método para listar categorias de tarefas.
     * @return 
     */
    public ArrayList<CategoriaTarefa> listarCategoriasTarefa(){
        return  new ArrayList<>(this.categoriasTarefas);
    }

}
