package domain;

import java.util.Objects;

public class Colaborador {

    private String nome;
    private Telefone telefone;
    private Email email;
    private Funcao funcao;
    private Organizacao organizacao;

    public Colaborador(String nome, Telefone telefone, Email email, Organizacao organizacao, Funcao funcao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setOrganizacao(organizacao);
        setFuncao(funcao);
    }

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

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isGestor() {
        return funcao == Funcao.GESTOR;
    }


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
