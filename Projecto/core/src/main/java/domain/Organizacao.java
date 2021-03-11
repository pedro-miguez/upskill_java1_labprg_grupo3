package domain;

import java.io.Serializable;
import java.util.Objects;


/**
 * Current class implements the tools for creating new organizations (as Organizacao) 
 * with specific parameters such as name (nome), tax id number (nif), website, 
 * phone number (telefone), email, address and a manager (gestor).
 * It has implementations to get all these parameters through toString() method 
 * and a method to set the manager from an organization.
 */
public class Organizacao implements Serializable {

    private String nome;
    private NIF nif;
    private Website website;
    private Telefone telefone;
    private Email email;
    private EnderecoPostal enderecoPostal;


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
    public Organizacao(String nome, NIF nif, Website website, Telefone telefone,
                        Email email, EnderecoPostal enderecoPostal){
        setNome(nome);
        setNif(nif);
        setEnderecoPostal(enderecoPostal);
        setTelefone(telefone);
        setWebsite(website);
        setEmail(email);
    }

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da organização inválido");
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

    /**
     * Gets the nif of the organization.
     * 
     * @return nif
     */
    public NIF getNIF() {
        return this.nif;
    }

    /**
     * Gets the website of the organization.
     * 
     * @return website
     */
    public Website getWebsite() {
        return this.website;
    }

    /**
     * Gets the phone number of the organization.
     * 
     * @return telefone
     */
    public Telefone getTelefone() {
        return this.telefone;
    }

    /**
     * Gets the email of the organization.
     * 
     * @return email
     */
    public Email getEmail() {
        return this.email;
    }

    /**
     * Gets the address of the organization.
     * 
     * @return endereçoPostal
     */
    public EnderecoPostal getEnderecoPostal() {
        return enderecoPostal;
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

    private void setEnderecoPostal(EnderecoPostal enderecoPostal) {
        this.enderecoPostal = enderecoPostal;
    }

    
    /**
     * Returns a string representation with very concise but precise information 
     * about the object and its attributes.
     *
     * @return a string representation of the object (organization).
     *
     */
    @Override
    public String toString() {
        return String.format("Nome: %s%nNIF: %s%nWebsite: %s%nTelefone: %s%nE-mail: %s%nEndereço Postal: %s",
                this.getNome(), this.nif, this.website, this.telefone, this.email, 
                this.getEnderecoPostal());
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
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizacao that = (Organizacao) o;
        return nome.equals(that.nome);
    }*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organizacao)) return false;
        Organizacao that = (Organizacao) o;
        return getNome().equals(that.getNome()) && nif.equals(that.nif) &&
                getWebsite().equals(that.getWebsite()) && 
                getTelefone().equals(that.getTelefone())
                && getEmail().equals(that.getEmail());
    }
    
}
