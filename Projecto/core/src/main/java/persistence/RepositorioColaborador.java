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
    private ConnectionHandler connectionHandler;

    /**
     * Collaborators that will be added to the repository.
     */
    private RepositorioColaborador() {
        connectionHandler = new ConnectionHandler();

    }

    /**
     * Static method that returns a unique reference to the class object, which
     * implements a singleton.
     *
     * @return
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
     * @return
     */
    public boolean insertUtilizadorColaborador(Colaborador colaborador, String password, String emailGestor) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        try {
            conn.setAutoCommit(false);

            CallableStatement cs1 = conn.prepareCall("{? = call getOrganizacaoByEmailColaborador(?)}");
            cs1.registerOutParameter(1, Types.INTEGER);
            cs1.setString(2, emailGestor.toString());
            cs1.executeUpdate();

            int orgID = cs1.getInt(1);

            CallableStatement cs2 = conn.prepareCall("{CALL createUtilizadorColaborador(?, ?, ?, ?, ?, ?)}");
            cs1.setString(1, colaborador.getNome());
            cs1.setString(2, colaborador.getEmail().toString());
            cs1.setString(3, password);
            cs1.setInt(4, Integer.parseInt(colaborador.getTelefone().toString()));
            cs1.setString(5, colaborador.getFuncao());
            cs1.setInt(6, orgID);

            cs1.executeQuery();

            conn.commit();

            conn.close();
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

        conn.close();
        return false;
    }

    /**
     * Method to obtain a collaborator through your email.
     *
     * @param email
     * @return
     */
    public Colaborador getColaboradorByEmail(Email email) throws SQLException {
        try {
            Connection conn = connectionHandler.openConnection();
            String emailColaborador = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Colaborador where Email = ?");
            pstmt.setString(1, emailColaborador);

            return montarColaborador(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum colaborador com esse email.");
        }

    }

    public ArrayList<Colaborador> getColaboradoresOrganizacaoByEmail(Email email){
        try {
            Connection conn = connectionHandler.openConnection();

            CallableStatement cs = conn.prepareCall("SELECT idOrganizacao FROM Colaborador WHERE email = ?");
            cs.setString(1, email.toString());

            int orgID = cs.getInt("idOrganizacao");

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Colaborador WHERE idOrganizacao = ?");
            pstmt.setInt(1, orgID);

            return montarListaColaboradores(pstmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
            e.getErrorCode();
            throw new FetchingProblemException("Erro ao listar colaboradores.");
        }
    }

    public Colaborador montarColaborador(ResultSet row) throws SQLException {
        Colaborador colaborador = null;

        try {
            row.next();
            String nome = row.getString(3);
            String funcao = row.getString(4);
            Telefone telefone = new Telefone(Integer.parseInt(row.getString(5)));
            Email email = new Email(row.getString(6));
            colaborador = new Colaborador(nome, telefone, email, funcao);
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
        }catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        if (listaColaboradores.size() != 0) {
            return listaColaboradores;
        } else {
            throw new FetchingProblemException("Lista de Competências técnicas vazia");
        }
    }


    public Colaborador criarColaborador(String nomeColaborador, int contactoColaborador,
                                        String emailColaborador, String funcao) {
        return new Colaborador(nomeColaborador, new Telefone(contactoColaborador),
                new Email(emailColaborador), funcao);
    }

}
