package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.exception.EmailNaoAssociadoException;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;

import java.util.ArrayList;

public class RepositorioUtilizador {
    private static RepositorioUtilizador instance;

//    Connection conn =

    /**
     * Static method that returns a unique reference to the class object,
     * that implements a singleton.
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
     * @param user
     * @return boolean
     */
    public boolean insertUtilizador(User user) {


        return true;
    }

    /**
     * Method to get a user through your email.
     * @param email
     * @return u
     */
    public User getUserByEmail(Email email) {
/*        for (User u : utilizadoresRegistados) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }*/
        throw new EmailNaoAssociadoException(email.toString() + " não está associado a nenhum utilizador");
    }

    /**
     * Method for obtaining a user using his username.
     * @param nome
     * @return u
     */
    public User getUserByUsername(String nome) {
/*
        for (User u : utilizadoresRegistados) {
            if (u.getUsername().equals(nome)) {
                return u;
            }
        }
*/

        throw new NomeNaoAssociadoException("O nome " + nome + " não está associado a nenhum utilizador");
    }

    /**
     * Method for obtaining a user through their role.
     * @param role
     * @return usersByRole
     */
    public ArrayList<User> getUtilizadoresByRole (Role role) {
        ArrayList<User> usersByRole = new ArrayList<>();

       /* for (User u : utilizadoresRegistados) {
            if (u.getRole() == role) {
                usersByRole.add(u);
            }
        }*/
        return usersByRole;
    }


    /**
     * Method for listing (registering) users.
     * @return new ArrayList<>
     */
    public ArrayList<User> listarUtilizadores() {
        return new ArrayList<>();
    }

}
