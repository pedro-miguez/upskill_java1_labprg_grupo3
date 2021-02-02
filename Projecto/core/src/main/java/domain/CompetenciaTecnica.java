package domain;

import java.io.Serializable;

public class CompetenciaTecnica implements Serializable {

    private CodigoUnico codigoUnico;
    private AreaAtividade areaAtividade;
    private String descricao;
    private String descDetalhada;

    public CompetenciaTecnica(CodigoUnico codigoUnico, AreaAtividade areaAtividade, String descricao, String descDetalhada) {
        this.codigoUnico = codigoUnico;
        setDescricao(descricao);
        setDescDetalhada(descDetalhada);
        setAreaAtividade(areaAtividade);
    }

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

    public String getDescDetalhada() {
        return descDetalhada;
    }

    private void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida!! A descrição não pode estar vazia.");
        } else {
            this.descDetalhada = descDetalhada;
        }
    }

    public CodigoUnico getCodigoUnico() {
        return codigoUnico;
    }

    @Override
    public String toString() {
        return String.format("Código Unico: %s; Area de Actividade: %s Descrição: %s; Descrição Detalhada: %s",
                this.codigoUnico, this.areaAtividade, this.descricao, this.descDetalhada);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetenciaTecnica)) return false;
        CompetenciaTecnica that = (CompetenciaTecnica) o;
        return getCodigoUnico().equals(that.getCodigoUnico()) && this.areaAtividade.equals(that.areaAtividade);
    }

    public AreaAtividade getAreaAtividade() {
        return areaAtividade;
    }

    private void setAreaAtividade(AreaAtividade areaAtividade) {
        if (areaAtividade != null) {
            this.areaAtividade = areaAtividade;
        }
    }
}
