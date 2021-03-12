package com.company.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class implements the tools to create new tax id numbers (as NIF). 
 * It has implementations to get a string representation through the toString() 
 * method of the object.
 */
public class NIF implements Serializable {

    private int nif;

    /**
     * Instantiates a new tax id number (as NIF) with set parameter.
     *
     * @param nif the nif
     */
    public NIF(int nif) {
        setNIF(nif);
    }

    private void setNIF(int nif) {

        if (nif < 100000000 || nif > 999999999) {
            throw new IllegalArgumentException("Nif invalido");
        } else {
            this.nif = nif;
        }
    }

    /**
     * Get the tax id number.
     *
     * @return the tax id number.
     */
    public int getNif(){
        return this.nif;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("%d", nif);
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
        NIF nif1 = (NIF) o;
        return getNif() == nif1.getNif();
    }

}
