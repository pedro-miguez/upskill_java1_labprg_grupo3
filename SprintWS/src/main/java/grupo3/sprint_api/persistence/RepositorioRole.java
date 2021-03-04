package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.domain.User;
import grupo3.sprint_api.exception.FetchingProblemException;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioRole {

    private static RepositorioRole instance;

    private ConnectionHandler connectionHandler;

    private RepositorioRole() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Static method that returns a unique reference to the class object,
     * that implements a singleton.
     *
     * @return instance
     */
    public static RepositorioRole getInstance() throws SQLException {
        if(instance == null){
            instance = new RepositorioRole();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a role exists in the repository,
     * otherwise it is added to it.
     *
     * @param role
     * @return boolean
     */
    public boolean insertRole(Role role) {
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareCall("insert into Role(designacao, descricao) values (?, ?)");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            pstmt.setString(1, role.getDesignacao());
            pstmt.setString(2, role.getDescricao());


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
     * Method for obtaining a role through their user.
     *
     * @param nome
     * @return usersByRole
     */
    public Role getRoleByUtilizador(String nome) {
        try {
            Connection conn = connectionHandler.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT desginacao FROM Utilizador where nome = ?");
            pstmt.setString(1, nome);
            ResultSet rSetUtilizador = pstmt.executeQuery();
            rSetUtilizador.next();
            String designacao = rSetUtilizador.getString("designacao");

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Role WHERE designacao = ?");
            pstmt2.setString(1, designacao);
            ResultSet rSetRole = pstmt.executeQuery();
            rSetRole.next();

            Role role = montarRole(rSetRole, true);

            pstmt.close();
            return role;
        } catch (SQLException e) {
            throw new NomeNaoAssociadoException("O nome " + nome + " não está associado a nenhum utilizador");
        }
    }


    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmtRoles = conn.prepareStatement("SELECT * FROM Roles");
            ResultSet rSetRoles = pstmtRoles.executeQuery();
            rSetRoles.next();
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
