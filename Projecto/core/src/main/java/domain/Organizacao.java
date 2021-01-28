package domain;

public class Organizacao {

    private String nome;
    private NIF nif;
    private Website website;
    private Telefone telefone;
    private Email email;
    private Colaborador gestor;

    public Organizacao(String nome, NIF nif, Website website, Telefone telefone, Email email){
        setNome(nome);
        setNif(nif);
        setWebsite(website);
        setTelefone(telefone);
        setEmail(email);
    }
    
    private void setNome(String nome) {
        this.nome = nome;
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

    public Colaborador getGestor() {
        return gestor;
    }

    public void setGestor(Colaborador gestor) {
        this.gestor = gestor;
    }

}
