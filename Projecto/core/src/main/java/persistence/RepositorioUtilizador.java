package persistence;

import domain.*;
import exceptions.EmailNaoAssociadoException;
import exceptions.NomeNaoAssociadoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * User.
 */
public class RepositorioUtilizador implements Serializable {

    private static RepositorioUtilizador instance;

    /**
     * Users to be added (registered) to the repository.
     */
    private RepositorioUtilizador(){
        utilizadoresRegistados = new ArrayList<>();
    }

    private List<User> utilizadoresRegistados;


    /**
     * Static method that returns a unique reference to the class object,
     * that implements a singleton.
     * 
     * @return instance
     */
    public static RepositorioUtilizador getInstance(){
        if(instance == null){
            instance = new RepositorioUtilizador();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a user exists in the repository,
     * otherwise it is added to it.
     * 
     * @param user
     * @return boolean
     */
    public boolean addUtilizador(User user) {
        if (this.utilizadoresRegistados.contains(user)) {
            return false;
        } else {
            return this.utilizadoresRegistados.add(user);
        }
    }

    /**
     * Method to get a user through your email.
     * 
     * @param email
     * @return u
     */
    public User getUserByEmail(Email email) {
        for (User u : utilizadoresRegistados) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        throw new EmailNaoAssociadoException(email.toString() + " não está associado a nenhum utilizador");
    }

    /**
     * Method for obtaining a user using his username.
     * 
     * @param nome
     * @return u
     */
    public User getUserByUsername(String nome) {
        for (User u : utilizadoresRegistados) {
            if (u.getUsername().equals(nome)) {
                return u;
            }
        }

        throw new NomeNaoAssociadoException("O nome " + nome + " não está associado a nenhum utilizador");
    }

    /**
     * Method for obtaining a user through their role.
     * 
     * @param role
     * @return usersByRole
     */
    public ArrayList<User> getUtilizadoresByRole (Role role) {
        ArrayList<User> usersByRole = new ArrayList<>();

        for (User u : utilizadoresRegistados) {
            if (u.getRole() == role) {
                usersByRole.add(u);
            }
        }
        return usersByRole;
    }


    /**
     * Method for listing (registering) users.
     * 
     * @return new ArrayList<>
     */
    public ArrayList<User> listarUtilizadores() {
        return new ArrayList<>(this.utilizadoresRegistados);
    }

}
