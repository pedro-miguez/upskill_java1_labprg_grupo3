package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class implements the tool for creating new addresses (as EnderecoPostal)
 * with specific parameters such as street data (local), city and country (optional) 
 * data (localidade) and a post code. It has implementations to get all
 * parameters and a toString() method that returns a representation of the object.
 */
public class EnderecoPostal implements Serializable {

    private String local;
    private String localidade;
    private String codPostal;


    /**
     * Instantiates a new address (as EnderecoPostal) with set parameters.
     *
     * @param local      the street data
     * @param localidade the city and country (optional) data
     * @param codPostal  the post code
     */

    public EnderecoPostal(String local, String localidade, String codPostal) {
        setLocal(local);
        setLocalidade(localidade);
        setCodPostal(codPostal);
    }

    /**
     * Gets the street data of the object.
     *
     * @return the street data of the object.
     */

    public String getLocal() {
        return local;
    }

    /**
     * Gets city and country (optional) data of the object.
     *
     * @return the city and country (optional) data of the object.
     */
    
    public String getLocalidade() {
        return localidade;
    }

    /**
     * Gets the post code of the object.
     *
     * @return the post code of the object.
     */
    public String getCodPostal() {
        return codPostal;
    }

    private void setLocal(String local) {
        this.local = local;
    }

    private void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    private void setCodPostal(String codPostal) {
        if (codPostal.matches("^\\d{4}(-\\d{3})?$")) {
            this.codPostal = codPostal;
        } else {
            throw new IllegalArgumentException("O código postal introduzido não é valido");
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("%s, %s, com o código-postal %s", getLocal(), getLocalidade(), getCodPostal());
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
        EnderecoPostal that = (EnderecoPostal) o;
        return Objects.equals(getLocal(), that.getLocal()) &&
                Objects.equals(getLocalidade(), that.getLocalidade()) &&
                Objects.equals(getCodPostal(), that.getCodPostal());
    }

}
