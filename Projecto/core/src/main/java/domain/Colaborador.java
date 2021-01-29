package domain;

public class Colaborador {

    private String nome;
    private Telefone telefone;
    private Email email;
    private Funcao funcao;
    private Organizacao organizacao;

    public Colaborador(String nome, Telefone telefone, Email email, Funcao funcao, Organizacao organizacao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setFuncao(funcao);
        setOrganizacao(organizacao);
    }

    public Colaborador(String nome, Telefone telefone, Email email, Organizacao organizacao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setFuncao(Funcao.COLABORADOR);
        setOrganizacao(organizacao);
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
}
