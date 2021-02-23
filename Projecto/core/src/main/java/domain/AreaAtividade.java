package domain;


import java.io.Serializable;

/**
 * Current class implements the tool for the Platform administrative to create a 
 * new activity area. This will enable the administrative to catalog the technical 
 * competences by area of activity. Each has a unique reference, a short and a 
 * detailed description.
 */
public class AreaAtividade implements Serializable {

    private CodigoUnico codigoUnico;
    private String descricao;
    private String descDetalhada;

    /**
     * Instantiates a new activity area.
     *
     * @param codigoUnico   the codigo unico
     * @param descricao     the descricao
     * @param descDetalhada the desc detalhada
     */
    public AreaAtividade(CodigoUnico codigoUnico, String descricao, String descDetalhada) {
        this.codigoUnico = codigoUnico;
        setDescricao(descricao);
        setDescDetalhada(descDetalhada);
    }

    /**
     * Gets the short description.
     *
     * @return the short description.
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
     * Gets the detailed description.
     *
     * @return the detailed description.
     */
    public String getDescDetalhada() {
        return descDetalhada;
    }

    private void setDescDetalhada(String descDetalhada) {
        if (descDetalhada == null || descDetalhada.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição detalhada inválida!! A descrição não pode estar vazia.");
        } else {
            this.descDetalhada = descDetalhada;
        }
    }

    /**
     * Gets its unique reference.
     *
     * @return the unique reference.
     */
    public CodigoUnico getCodigoUnico() {
        return codigoUnico;
    }

    /**
     * Returns a string representation of the object activity area and its attributes.
     *
     * The result should be a concise but informative representation that is easy
     * for a person to read.
     * It is overriden by all subclasses.
     *
     * @return  a string representation of the object.
     *
     */
    @Override
    public String toString() {
        return String.format("%s - %s", this.getCodigoUnico(), this.descricao);
    }

    public String toStringCompleto() {
        return String.format("Código Unico: %s%nDescrição: %s%nDescrição Detalhada: %s",
                this.getCodigoUnico(), this.descricao, this.descDetalhada);
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
        if (!(o instanceof AreaAtividade)) return false;
        AreaAtividade that = (AreaAtividade) o;
        return getCodigoUnico().equals(that.getCodigoUnico());
    }

}
