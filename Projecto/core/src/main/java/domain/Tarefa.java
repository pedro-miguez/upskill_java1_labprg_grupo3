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

    /**
     * Construtor de uma tarefa com os seguintes parâmetros:
     *
     * @param referencia
     * @param designacao
     * @param descricao
     * @param descricaoTecnica
     * @param duracaoHoras
     * @param custo
     * @param categoria
     */
    public Tarefa(String referencia, String designacao, String descricao, String descricaoTecnica, int duracaoHoras, float custo, CategoriaTarefa categoria) {
        setReferencia(referencia);
        setDesignacao(designacao);
        setDescricaoInformal(descricao);
        setDescricaoTecnica(descricaoTecnica);
        setDuracaoHoras(duracaoHoras);
        setCusto(custo);
        this.categoria = categoria;
    }

    /**
     * Método para obtenção da referência de uma tarefa
     *
     * @return
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Valida uma referência
     *
     * @param referencia
     */
    private void setReferencia(String referencia) {
        if (referencia.matches("^([a-zA-Z]){3}(-\\d{2})?$")) {
            this.referencia = referencia;
        } else
            throw new IllegalArgumentException("Referência inválida introduzida. Deve ser como o seguinte exemplo, 'TAR-01'.");

    }

    /**
     * Método para obtenção de uma designação
     *
     * @return
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Valida uma designação
     *
     * @param designacao
     */
    private void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Designação inválida!! A designação não pode estar vazia.");
        } else
            this.designacao = designacao;
    }

    /**
     * Método para obtenção de uma descrição informal
     *
     * @return
     */
    public String getDescricaoInformal() {
        return descricao;
    }

    /**
     * Valida uma descrição Informal
     *
     * @param descricao
     */
    private void setDescricaoInformal(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição informal inválida!! A descrição não pode estar vazia.");
        } else
            this.descricao = descricao;
    }

    /**
     * Método para obtenção de uma descrição técnica
     *
     * @return
     */
    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    /**
     * Valida uma descrição Técnica
     *
     * @param descricaoTecnica
     */
    private void setDescricaoTecnica(String descricaoTecnica) {
        if (descricaoTecnica == null || descricaoTecnica.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição técnica inválida!! A descrição não pode estar vazia.");
        } else
            this.descricaoTecnica = descricaoTecnica;
    }

    /**
     * Método para obtenção de uma duração de horas estimada
     *
     * @return
     */
    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    /**
     * Valida a duração estimada
     *
     * @param duracaoHoras
     */
    private void setDuracaoHoras(int duracaoHoras) {
        if (duracaoHoras > 0) {
            this.duracaoHoras = duracaoHoras;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }

    /**
     * Método para obtenção de um custo estimado
     *
     * @return
     */
    public float getCusto() {
        return custo;
    }

    /**
     * Valida o custo estimado
     *
     * @param custo
     */
    private void setCusto(float custo) {
        if (custo > 0) {
            this.custo = custo;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }

    /**
     * Método para obtenção da Categoria de Tarefa
     *
     * @return
     */
    public CategoriaTarefa getCategoria() {
        return categoria;
    }

    /**
     * Representação em formato String do objeto criado
     *
     * @return
     */
    public String toString(){
        return String.format("Referencia: %s; Designacao: %s; Descrição Informal: %s; Descrição Técnica: %s; " +
                "Estivativa de Duração: %d horas; Estimativa de Custo: %.2f €; Categoria Tarefa: %s.", this.referencia,
                this.designacao, this.descricao, this.descricaoTecnica, this.duracaoHoras, this.custo,
                this.categoria.getDescricao());
    }

    /**
     * Método para verificar se dois objetos são iguais
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarefa)) return false;

        Tarefa tarefa = (Tarefa) o;

        if (getDuracaoHoras() != tarefa.getDuracaoHoras()) return false;
        if (Float.compare(tarefa.getCusto(), getCusto()) != 0) return false;
        if (!getReferencia().equals(tarefa.getReferencia())) return false;
        if (!getDesignacao().equals(tarefa.getDesignacao())) return false;
        if (!getDescricaoInformal().equals(tarefa.getDescricaoInformal())) return false;
        if (!getDescricaoTecnica().equals(tarefa.getDescricaoTecnica())) return false;
        return getCategoria().equals(tarefa.getCategoria());
    }

}
