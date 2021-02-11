package persistence;

import domain.*;
import exceptions.EmailNaoAssociadoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * collaborator.
 */
public class RepositorioColaborador implements Serializable {

    private static RepositorioColaborador instance;

    /**
     * Collaborators that will be added to the repository.
     */
    private RepositorioColaborador(){
        colaboradoresRegistados = new ArrayList<>();
    }

    private List<Colaborador> colaboradoresRegistados;

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioColaborador getInstance(){
        if(instance == null){
            instance = new RepositorioColaborador();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a collaborator exists in the repository, 
     * otherwise it is added to it.
     * @param colaborador
     * @return 
     */
    public boolean addColaborador(Colaborador colaborador) {
        if (this.colaboradoresRegistados.contains(colaborador)) {
            return false;
        } else {
            return this.colaboradoresRegistados.add(colaborador);
        }
    }

    /**
     * Method to obtain a collaborator through your email.
     * @param email
     * @return 
     */
    public Colaborador getColaboradorByEmail(Email email) {
        for (Colaborador c : colaboradoresRegistados) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }

        throw new EmailNaoAssociadoException(email.toString() + " não está associado a nenhum colaborador");

    }

    /**
     * Method for obtaining a collaborator from a given organization.
     * @param organizacao
     * @return 
     */
    public ArrayList<Colaborador> getColaboradoresOrganizacao (Organizacao organizacao) {
        ArrayList<Colaborador> colaboradoresOrganizacao = new ArrayList<>();

        for (Colaborador c : colaboradoresRegistados) {
            if (c.getOrganizacao().equals(organizacao)) {
                colaboradoresOrganizacao.add(c);
            }
        }

        return colaboradoresOrganizacao;
    }

    /**
     * Method for listing collaborators.
     * @return 
     */
    public ArrayList<Colaborador> listarColaboradores() {
        return new ArrayList<>(this.colaboradoresRegistados);
    }

    public Colaborador criarColaborador(String nomeColaborador,int contactoColaborador,
                                        String emailColaborador, Organizacao org) {
        return new Colaborador(nomeColaborador, new Telefone(contactoColaborador),
                new Email(emailColaborador), org, Funcao.COLABORADOR);
    }

    public Colaborador criarGestor(String nomeGestor,int contactoGestor,
                              String emailGestor, Organizacao org) {
        return new Colaborador(nomeGestor, new Telefone(contactoGestor),
                new Email(emailGestor), org, Funcao.GESTOR);
    }
    /**
     * Method to check if two objects (in this case, collaborators) are the same.
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositorioColaborador that = (RepositorioColaborador) o;
        return colaboradoresRegistados.equals(that.colaboradoresRegistados);
    }

}
