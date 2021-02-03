package domain;

import java.io.Serializable;
import java.util.Objects;


/**
 * The type Organizacao.
 */
public class Organizacao implements Serializable {

    /**
     * The Nome.
     */
    private String nome;
    /**
     * The Nif.
     */
    private NIF nif;
    /**
     * The Website.
     */
    private Website website;
    /**
     * The Telefone.
     */
    private Telefone telefone;
    /**
     * The Email.
     */
    private Email email;
    /**
     * The Endereco postal.
     */
    private EnderecoPostal enderecoPostal;
    /**
     * The Gestor.
     */
    private Colaborador gestor;

    /**
     * Instantiates a new Organizacao.
     *
     * @param nome           the nome
     * @param nif            the nif
     * @param website        the website
     * @param telefone       the telefone
     * @param email          the email
     * @param enderecoPostal the endereco postal
     */
    public Organizacao(String nome, NIF nif, Website website, Telefone telefone, Email email, EnderecoPostal enderecoPostal){
        setNome(nome);
        setNif(nif);
        setWebsite(website);
        setTelefone(telefone);
        setEmail(email);
        setEnderecoPostal(enderecoPostal);
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    private void setNome(String nome) {
        if (nome.length() < 1) {
            throw new IllegalArgumentException(nome + " é um nome inválido");
        }
        this.nome = nome;
    }

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Sets nif.
     *
     * @param nif the nif
     */
    private void setNif(NIF nif) {
        this.nif = nif;
    }

    /**
     * Sets website.
     *
     * @param website the website
     */
    private void setWebsite(Website website) {
        this.website = website;
    }

    /**
     * Sets telefone.
     *
     * @param telefone the telefone
     */
    private void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    private void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Gets gestor.
     *
     * @return the gestor
     */
    public Colaborador getGestor() {
        return gestor;
    }

    /**
     * Sets gestor.
     *
     * @param gestor the gestor
     * @return the gestor
     */
    public boolean setGestor(Colaborador gestor) {
        if (gestor.isGestor()) {
            this.gestor = gestor;
            return true;
        } else {
            throw new IllegalArgumentException("Colaborador não tem a função de Gestor");
        }
    }

    /**
     * Sets endereco postal.
     *
     * @param enderecoPostal the endereco postal
     */
    private void setEnderecoPostal(EnderecoPostal enderecoPostal) {
        this.enderecoPostal = enderecoPostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizacao that = (Organizacao) o;
        return nome.equals(that.nome);
    }

}
