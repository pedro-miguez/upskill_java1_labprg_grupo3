package domain;


public class AreaAtividade {

    private CodigoUnico codigoUnico;
    private String descricao;
    private String descDetalhada;

    public AreaAtividade(CodigoUnico codigoUnico, String descricao, String descDetalhada) {
        this.codigoUnico = codigoUnico;
        setDescricao(descricao);
        setDescDetalhada(descDetalhada);
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
        return String.format("Código Unico: %s; Descrição: %s; Descrição Detalhada: %s", this.codigoUnico, this.descricao, this.descDetalhada);
    }

}
