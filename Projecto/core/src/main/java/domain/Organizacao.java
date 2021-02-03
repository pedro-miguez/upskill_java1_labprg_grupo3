package domain;

import java.io.Serializable;
import java.util.Objects;


/**
 * Current class implements the tool for creating new organizations (as Organizacao) with specific parameters such as
 * name (nome), tax id number (nif), website, phone number (telefone), email, address and a manager (gestor).
 * It has implementations to get all these parameters through toString() method and a method to set the manager from an
 * organization.
 *
 */
public class Organizacao implements Serializable {

    private String nome;
    private NIF nif;
    private Website website;
    private Telefone telefone;
    private Email email;
    private EnderecoPostal enderecoPostal;
    private Colaborador gestor;

    /**
     * Instantiates a new organization (as Organizacao) with set parameters.
     *
     * @param nome           the name of the Organization
     * @param nif            the nif (Tax ID Number) of the Organization
     * @param website        the website of the Organization
     * @param telefone       the phone number of the Organization
     * @param email          the email of the Organization
     * @param enderecoPostal the adress of the Organization
     */
    public Organizacao(String nome, NIF nif, Website website, Telefone telefone, Email email, EnderecoPostal enderecoPostal){
        setNome(nome);
        setNif(nif);
        setWebsite(website);
        setTelefone(telefone);
        setEmail(email);
        setEnderecoPostal(enderecoPostal);
    }

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException(nome + " é um nome inválido");
        }
        this.nome = nome;
    }

    /**
     * Gets the name of the organization.
     *
     * @return name of the organization as a String.
     */
    public String getNome(){
        return this.nome;
    }

    private void setNif(NIF nif) {
        this.nif = nif;
    }

    private void setWebsite(Website website) {
        this.website = website;
    }

    private void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Gets the manager of the organization.
     *
     * @return the manager of the organization.
     */
    public Colaborador getGestor() {
        return gestor;
    }

    /**
     * Sets the manager of the organization.
     *
     * @param gestor the manager of the organization.
     * @return the manager of the organization.
     */
    public boolean setGestor(Colaborador gestor) {
        if (gestor.isGestor()) {
            this.gestor = gestor;
            return true;
        } else {
            throw new IllegalArgumentException("Colaborador não tem a função de Gestor");
        }
    }

    private void setEnderecoPostal(EnderecoPostal enderecoPostal) {
        this.enderecoPostal = enderecoPostal;
    }

    /**
     * Returns a string representation with very concise but precise information about the object and its attributes.
     *
     * @return a string representation of the object (organization).
     *
     */
    @Override
    public String toString() {
        return "Organizacao{" +
                "nome='" + nome + '\'' +
                ", nif=" + nif +
                ", website=" + website +
                ", telefone=" + telefone +
                ", email=" + email +
                ", enderecoPostal=" + enderecoPostal +
                ", gestor=" + gestor +
                '}';
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
        Organizacao that = (Organizacao) o;
        return nome.equals(that.nome);
    }

}
