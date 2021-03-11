package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.Email;
import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.exception.EmailNaoAssociadoException;
import grupo3.sprint_api.exception.FetchingProblemException;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;


import java.sql.*;

import java.sql.SQLException;

/**
 * Class responsible for creating a repository to store information about Users.
 */
public class RepositorioUtilizador {

    private static RepositorioUtilizador instance;

    private ConnectionHandler connectionHandler;

    private RepositorioUtilizador() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }


    /**
     * Static method that returns a unique reference to the class object.
     *
     * @return instance
     */
    public static RepositorioUtilizador getInstance() throws SQLException {
        if (instance == null) {
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
    public boolean insertUtilizadorComRole(User user) {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into Utilizador(nome, email, palavraPasse, designacao) values (?, ?, ?, ?)");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail().toString());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole().getRolename());

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
     * Boolean method that checks if a user exists in the repository,
     * otherwise it is added to it.
     *
     * @param user
     * @return boolean
     */
    public boolean insertUtilizadorSemRole(User user) {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into Utilizador(nome, email, palavraPasse) values (?, ?, ?)");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail().toString());
            pstmt.setString(3, user.getPassword());

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
     * Boolean method that checks if a role was added to an user.
     *
     * @param user
     * @return boolean
     */
    public boolean addRoleToUser(User user) {
        Connection conn = connectionHandler.getConnection();

        try {

            CallableStatement csUpdateUser = conn.prepareCall("UPDATE Utilizador SET designacao = ? WHERE email = ?");
            csUpdateUser.setString(1, user.getRole().getRolename());
            csUpdateUser.setString(2, user.getEmail().toString());

            csUpdateUser.executeQuery();


            csUpdateUser.close();

            return true;

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
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
            Connection conn = connectionHandler.getConnection();
            String emailUtilizador = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utilizador where email = ?");
            pstmt.setString(1, emailUtilizador);

            User user = montarUtilizador(pstmt.executeQuery());

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
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Utilizador where UPPER(nome) = UPPER(?)");
            pstmt.setString(1, nome);

            User user = montarUtilizador(pstmt.executeQuery());

            pstmt.close();
            return user;
        } catch (SQLException e) {
            throw new NomeNaoAssociadoException("O nome " + nome + " não está associado a nenhum utilizador");
        }
    }


    /**
     * Builds an User.
     *
     * @param row
     * @return user
     */
    public User montarUtilizador(ResultSet row) {

        User user = null;

        try {
            Connection conn = connectionHandler.getConnection();

            row.next();

            //Parâmetros do User disponiveis

            String nome = row.getString(2);
            Email email = new Email(row.getString(3));
            String password = row.getString(4);

            //Construir objeto role

            if (row.getString("designacao") != null) {
                PreparedStatement pstmtRole = conn.prepareStatement("SELECT * FROM Role WHERE designacao = ?");
                pstmtRole.setString(1, row.getString("designacao"));
                ResultSet rSetRole = pstmtRole.executeQuery();
                rSetRole.next();

                String descricao = rSetRole.getString("descricao");
                Role role = new Role(row.getString("designacao"), descricao);

                user = new User(nome, password, email, role);
            } else {
                user = new User(nome, password, email);
            }


            row.close();

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

    /**
     * Deletes an role from a user.
     *
     * @param user
     */
    public void deleteRoleFromUser(User user) {
        Connection conn = connectionHandler.getConnection();

        try {

            CallableStatement csUpdateUser = conn.prepareCall("UPDATE Utilizador SET designacao = ? WHERE email = ?");
            csUpdateUser.setString(1, null);
            csUpdateUser.setString(2, user.getEmail().toString());

            csUpdateUser.executeQuery();


            csUpdateUser.close();


        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

    }
}
