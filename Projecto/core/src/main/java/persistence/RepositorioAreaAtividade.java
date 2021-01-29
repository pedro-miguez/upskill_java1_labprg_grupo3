package persistence;

import domain.AreaAtividade;
import domain.CodigoUnico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositorioAreaAtividade {

    private static RepositorioAreaAtividade instance;

    private List<AreaAtividade> areasAdicionadas;

    private RepositorioAreaAtividade(){
        areasAdicionadas = new ArrayList<>();
    }

    public static RepositorioAreaAtividade getInstance(){
        if (instance == null){
            instance = new RepositorioAreaAtividade();
        }
        return instance;
    }

    public boolean addAreaAtividade(AreaAtividade areaAtividade){
        if(this.areasAdicionadas.contains(areaAtividade)){
            return false;
        } else {
            this.areasAdicionadas.add(areaAtividade);
            return true;
        }
    }

    public AreaAtividade getAreaAtividadeByCodUnico(CodigoUnico codigoUnico){
        for (AreaAtividade a: areasAdicionadas) {
            if(a.getCodigoUnico() != null && a.getCodigoUnico().equals(codigoUnico)) {
                return a;
            }
        }
    throw new IllegalArgumentException("Não existe nenhuma área de atividade com esse código único.");
    }

    public ArrayList<AreaAtividade> listarAreaAtividade(){
        return  new ArrayList<>(this.areasAdicionadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositorioAreaAtividade)) return false;
        RepositorioAreaAtividade that = (RepositorioAreaAtividade) o;
        return areasAdicionadas.equals(that.areasAdicionadas);
    }

}
