package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Current class represents the tool for creating new collaborators   
 */
public class Colaborador implements Serializable {

    private String nome;
    private Telefone telefone;
    private Email email;
    private Funcao funcao;
    private Organizacao organizacao;

    /**
     * Instantiates a new collaborator.
     *
     * @param nome        the nome
     * @param telefone    the telefone
     * @param email       the email
     * @param organizacao the organizacao
     * @param funcao      the funcao
     */
    public Colaborador(String nome, Telefone telefone, Email email, Organizacao organizacao, Funcao funcao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setOrganizacao(organizacao);
        setFuncao(funcao);
    }

    /**
     * Instantiates a new Colaborador.
     *
     * @param nome        the nome
     * @param telefone    the telefone
     * @param email       the email
     * @param organizacao the organizacao
     */
    public Colaborador(String nome, Telefone telefone, Email email, Organizacao organizacao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setOrganizacao(organizacao);
        setFuncao(Funcao.COLABORADOR);
    }

    private void setNome(String nome) {
        if (nome.length() < 2) {
            throw new IllegalArgumentException(nome + " é um nome inválido");
        }
        this.nome = nome;
    }

    private void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    private void setEmail(Email email) {
        this.email = email;
    }

    private void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    /**
     * Gets nome.
     *
     * @return the nome
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
     * Is gestor boolean.
     *
     * @return the boolean
     */
    public boolean isGestor() {
        return funcao == Funcao.GESTOR;
    }


    /**
     * Gets organizacao.
     *
     * @return the organizacao
     */
    public Organizacao getOrganizacao() {
        return organizacao;
    }

    private void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colaborador that = (Colaborador) o;
        return Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                funcao == that.funcao &&
                Objects.equals(getOrganizacao(), that.getOrganizacao());
    }

}
