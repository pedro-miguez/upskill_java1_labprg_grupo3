package persistence;

import domain.*;
import exceptions.EmailNaoAssociadoAColaboradorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUtilizador implements Serializable {

    private static RepositorioUtilizador instance;

    private RepositorioUtilizador(){
        utilizadoresRegistados = new ArrayList<>();
    }

    private List<User> utilizadoresRegistados;

    public static RepositorioUtilizador getInstance(){
        if(instance == null){
            instance = new RepositorioUtilizador();
        }
        return instance;
    }

    public boolean addUtilizador(User user) {
        if (this.utilizadoresRegistados.contains(user)) {
            return false;
        } else {
            return this.utilizadoresRegistados.add(user);
        }
    }


    public User getUserByEmail(Email email) {
        for (User u : utilizadoresRegistados) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }

        throw new EmailNaoAssociadoAColaboradorException(email.toString() + " não está associado a nenhum utilizador");
    }

    public User getUserByUsername(String nome) {
        for (User u : utilizadoresRegistados) {
            if (u.getUsername().equals(nome)) {
                return u;
            }
        }

        throw new IllegalArgumentException("O nome " + nome + " não está associado a nenhum utilizador");
    }

    public ArrayList<User> getUtilizadoresByRole (Role role) {
        ArrayList<User> usersByRole = new ArrayList<>();

        for (User u : utilizadoresRegistados) {
            if (u.getRole() == role) {
                usersByRole.add(u);
            }
        }
        return usersByRole;
    }



    public ArrayList<User> listarUtilizadores() {
        return new ArrayList<>(this.utilizadoresRegistados);
    }


}
