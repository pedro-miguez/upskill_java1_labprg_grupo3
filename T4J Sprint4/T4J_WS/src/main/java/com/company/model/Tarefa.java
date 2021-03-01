package com.company.model;

import java.io.Serializable;

/**
 * Current class implements the tools for creating new tasks (as Tarefa) with 
 * specific parameters such as unique code (codigoUnico), designation (designacao), 
 * informal description (descricaoInformal), technical description (descricaoTecnica), 
 * estimated time to complete the task (duracaoHoras), estimated cost to perform 
 * the task (custo), the task category it falls under (categoria) and the organization 
 * that belongs (organizacao).
 * It has implementations to get all these parameters through toString() method.
 */
public class Tarefa implements Serializable {

    private CodigoUnico codigoUnico;
    private String designacao;
    private String descricaoInformal;
    private String descricaoTecnica;
    private int duracaoDias;
    private float custo;
    private CategoriaTarefa categoria;
    private Organizacao organizacao;

    /**
     * Instantiates a new task (as Tarefa) with set parameters.
     *
     * @param codigoUnico       the unique code of the task
     * @param designacao        the designation of the task
     * @param descricaoInformal the informal description of the task
     * @param descricaoTecnica  the technical description of the task
     * @param duracaoDias      the estimated time to complete the task
     * @param custo             the estimated cost to perform the task
     * @param categoria         the task category it falls under
     * @param organizacao       the the organization that belongs
     */
    
    public Tarefa(CodigoUnico codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                  int duracaoDias, float custo, CategoriaTarefa categoria, Organizacao organizacao) {
        this.codigoUnico = codigoUnico;
        this.organizacao = organizacao;
        setDesignacao(designacao);
        setDescricaoInformal(descricaoInformal);
        setDescricaoTecnica(descricaoTecnica);
        setDuracaoDias(duracaoDias);
        setCusto(custo);
        this.categoria = categoria;
    }

    /**
     * Gets organization that belongs.
     *
     * @return the organization that belongs.
     */
    public Organizacao getOrganizacao() {
        return this.organizacao;
    }

    /**
     * Gets unique code of the task.
     *
     * @return the unique code of the task.
     */
    public CodigoUnico getCodigoUnico() {
        return this.codigoUnico;
    }

    /**
     * Gets designation of the task.
     *
     * @return the designation of the task.
     */
    public String getDesignacao() {
        return designacao;
    }

    private void setDesignacao(String designacao) {
        if (designacao == null || designacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Designação inválida!! A designação não pode estar vazia.");
        } else
            this.designacao = designacao;
    }

    /**
     * Gets the informal description of the task.
     *
     * @return the informal description of the task.
     */
    public String getDescricaoInformal() {
        return descricaoInformal;
    }

    private void setDescricaoInformal(String descricaoInformal) {
        if (descricaoInformal == null || descricaoInformal.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição informal inválida!! A descrição não pode estar vazia.");
        } else
            this.descricaoInformal = descricaoInformal;
    }

    /**
     * Gets the technical description of the task.
     *
     * @return the technical description of the task.
     */
    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }


    private void setDescricaoTecnica(String descricaoTecnica) {
        if (descricaoTecnica == null || descricaoTecnica.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição técnica inválida!! A descrição não pode estar vazia.");
        } else
            this.descricaoTecnica = descricaoTecnica;
    }

    /**
     * Gets the estimated time to complete the task.
     *
     * @return the estimated time to complete the task.
     */
    public int getDuracaoDias() {
        return duracaoDias;
    }

    private void setDuracaoDias(int duracaoDias) {
        if (duracaoDias > 0) {
            this.duracaoDias = duracaoDias;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }

    /**
     * Gets the estimated cost to perform the task.
     *
     * @return the estimated cost to perform the task.
     */
    public float getCusto() {
        return custo;
    }

    private void setCusto(float custo) {
        if (custo > 0) {
            this.custo = custo;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }

    /**
     * Gets the task category it falls under.
     *
     * @return the task category it falls under.
     */
    public CategoriaTarefa getCategoria() {
        return categoria;
    }

    /**
     * Returns a string representation with very concise but precise information about the object and its attributes.
     *
     * @return a string representation of the object (task).
     *
     */
    public String toString(){
        return String.format("Código Único: %s%nDesignacao: %s%nDescrição Informal: %s%nDescrição Técnica: %s" +
                "%nEstimativa de Duração: %d dias%nEstimativa de Custo: %.2f POT's%nCategoria Tarefa: %s", this.codigoUnico.toString(),
                this.designacao, this.descricaoInformal, this.descricaoTecnica, this.duracaoDias, this.custo,
                this.categoria.getDescricao());
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
        if (!(o instanceof Tarefa)) return false;

        Tarefa tarefa = (Tarefa) o;

        if (!getCodigoUnico().equals(tarefa.getCodigoUnico())) return false;
        return getOrganizacao().equals(tarefa.getOrganizacao());
    }

}
