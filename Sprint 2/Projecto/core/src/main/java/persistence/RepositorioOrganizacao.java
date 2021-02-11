package persistence;

import application.UsersAPI;
import domain.*;
import exceptions.EmailNaoAssociadoException;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * organization.
 */
public class RepositorioOrganizacao implements Serializable {

    private static RepositorioOrganizacao instance;

    /**
     * Organizations that will be added (registered) to the repository.
     */
    private RepositorioOrganizacao(){
        organizacoesRegistadas = new ArrayList<>();
    }

    private List<Organizacao> organizacoesRegistadas;

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioOrganizacao getInstance(){
        if(instance == null){
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    /**
     * Boolean method that checks if an organization exists in the repository, 
     * otherwise it is added to it.
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
     * Boolean method that checks if a manager exists in the organization, 
     * otherwise it is added (as being a collaborator).
     * @param colaborador
     * @param organizacao
     * @return 
     */
    public boolean addGestor(Colaborador colaborador, Organizacao organizacao) {
        return organizacao.setGestor(colaborador);
    }



    /**
     * Method for obtaining an organization through its manager.
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

    public Organizacao getOrganizacaoByEmail(Email email) {
        for (Organizacao o : organizacoesRegistadas) {
            if (o.getEmail() != null && o.getEmail().equals(email) ) {
                return o;
            }
        }
        throw new EmailNaoAssociadoException("Não existe nenhuma organização associada a este e-mail.");
    }

    /**
     * Method for listing (registering) organizations.
     * @return 
     */
    public ArrayList<Organizacao> listarOrganizacoes() {
        return new ArrayList<>(this.organizacoesRegistadas);
    }

    public Organizacao criarOrganizacao(String nomeOrg, int nif, String website, int telefone,
                                        String email, String rua, String localidade, String codigoPostal) {
        return new Organizacao(nomeOrg, new NIF(nif), new Website(website),
                new Telefone(telefone), new Email(email) , new EnderecoPostal(rua, localidade, codigoPostal));
    }
    /**
     * Method to check if two objects (in this case, organizations) are the same.
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
