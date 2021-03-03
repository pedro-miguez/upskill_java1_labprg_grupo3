package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.exception.EmailNaoAssociadoException;
import grupo3.sprint_api.exception.FetchingProblemException;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;
import oracle.jdbc.proxy.annotation.Pre;


import java.sql.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioUtilizador {
    private static RepositorioUtilizador instance;

    private ConnectionHandler connectionHandler;

    private RepositorioUtilizador() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }


    /**
     * Static method that returns a unique reference to the class object,
     * that implements a singleton.
     *
     * @return instance
     */


    public static RepositorioUtilizador getInstance() throws SQLException {
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
    public boolean insertUtilizador(User user) {
        Connection conn = ;

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into Utilizador(nome, email, palavraPasse, designacao) values (?, ?, ?, ?)");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail().toString());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole().getDesignacao());

            pstmt.executeQuery();

            conn.commit();
            pstmt.close();

            return true;
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch (SQLException excep) {
                excep.getErrorCode();
            }
        }

        return false;

    }

    /**
     * Method to get a user through your email.
     *
     * @param email
     * @return u
     */
    public User getUtilizadorByEmail(Email email) {
        try {
            Connection conn = ;
            String emailUtilizador = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utilizador where email = ?");
            pstmt.setString(1, emailUtilizador);

            User user = montarUtilizador(pstmt.executeQuery(), true);

            pstmt.close();
            return user;
        } catch (SQLException e) {
            throw new EmailNaoAssociadoException(email.toString() + " não está associado a nenhum utilizador");
        }
    }


    /**
     * Method for obtaining a user using his username.
     *
     * @param nome
     * @return u
     */
    public User getUtilizadorByNome(String nome) {
        try {
            Connection conn = ;
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utilizador where nome = ?");
            pstmt.setString(1, nome);

            User user = montarUtilizador(pstmt.executeQuery(), true);

            pstmt.close();
            return user;
        } catch (SQLException e) {
            throw new NomeNaoAssociadoException("O nome " + nome + " não está associado a nenhum utilizador");
        }
    }

    /**
     * Method for obtaining a user through their role.
     *
     * @param role
     * @return usersByRole
     */
    public ArrayList<User> getUtilizadoresByRole(Role role) {
        ArrayList<User> usersByRole = new ArrayList<>();

       /* for (User u : utilizadoresRegistados) {
            if (u.getRole() == role) {
                usersByRole.add(u);
            }
        }*/
        return usersByRole;
    }

    public ArrayList<Role> getRoles(ResultSet rSetRoles) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            while (rSetRoles.next()) {
                roles.add(montarRole(rSetRoles, false));
            }

            rSetRoles.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
        }

        return roles;
    }


    /**
     * Method for listing (registering) users.
     *
     * @return new ArrayList<>
     */
    public ArrayList<User> listarUtilizadores() {
        return new ArrayList<>();
    }


    private User montarUtilizador(ResultSet row, boolean unico) {

        User user = null;

        try {
            Connection conn = ;
            if (row.getRow() < 1) {
                row.next();
            }
            //Parâmetros do User disponiveis

            String nome = row.getString(2);
            Email email = new Email(row.getString(3));
            String password = row.getString(4);

            //Construir objeto role
            PreparedStatement pstmtRole = conn.prepareStatement("SELECT * FROM Role WHERE designacao = ?");
            pstmtRole.setString(1, row.getString("designacao"));
            ResultSet rSetRole = pstmtRole.executeQuery();
            rSetRole.next();

            String descricao = rSetRole.getString("descricao");
            Role role = new Role(row.getString("designacao"), descricao);

            user = new User(nome, password, email, role);

            if (unico) row.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (user != null) {
            return user;
        } else {
            throw new FetchingProblemException("Problema a montar utilizador");
        }
    }

    public Role montarRole(ResultSet row, boolean unico) {

        Role role = null;
        try {
            row.next();
            String designacao = row.getString(1);
            String descricao = row.getString(2);

            role = new Role(designacao, descricao);

            if (unico) row.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }
        if (role != null) {
            return role;
        } else {
            throw new FetchingProblemException("Problema a montar o role");
        }

    }

}
