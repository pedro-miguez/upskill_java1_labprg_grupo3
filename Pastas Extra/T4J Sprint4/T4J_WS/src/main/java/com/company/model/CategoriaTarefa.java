package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is funneled by the activity area class and hosts a list of 
 * technical competences.It is identified by a description. 
 * It is also handled by the Platform administrative.
 */
public class CategoriaTarefa implements Serializable {

    private AreaAtividade areaAtividade;
    private String descricao;
    private List<CaracterizacaoCompTec> competenciasTecnicas;

    /**
     * Instantiates a new task category.
     *
     * @param areaAtividade        as activity area.
     * @param descricao            as description.
     * @param competenciasTecnicas as a list of technical competences.
     */
    public CategoriaTarefa(AreaAtividade areaAtividade, String descricao, 
                            List<CaracterizacaoCompTec> competenciasTecnicas) {
        
        setDescricao(descricao);
        setAreaAtividade(areaAtividade);
        this.competenciasTecnicas = new ArrayList<>(competenciasTecnicas);
    }

    /**
     * Gets the description.
     *
     * @return the description.
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
     * Gets activity area.
     *
     * @return the activity area.
     */
    public AreaAtividade getAreaAtividade() {
        return areaAtividade;
    }

    private void setAreaAtividade(AreaAtividade areaAtividade) {
        if (areaAtividade != null) {
            this.areaAtividade = areaAtividade;
        }else{
            throw new IllegalArgumentException("Area de atividade inválida");
        }
    }

    /**
     * Gets list of technical competences.
     *
     * @return list of technical competences.
     */
    public ArrayList<CaracterizacaoCompTec> getCompetenciasTecnicas() {
        return new ArrayList<>(this.competenciasTecnicas);
    }

    /**
     * Returns a string representation of the object task category and its attributes.
     *
     * The result should be a concise but informative representation that is easy
     * for a person to read.
     * It is overriden by all subclasses.
     *
     * @return  a string representation of the object.
     *
     */
    @Override
    public String toString() {
        return this.descricao + " - " + this.getAreaAtividade().getDescricao();
        }

    public String toStringCompleto() {
        StringBuilder s = new StringBuilder();

        for (CaracterizacaoCompTec cct : this.competenciasTecnicas) {
            s.append("- ").append(cct).append("\n");
        }
        return String.format("Descrição: %s%nArea de Actividade: %s%nCompetências: %n%s",
                this.descricao, this.areaAtividade.getDescricao(), s);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     *
     * @param   o   the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaTarefa)) return false;
        CategoriaTarefa that = (CategoriaTarefa) o;
        return this.descricao.equals(that.descricao) && this.areaAtividade.equals(that.areaAtividade);
    }

}
