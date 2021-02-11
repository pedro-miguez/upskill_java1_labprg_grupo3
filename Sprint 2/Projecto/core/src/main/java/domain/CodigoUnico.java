package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class represents the tool for creating and condition new unique references either when instantiating objects of type Activity
 * Area as for objects of type Technical Skill.
 */
public class CodigoUnico implements Serializable {

    private String codigoUnico;

    /**
     * Instantiates a new unique reference.
     *
     * @param codigoUnico as unique reference.
     */
    public CodigoUnico(String codigoUnico) {
        setCodigoUnico(codigoUnico);
    }

    /**
     * Gets unique reference.
     *
     * @return the unique reference.
     */
    public String getCodigoUnico() {
        return codigoUnico;
    }

    private void setCodigoUnico(String codigoUnico) {
        if (codigoUnico.matches("^([a-zA-Z]){3}(-\\d{2})?$")) {
            this.codigoUnico = codigoUnico;
        } else {
            throw new IllegalArgumentException("Código único inválido, deve ser como o seguinte exemplo, 'PRG-01'.");
        }
    }

    /**
     * Returns a string representation of the object unique reference and its attributes.
     *
     * The result should be a concise but informative representation that is easy
     * for a person to read.
     * It is overriden by all subclasses.
     *
     * @return  a string representation of the object.
     *
     */
    public String toString(){
        return String.format("%s", this.codigoUnico);
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
        if (o == null || getClass() != o.getClass()) return false;
        CodigoUnico that = (CodigoUnico) o;
        return getCodigoUnico().equals(that.getCodigoUnico());
    }

}
