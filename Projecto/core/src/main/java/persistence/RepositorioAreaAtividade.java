package persistence;

import domain.AreaAtividade;
import domain.CodigoUnico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * The type RepositorioAreaAtividade.
 */
public class RepositorioAreaAtividade implements Serializable {

    private static RepositorioAreaAtividade instance;

    private List<AreaAtividade> areasAdicionadas;

    /**
     * Áreas de atividade que irão ser adicionadas no repositório.
     */
    private RepositorioAreaAtividade(){
        areasAdicionadas = new ArrayList<>();
    }

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioAreaAtividade getInstance(){
        if (instance == null){
            instance = new RepositorioAreaAtividade();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se uma área de atividade existe no repositório,
     * caso contrário é adicionada ao mesmo.
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
     * Método para obtenção de uma área de atividade através do seu código único.
     * @param codigoUnico
     * @return 
     */
    public AreaAtividade getAreaAtividadeByCodUnico(CodigoUnico codigoUnico){
        for (AreaAtividade a: areasAdicionadas) {
            if(a.getCodigoUnico() != null && a.getCodigoUnico().equals(codigoUnico)) {
                return a;
            }
        }
            throw new IllegalArgumentException("Não existe nenhuma área de atividade com esse código único.");
    }

    /**
     * Método para listar áreas de atividades.
     * @return 
     */
    public ArrayList<AreaAtividade> listarAreaAtividade(){
        return  new ArrayList<>(this.areasAdicionadas);
    }

    /**
     * Método para verificar se dois objectos (neste caso, áreas de atividade) 
     * são iguais.
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
