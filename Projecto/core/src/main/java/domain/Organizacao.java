package domain;

import java.util.Objects;

public class Organizacao {

    private String nome;
    private NIF nif;
    private Website website;
    private Telefone telefone;
    private Email email;
    private EnderecoPostal enderecoPostal;
    private Colaborador gestor;

    public Organizacao(String nome, NIF nif, Website website, Telefone telefone, Email email, EnderecoPostal enderecoPostal){
        setNome(nome);
        setNif(nif);
        setWebsite(website);
        setTelefone(telefone);
        setEmail(email);
        setEnderecoPostal(enderecoPostal);
    }
    
    private void setNome(String nome) {
        if (nome.length() < 1) {
            throw new IllegalArgumentException(nome + " é um nome inválido");
        }
        this.nome = nome;
    }

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

    public Colaborador getGestor() {
        if (this.gestor != null) {
            return gestor;
        } else {
            throw new NullPointerException("Ainda não existe gestor associado a esta organização");
        }
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizacao that = (Organizacao) o;
        return nome.equals(that.nome);
    }

}
