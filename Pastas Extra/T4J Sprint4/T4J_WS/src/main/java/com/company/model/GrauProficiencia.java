package com.company.model;

/**
 * Current class represents the tool for creating new proficiency degrees.
 * 
 * @author Grupo 3
 */
public class GrauProficiencia {

    private int nivel;
    private String designacao;

    /**
     * Instantiates a new proficiency degree.
     * @param nivel
     * @param designacao 
     */
    public GrauProficiencia(int nivel, String designacao) {
        setNivel(nivel);
        setDesignacao(designacao);
    }

    /**
     * Gets the level.
     * @return nivel
     */
    public int getNivel() {
        return nivel;
    }

    private void setNivel(int nivel) {
        if (nivel > 0) {
            this.nivel = nivel;
        } else
            throw new IllegalArgumentException("Nível de grau de proficiência inválido.");
    }

    /**
     * Gets the designation.
     * @return designacao
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
     * Returns a string representation of the object proficiency degree.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Nível: %d - %s\n", this.nivel, this.designacao);
    }

    
    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrauProficiencia)) return false;

        GrauProficiencia that = (GrauProficiencia) o;

        if (getNivel() != that.getNivel()) return false;
        return designacao.equals(that.designacao);
    }

}
