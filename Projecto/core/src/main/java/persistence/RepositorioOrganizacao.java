package persistence;

import application.UsersAPI;
import domain.*;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizacao implements Serializable {

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
