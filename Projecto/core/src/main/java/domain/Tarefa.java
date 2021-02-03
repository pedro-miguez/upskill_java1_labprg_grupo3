package domain;

import java.io.Serializable;

public class Tarefa implements Serializable {



    private CodigoUnico codigoUnico;
    private String designacao;
    private String descricaoInformal;
    private String descricaoTecnica;
    private int duracaoHoras;
    private float custo;
    private CategoriaTarefa categoria;
    private Organizacao organizacao;

    /**
     * Construtor de uma tarefa com os seguintes parâmetros:
     *  @param codigoUnico
     * @param designacao
     * @param descricaoInformal
     * @param descricaoTecnica
     * @param duracaoHoras
     * @param custo
     * @param categoria
     * @param organizacao
     */
    public Tarefa(CodigoUnico codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                  int duracaoHoras, float custo, CategoriaTarefa categoria, Organizacao organizacao) {
        this.codigoUnico = codigoUnico;
        this.organizacao = organizacao;
        setDesignacao(designacao);
        setDescricaoInformal(descricaoInformal);
        setDescricaoTecnica(descricaoTecnica);
        setDuracaoHoras(duracaoHoras);
        setCusto(custo);
        this.categoria = categoria;
    }

    public Organizacao getOrganizacao() {
        return this.organizacao;
    }

    public CodigoUnico getCodigoUnico() {
        return this.codigoUnico;
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
        return descricaoInformal;
    }

    /**
     * Valida uma descrição Informal
     *
     * @param descricaoInformal
     */
    private void setDescricaoInformal(String descricaoInformal) {
        if (descricaoInformal == null || descricaoInformal.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição informal inválida!! A descrição não pode estar vazia.");
        } else
            this.descricaoInformal = descricaoInformal;
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
        return String.format("Código Único: %s; Designacao: %s; Descrição Informal: %s; Descrição Técnica: %s; " +
                "Estivativa de Duração: %d horas; Estimativa de Custo: %.2f €; Categoria Tarefa: %s.", this.codigoUnico.toString(),
                this.designacao, this.descricaoInformal, this.descricaoTecnica, this.duracaoHoras, this.custo,
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

        if (!getCodigoUnico().equals(tarefa.getCodigoUnico())) return false;
        return getOrganizacao().equals(tarefa.getOrganizacao());
    }

}
