package persistence;

import domain.*;
import exceptions.EmailNaoAssociadoAColaboradorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The type RepositorioUtilizador.
 */
public class RepositorioUtilizador implements Serializable {

    private static RepositorioUtilizador instance;

    /**
     * Utilizadores que irão ser adicionados (registados) no repositório.
     */
    private RepositorioUtilizador(){
        utilizadoresRegistados = new ArrayList<>();
    }

    private List<User> utilizadoresRegistados;

    /**
     * Método estático que devolve uma referência única do objecto da classe,
     * que implementa um singleton.
     * @return 
     */
    public static RepositorioUtilizador getInstance(){
        if(instance == null){
            instance = new RepositorioUtilizador();
        }
        return instance;
    }

    /**
     * Método booleano que verifica se um utilizador existe no repositório,
     * caso contrário é adicionado ao mesmo.
     * @param user
     * @return 
     */
    public boolean addUtilizador(User user) {
        if (this.utilizadoresRegistados.contains(user)) {
            return false;
        } else {
            return this.utilizadoresRegistados.add(user);
        }
    }

    /**
     * Método para obtenção de um utilizador através do seu email.
     * @param email
     * @return 
     */
    public User getUserByEmail(Email email) {
        for (User u : utilizadoresRegistados) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }

        throw new EmailNaoAssociadoAColaboradorException(email.toString() + " não está associado a nenhum utilizador");
    }

    /**
     * Método para obtenção de um utilizador através do seu username.
     * @param nome
     * @return 
     */
    public User getUserByUsername(String nome) {
        for (User u : utilizadoresRegistados) {
            if (u.getUsername().equals(nome)) {
                return u;
            }
        }

        throw new IllegalArgumentException("O nome " + nome + " não está associado a nenhum utilizador");
    }

    /**
     * Método para obtenção de um utilizador através do seu papel(role).
     * @param role
     * @return 
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
     * Método para listar (registar) utilizadores.
     * @return 
     */
    public ArrayList<User> listarUtilizadores() {
        return new ArrayList<>(this.utilizadoresRegistados);
    }


}
