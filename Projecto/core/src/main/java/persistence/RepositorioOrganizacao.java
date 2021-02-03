package persistence;

import application.UsersAPI;
import domain.*;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The type RepositorioOrganizacao.
 */
public class RepositorioOrganizacao implements Serializable {

    private static RepositorioOrganizacao instance;

    /**
     * Organizações que irão ser adicionadas (registadas) no repositório.
     */
    private RepositorioOrganizacao(){
        organizacoesRegistadas = new ArrayList<>();
    }

    private List<Organizacao> organizacoesRegistadas;

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioOrganizacao getInstance(){
        if(instance == null){
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se uma organização existe no repositório,
     * caso contrário é adicionada ao mesmo.
     * @param organizacao
     * @return 
     */
    public boolean addOrganizacao(Organizacao organizacao) {
        if (this.organizacoesRegistadas.contains(organizacao)) {
            return false;
        } else {
            this.organizacoesRegistadas.add(organizacao);
            return true;
        }
    }

    /**
     * Método booleano que verifica se na organização existe um gestor,
     * caso contrário é adicionado (como sendo um colaborador).
     * @param colaborador
     * @param organizacao
     * @return 
     */
    public boolean addGestor(Colaborador colaborador, Organizacao organizacao) {
        return organizacao.setGestor(colaborador);
    }



    /**
     * Método para obtenção de uma organização através do seu gestor.
     * @param colaborador
     * @return 
     */
    public Organizacao getOrganizacaoByGestor(Colaborador colaborador) {
        for (Organizacao o : organizacoesRegistadas) {
            if (o.getGestor() != null && o.getGestor().equals(colaborador) ) {
                return o;
            }
        }
        throw new GestorNaoRelacionadoANenhumaOrgException("Não existe nenhuma organização associada a este gestor");
    }

    /**
     * Método para listar (registar) organizações.
     * @return 
     */
    public ArrayList<Organizacao> listarOrganizacoes() {
        return new ArrayList<>(this.organizacoesRegistadas);
    }

    /**
     * Método para verificar se dois objectos (neste caso, organizações) são
     * iguais.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioOrganizacao that = (RepositorioOrganizacao) o;
        return organizacoesRegistadas.equals(that.organizacoesRegistadas);
    }



}
