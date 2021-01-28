package persistence;

import domain.Colaborador;
import domain.Organizacao;

import java.util.ArrayList;
import java.util.List;

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
}
