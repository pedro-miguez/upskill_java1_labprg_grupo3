package persistence;

import domain.Colaborador;
import domain.Organizacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositorioOrganizacao {

    private static RepositorioOrganizacao instance;

    private RepositorioOrganizacao(){
        organizacoesRegistadas = new ArrayList<Organizacao>();
    }

    private List<Organizacao> organizacoesRegistadas;

    public static RepositorioOrganizacao getInstance(){
        if(instance == null){
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    public void addOrganizacao(Organizacao organizacao) {
        this.organizacoesRegistadas.add(organizacao);
    }

    public void addGestor(Colaborador colaborador, Organizacao organizacao) {
        organizacao.setGestor(colaborador);
    }

    public Organizacao getOrganizacaoByGestor(Colaborador colaborador) {
        for (Organizacao o : organizacoesRegistadas) {
            if (o.getGestor().equals(colaborador)) {
                return o;
            }
        }
        return null; //throw new GestorNaoRelacionadoANenhumaOrganizacaoException()
    }

    public ArrayList<Organizacao> listarOrganizacoes() {
        return new ArrayList<Organizacao>(this.organizacoesRegistadas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioOrganizacao that = (RepositorioOrganizacao) o;
        return organizacoesRegistadas.equals(that.organizacoesRegistadas);
    }

}
