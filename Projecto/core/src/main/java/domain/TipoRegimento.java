package domain;

/**
 * Current class implements the tools for creating new type of regiment.
 * 
 * @author Grupo 3
 */
public class TipoRegimento {

    private String designacao;
    private String regras;

    /**
     * Instantiates a new type of regiment.
     * 
     * @param designacao
     * @param regras 
     */
    public TipoRegimento(String designacao, String regras) {
        this.designacao = designacao;
        this.regras = regras;
    }

    /**
     * Gets the designation.
     * @return designacao
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Sets the designation.
     * @param descricao 
     */
    public void setDesignacao(String descricao) {
        this.designacao = descricao;
    }

    /**
     * Gets the rules.
     * @return regras
     */
    public String getRegras() {
        return regras;
    }

    /**
     * Sets the rules.
     * @param regras 
     */
    public void setRegras(String regras) {
        this.regras = regras;
    }

    
    /**
     * Returns a string representation with very concise but precise information 
     * about the object and its attributes.
     *
     * @return a string representation of the object (type of regiment).
     *
     */
    @Override
    public String toString() {
        return designacao;
    }
}
