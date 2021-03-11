package grupo3.sprint_api.persistence;

import grupo3.sprint_api.domain.Role;
import grupo3.sprint_api.exception.FetchingProblemException;
import grupo3.sprint_api.exception.NomeNaoAssociadoException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class responsible for creating a repository to store information about Roles.
 */
public class RepositorioRole {

    private static RepositorioRole instance;

    private ConnectionHandler connectionHandler;

    private RepositorioRole() throws SQLException {
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Static method that returns a unique reference to the class object.
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

            pstmt.setString(1, role.getRolename());
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT designacao FROM Utilizador where nome = ?");
            pstmt.setString(1, nome);
            ResultSet rSetUtilizador = pstmt.executeQuery();
            rSetUtilizador.next();
            String designacao = rSetUtilizador.getString("designacao");

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Role WHERE designacao = ?");
            pstmt2.setString(1, designacao);
            ResultSet rSetRole = pstmt2.executeQuery();


            Role role = montarRole(rSetRole, true);

            pstmt.close();
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NomeNaoAssociadoException("O nome " + nome + " não está associado a nenhum utilizador");
        }
    }

    /**
     * Gets an role by its name.
     *
     * @param rolename
     * @return role
     */
    public Role getRoleByRolename(String rolename) {
        try {
            Connection conn = connectionHandler.getConnection();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Role WHERE designacao = ?");
            pstmt.setString(1, rolename);
            ResultSet rSetRole = pstmt.executeQuery();


            Role role = montarRole(rSetRole, true);

            pstmt.close();
            return role;
        } catch (SQLException e) {
            throw new NomeNaoAssociadoException("A designacao: " + rolename + " não está associado a nenhum Role");
        }
    }


    /**
     * Gets an list of the roles.
     *
     * @return roles
     */
    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        Connection conn = connectionHandler.getConnection();

        try {
            PreparedStatement pstmtRoles = conn.prepareStatement("SELECT * FROM Role");
            ResultSet rSetRoles = pstmtRoles.executeQuery();
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
     * Sets an role.
     *
     * @param row
     * @param unico
     * @return role
     */
    public Role montarRole(ResultSet row, boolean unico) {

        Role role = null;
        try {
            if (unico) row.next();
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

    /**
     * Deletes an role.
     *
     * @param role
     */
    public void deleteRole(Role role) {
        Connection conn = connectionHandler.getConnection();

        try {

            CallableStatement deleteRole = conn.prepareCall("Delete from Role where designacao = ? ");
            deleteRole.setString(1, role.getRolename());

            deleteRole.executeQuery();

            deleteRole.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

    }
}
