package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class represents the tool for creating new collaborators from an 
 * organization. This ones may have different roles within their organization 
 *(which are listed in the Funcao enum class). Each of them is also described by
 * their name,mobile phone and email.
 */
public class Colaborador implements Serializable {

    private String nome;
    private Telefone telefone;
    private Email email;
    private String funcao;

    /**
     * Instantiates a new collaborator.
     *
     * @param nome        as name
     * @param telefone    as telephone
     * @param email       as email
     * @param funcao      as role (only the organization managers have this attribute)
     */
    public Colaborador(String nome, Telefone telefone, Email email, String funcao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setFuncao(funcao);
    }

    private void setNome(String nome) {
        if (nome.length() < 2 || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do colaborador inválido");
        }
        this.nome = nome;
    }

    private void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    private void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Gets the phone number.
     * 
     * @return telefone
     */
    public Telefone getTelefone() {
        return this.telefone;
    }

    /**
     * Gets the role.
     * 
     * @return funcao
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * Returns a string representation of the object collaborator and its attributes.
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Nome: %s%nTelefone: %s%nE-mail: %s%nFunção: %s",
                this.nome, this.telefone, this.email, this.funcao);
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
        Colaborador that = (Colaborador) o;
        return Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                funcao.equals(that.funcao);
    }

}
