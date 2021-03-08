package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import network.ConnectionHandler;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class responsible for creating a repository to store information about
 * collaborator.
 */
public class RepositorioColaborador implements Serializable {

    private static RepositorioColaborador instance;


    /**
     * Collaborators that will be added to the repository.
     */
    private RepositorioColaborador() {

    }

    /**
     * Static method that returns a unique reference to the class object, which
     * implements a singleton.
     *
     * @return instance
     */
    public static RepositorioColaborador getInstance() {
        if (instance == null) {
            instance = new RepositorioColaborador();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a collaborator exists in the repository,
     * otherwise it is added to it.
     *
     * @param colaborador
     * @return boolean
     */
    public boolean insertUtilizadorColaborador(Colaborador colaborador, String password, String emailGestor) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {
            conn.setAutoCommit(false);

            CallableStatement cs1 = conn.prepareCall("{? = call getOrganizacaoByEmailColaborador(?)}");
            cs1.registerOutParameter(1, Types.INTEGER);
            cs1.setString(2, emailGestor.toString());
            cs1.executeUpdate();

            int orgID = cs1.getInt(1);

            CallableStatement cs2 = conn.prepareCall("{CALL createColaborador(?, ?, ?, ?, ?)}");
            cs2.setString(1, colaborador.getNome());
            cs2.setString(2, colaborador.getEmail().toString());
            cs2.setInt(3, Integer.parseInt(colaborador.getTelefone().toString()));
            cs2.setInt(4, orgID);
            cs2.setString(5, colaborador.getFuncao());

            cs2.executeQuery();

            conn.commit();
            cs1.close();
            cs2.close();

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
     * Method to obtain a collaborator through your email.
     *
     * @param email
     * @return montarColaborador(pstmt.executeQuery())
     */
    public Colaborador getColaboradorByEmail(Email email) throws SQLException {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();
            String emailColaborador = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Colaborador where Email = ?");
            pstmt.setString(1, emailColaborador);

            Colaborador colaborador = montarColaborador(pstmt.executeQuery(), true);
            pstmt.close();
            return colaborador;
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum colaborador com esse email.");
        }

    }

    /**
     * Method to obtain a collaborator of an organization through the email.
     * 
     * @param email
     * @return montarListaColaboradores(pstmt.executeQuery())
     */
    public ArrayList<Colaborador> getColaboradoresOrganizacaoByEmail(Email email) throws FetchingProblemException {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

            CallableStatement cs = conn.prepareCall("SELECT idOrganizacao FROM Colaborador WHERE email = ?");
            cs.setString(1, email.toString());
            ResultSet rSetColaborador = cs.executeQuery();
            rSetColaborador.next();

            int orgID = rSetColaborador.getInt("idOrganizacao");

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Colaborador WHERE idOrganizacao = ?");
            pstmt.setInt(1, orgID);

            ArrayList<Colaborador> listaColaboradores = montarListaColaboradores(pstmt.executeQuery());

            cs.close();
            pstmt.close();
            rSetColaborador.close();

            return listaColaboradores;
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
            e.getErrorCode();
            throw new FetchingProblemException("Erro ao listar colaboradores.");
        }
    }

    /**
     * Method to set an collaborator.
     * 
     * @param row
     * @return colaborador
     * @throws SQLException 
     */
    public Colaborador montarColaborador(ResultSet row, boolean unico) throws SQLException {
        Colaborador colaborador = null;

        try {
            row.next();
            String nome = row.getString(3);
            String funcao = row.getString(4);
            Telefone telefone = new Telefone(Integer.parseInt(row.getString(5)));
            Email email = new Email(row.getString(6));
            colaborador = new Colaborador(nome, telefone, email, funcao);


            if (unico) row.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }

        if (colaborador != null) {
            return colaborador;
        } else {
            throw new FetchingProblemException("Problema ao montar colaborador");
        }

    }

    /**
     * Method to create a list of collaborators.
     * 
     * @param row
     * @return listaColaboradores
     */
    public ArrayList<Colaborador> montarListaColaboradores(ResultSet row) {

        ArrayList<Colaborador> listaColaboradores = new ArrayList<>();

        try {
            while (row.next()) {
                String nome = row.getString(3);
                String funcao = row.getString(4);
                Telefone telefone = new Telefone(Integer.parseInt(row.getString(5)));
                Email email = new Email(row.getString(6));
                listaColaboradores.add(new Colaborador(nome, telefone, email, funcao));
            }

            row.close();
        }catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }


        return listaColaboradores;

    }


    /**
     * Method to create a new collaborator.
     * 
     * @param nomeColaborador
     * @param contactoColaborador
     * @param emailColaborador
     * @param funcao
     * @return new Colaborador
     */
    public Colaborador criarColaborador(String nomeColaborador, int contactoColaborador,
                                        String emailColaborador, String funcao) {
        return new Colaborador(nomeColaborador, new Telefone(contactoColaborador),
                new Email(emailColaborador), funcao);
    }

}
