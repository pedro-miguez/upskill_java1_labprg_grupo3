package persistence;

import domain.AreaAtividade;
import domain.CodigoUnico;
import exceptions.CodigoNaoAssociadoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for creating a repository to store information about
 * activity areas.
 */
public class RepositorioAreaAtividade implements Serializable {

    private static RepositorioAreaAtividade instance;

    private List<AreaAtividade> areasAdicionadas;

    /**
     * Activity areas that will be added to the repository.
     */
    private RepositorioAreaAtividade(){
        areasAdicionadas = new ArrayList<>();
    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioAreaAtividade getInstance(){
        if (instance == null){
            instance = new RepositorioAreaAtividade();
        }
        return instance;
    }

    /**
     * Boolean method that checks if an area of ​​activity exists in the repository, 
     * otherwise it is added to it.
     * @param areaAtividade
     * @return 
     */
    public boolean addAreaAtividade(AreaAtividade areaAtividade){
        if(this.areasAdicionadas.contains(areaAtividade)){
            return false;
        } else {
            this.areasAdicionadas.add(areaAtividade);
            return true;
        }
    }

    /**
     * Method for obtaining an area of ​​activity using its unique code.
     * @param codigoUnico
     * @return 
     */
    public AreaAtividade getAreaAtividadeByCodUnico(CodigoUnico codigoUnico){
        for (AreaAtividade a: areasAdicionadas) {
            if(a.getCodigoUnico() != null && a.getCodigoUnico().equals(codigoUnico)) {
                return a;
            }
        }
            throw new CodigoNaoAssociadoException("Não existe nenhuma área de atividade com esse código único.");
    }

    /**
     * Method to list areas of activities.
     * @return 
     */
    public ArrayList<AreaAtividade> listarAreaAtividade(){
        return  new ArrayList<>(this.areasAdicionadas);
    }


    public AreaAtividade criarAreaAtividade(String codigoUnico, String descricao, String descricaoDetalhada) {
        return new AreaAtividade(new CodigoUnico(codigoUnico), descricao, descricaoDetalhada);
    }

    /**
     * Method to check if two objects (in this case, areas of activity) are the same.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositorioAreaAtividade)) return false;
        RepositorioAreaAtividade that = (RepositorioAreaAtividade) o;
        return areasAdicionadas.equals(that.areasAdicionadas);
    }

}
