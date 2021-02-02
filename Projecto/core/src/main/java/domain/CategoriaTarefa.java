package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriaTarefa implements Serializable {

    private CodigoUnico codigoUnico;
    private AreaAtividade areaAtividade;
    private String descricao;
    private List<CompetenciaTecnica> competenciasTecnicas;

    public CategoriaTarefa(CodigoUnico codigoUnico, AreaAtividade areaAtividade, String descricao, List<CompetenciaTecnica> competenciasTecnicas) {
        this.codigoUnico = codigoUnico;
        setAreaAtividade(areaAtividade);
        setDescricao(descricao);
        this.competenciasTecnicas = new ArrayList<CompetenciaTecnica>();
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

    public AreaAtividade getAreaAtividade() {
        return areaAtividade;
    }

    private void setAreaAtividade(AreaAtividade areaAtividade) {
        if (areaAtividade != null) {
            this.areaAtividade = areaAtividade;
        }
    }

    public CodigoUnico getCodigoUnico() {
        return codigoUnico;
    }

    public List<CompetenciaTecnica> getCompetenciasTecnicas() {
        return competenciasTecnicas;
    }

    public void setCompetenciasTecnicas(List<CompetenciaTecnica> competenciasTecnicas) {
        this.competenciasTecnicas = competenciasTecnicas;
    }

    @Override
    public String toString() {
        return String.format("Código Unico: %s; Area de Actividade: %s Descrição: %s; Descrição Detalhada: %s",
                this.codigoUnico, this.areaAtividade, this.descricao, this.competenciasTecnicas.);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaTarefa)) return false;
        CategoriaTarefa that = (CategoriaTarefa) o;
        return getCodigoUnico().equals(that.getCodigoUnico()) && this.areaAtividade.equals(that.areaAtividade);
    }







}
