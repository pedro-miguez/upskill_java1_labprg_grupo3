package domain;

public class GrauProficiencia {

    private int nivel;
    private String designacao;

    public GrauProficiencia(int nivel, String designacao) {
        setNivel(nivel);
        setDesignacao(designacao);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel > 0) {
            this.nivel = nivel;
        } else
            throw new IllegalArgumentException("Nível de grau de proficiência inválido.");
    }

    public String getDesginacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Designação inválida!! A designação não pode estar vazia.");
        } else
            this.designacao = designacao;
    }

    @Override
    public String toString() {
        return String.format("Nível: %d Designação: %s\n", this.nivel, this.designacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrauProficiencia)) return false;

        GrauProficiencia that = (GrauProficiencia) o;

        if (getNivel() != that.getNivel()) return false;
        return designacao.equals(that.designacao);
    }

}
