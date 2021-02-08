package domain;

import java.io.Serializable;

/**
 * Current class enables to create new technical competences, wich may or not be mandatory - defined in the CaracterizacaoCompTec Class.
 * They are represented by a unique code, a brief and a detailed description and make part of an activity area. The Platform administrative
 * is responsible for setting the ones required for each task.
 */
public class CompetenciaTecnica implements Serializable {

    private CodigoUnico codigoUnico;
    private AreaAtividade areaAtividade;
    private String descricao;
    private String descDetalhada;

    /**
     * Instantiates a new technical competence.
     *
     * @param codigoUnico   as unique code
     * @param areaAtividade as activity area
     * @param descricao     as description
     * @param descDetalhada as detailed description
     */
    public CompetenciaTecnica(CodigoUnico codigoUnico, AreaAtividade areaAtividade, String descricao, String descDetalhada) {
        this.codigoUnico = codigoUnico;
        setAreaAtividade(areaAtividade);
        setDescricao(descricao);
        setDescDetalhada(descDetalhada);
    }

    /**
     * Gets description.
     *
     * @return the description
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
     * Gets detailed description
     *
     * @return the detailed description
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }

    private void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida!! A descrição detalhada não pode estar vazia.");
        } else {
            this.descDetalhada = descDetalhada;
        }
    }

    /**
     * Gets unique code
     *
     * @return the unique code
     */
    public CodigoUnico getCodigoUnico() {
        return codigoUnico;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s",
                 this.codigoUnico, this.descricao);
    }

    public String toStringCompleto() {
        return String.format("Código Único: %s%nArea de Atividade: %s%nDescrição breve: %s%nDescrição detalhada: %s",
                this.codigoUnico, this.areaAtividade.getDescricao(), this.descricao, this.descDetalhada);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetenciaTecnica)) return false;
        CompetenciaTecnica that = (CompetenciaTecnica) o;
        return getCodigoUnico().equals(that.getCodigoUnico()) && this.areaAtividade.equals(that.areaAtividade);
    }

    /**
     * Gets activity area
     *
     * @return the activity area
     */
    public AreaAtividade getAreaAtividade() {
        return areaAtividade;
    }

    private void setAreaAtividade(AreaAtividade areaAtividade) {
        if (areaAtividade != null) {
            this.areaAtividade = areaAtividade;
        } else {
            throw new IllegalArgumentException("Area de atividade inválida para criação de competência técnica");
        }
    }
}
