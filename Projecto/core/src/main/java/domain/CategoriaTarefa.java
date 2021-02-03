package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Categoria tarefa.
 */
public class CategoriaTarefa implements Serializable {

    private AreaAtividade areaAtividade;
    private String descricao;
    private List<CaracterizacaoCompTec> competenciasTecnicas;

    /**
     * Instantiates a new Categoria tarefa.
     *
     * @param areaAtividade        the area atividade
     * @param descricao            the descricao
     * @param competenciasTecnicas the competencias tecnicas
     */
    public CategoriaTarefa(AreaAtividade areaAtividade, String descricao, List<CaracterizacaoCompTec> competenciasTecnicas) {
        setAreaAtividade(areaAtividade);
        setDescricao(descricao);
        this.competenciasTecnicas = new ArrayList<>();
    }

    /**
     * Gets descricao.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    private void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida!! A descrição não pode estar vazia.");
        } else {
            this.descricao = descricao;
        }
    }

    /**
     * Gets area atividade.
     *
     * @return the area atividade
     */
    public AreaAtividade getAreaAtividade() {
        return areaAtividade;
    }

    private void setAreaAtividade(AreaAtividade areaAtividade) {
        if (areaAtividade != null) {
            this.areaAtividade = areaAtividade;
        }else{
            throw new IllegalArgumentException("Area de atividade ja existente!!!.");
        }
    }

    /**
     * Gets competencias tecnicas.
     *
     * @return the competencias tecnicas
     */
    public List<CaracterizacaoCompTec> getCompetenciasTecnicas() {
        return new ArrayList<CaracterizacaoCompTec>(this.competenciasTecnicas);
    }



    @Override
    public String toString() {
        return String.format("Descrição: %s; Area de Actividade: %s",
                this.descricao, this.areaAtividade.getDescricao());
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaTarefa)) return false;
        CategoriaTarefa that = (CategoriaTarefa) o;
        return this.descricao.equals(that.descricao) && this.areaAtividade.equals(that.areaAtividade);
    }

}
