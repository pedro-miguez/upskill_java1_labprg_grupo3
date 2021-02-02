package domain;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private String referencia;
    private String designacao;
    private String descricao;
    private String descricaoTecnica;
    private int duracaoHoras;
    private float custo;
    private CategoriaTarefa categoria;

    public Tarefa(String referencia, String designacao, String descricao, String descricaoTecnica, int duracaoHoras, float custo, CategoriaTarefa categoria) {
        setReferencia(referencia);
        setDesignacao(designacao);
        setDescricao(descricao);
        setDescricaoTecnica(descricaoTecnica);
        setDuracaoHoras(duracaoHoras);
        setCusto(custo);
        this.categoria = categoria;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        if (referencia.matches("^([a-zA-Z]){3}(-\\d{2})?$")) {
            this.referencia = referencia;
        } else
            throw new IllegalArgumentException("Referência inválida introduzida. Deve ser como o seguinte exemplo, 'TAR-01'.");

    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Designação inválida!! A designação não pode estar vazia.");
        } else
            this.designacao = designacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição informal inválida!! A descrição não pode estar vazia.");
        } else
            this.descricao = descricao;
    }

    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    public void setDescricaoTecnica(String descricaoTecnica) {
        if (descricaoTecnica == null || descricaoTecnica.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição técnica inválida!! A descrição não pode estar vazia.");
        } else
            this.descricaoTecnica = descricaoTecnica;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        if (duracaoHoras > 0) {
            this.duracaoHoras = duracaoHoras;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        if (custo > 0) {
            this.custo = custo;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }

    public CategoriaTarefa getCategoria() {
        return categoria;
    }

    public String toString(){
        return String.format("Referencia: %d; Designacao: %d; Descrição Informal: %s; Descrição Técnica: %s; " +
                "Estivativa de Duração: %d horas; Estimativa de Custo: %.2f €; Categoria Tarefa: %s.", this.referencia,
                this.designacao, this.descricao, this.descricaoTecnica, this.duracaoHoras, this.custo,
                this.categoria.getDescricao());
    }


}
