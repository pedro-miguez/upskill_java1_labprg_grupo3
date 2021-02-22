package domain;

public class TipoRegimento {

    private String designacao;
    private String regras;

    public TipoRegimento(String designacao, String regras) {
        this.designacao = designacao;
        this.regras = regras;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String descricao) {
        this.designacao = descricao;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

    @Override
    public String toString() {
        return String.format("Designação: %s; Regras: %s", designacao, regras);
    }
}
