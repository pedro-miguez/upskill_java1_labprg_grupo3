package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class implements the tools to create new phone numbers (as Telefone). It has implementations to get a string
 * representation through the toString() method of the object.
 */
public class Telefone implements Serializable {

    private int number;

    /**
     * Instantiates a phone number (as Telefone) with set parameter.
     *
     * @param number the number
     */
    public Telefone(int number){
        setTelefone(number);
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number.
     */
    public int getTelefone() {
        return this.number;
    }

    private void setTelefone(int number) {
        if (number < 100000000 || number > 999999999) {
            throw new IllegalArgumentException("Número de telefone inválido");
        } else {
            this.number = number;
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("%d", number);
    }

    /**
     * Compares this object with specified object for order.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return number == telefone.number;
    }

}
