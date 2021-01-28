package domain;

public class Colaborador {

    private String nome;
    private Telefone telefone;
    private Email email;
    private Funcao funcao;

    public Colaborador(String nome, Telefone telefone, Email email, Funcao funcao) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setFuncao(funcao);
    }

    private void setNome(String nome) {
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


    
}
