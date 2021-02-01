package persistence;

import application.UsersAPI;
import domain.AlgoritmoGeradorPasswords;
import domain.Plataforma;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;
import domain.Colaborador;
import domain.Organizacao;

import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao {

    private static RepositorioOrganizacao instance;

    private RepositorioOrganizacao(){
        organizacoesRegistadas = new ArrayList<>();
    }

    private List<Organizacao> organizacoesRegistadas;

    public static RepositorioOrganizacao getInstance(){
        if(instance == null){
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    public boolean addOrganizacao(Organizacao organizacao) {
        if (this.organizacoesRegistadas.contains(organizacao)) {
            return false;
        } else {
            this.organizacoesRegistadas.add(organizacao);
            return true;
        }
    }

    public boolean addGestor(Colaborador colaborador, Organizacao organizacao) {
        return organizacao.setGestor(colaborador);
    }

    public boolean registarGestorComoUtilizador(Colaborador colaborador) {
        String nome = colaborador.getNome();
        String email = colaborador.getEmail().toString();

        AlgoritmoGeradorPasswords alg = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        String password = alg.geraPassword();

        UsersAPI uapi = Plataforma.getInstance().getUsersAPI();
        return uapi.registerUserWithRoles(nome, email, password, "gestor");
    }

    public Organizacao getOrganizacaoByGestor(Colaborador colaborador) {
        for (Organizacao o : organizacoesRegistadas) {
            if (o.getGestor() != null && o.getGestor().equals(colaborador) ) {
                return o;
            }
        }
        throw new GestorNaoRelacionadoANenhumaOrgException("Não existe nenhuma organização associada a este gestor");
    }

    public ArrayList<Organizacao> listarOrganizacoes() {
        return new ArrayList<>(this.organizacoesRegistadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioOrganizacao that = (RepositorioOrganizacao) o;
        return organizacoesRegistadas.equals(that.organizacoesRegistadas);
    }

}
