package domain;

import java.util.Objects;

/**
 * Current class represents the tool for creating new rating of each of the 
 * applications.
 * 
 * @author Grupo 3
 */
public class Classificacao {


    private Candidatura candidatura;
    int lugar;

    /**
     * Instantiates a new rating.
     * @param candidatura
     * @param lugar 
     */
    public Classificacao(Candidatura candidatura, int lugar) {

        setCandidatura(candidatura);
        this.lugar = lugar;
    }

    /**
     * Gets the place.
     * 
     * @return lugar
     */
    public int getLugar() {
        return lugar;
    }

    /**
     * Gets the candidatura.
     *
     * @return candidatura
     */

    public Candidatura getCandidatura() {
        return candidatura;
    }


    /**
     * Sets the candidatura.
     *
     * @param candidatura
     */
    public void setCandidatura(Candidatura candidatura) {
        if (candidatura != null)
            this.candidatura = candidatura;
        else
            throw new IllegalArgumentException("Candidatura Inválida");
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
        if (!(o instanceof Classificacao)) return false;
        Classificacao that = (Classificacao) o;
        return getLugar() == that.getLugar() && Objects.equals(getCandidatura(), that.getCandidatura());
    }

}
