package persistence;

import domain.*;
import exceptions.EmailNaoAssociadoException;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * collaborator.
 */
public class RepositorioColaborador implements Serializable {

    private static RepositorioColaborador instance;

    public Connection openConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        return  ods.getConnection();
    }

    /**
     * Collaborators that will be added to the repository.
     */
    private RepositorioColaborador(){

    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioColaborador getInstance(){
        if(instance == null){
            instance = new RepositorioColaborador();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a collaborator exists in the repository, 
     * otherwise it is added to it.
     * @param colaborador
     * @return 
     */
    public boolean createUtilizadorColaborador(Colaborador colaborador, String password, String emailGestor) throws SQLException {
        Connection conn = openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createUtilizadorColaborador(?, ?, ?, ?, ?, ?)}");
        ResultSet rs = null;

        CallableStatement css = conn.prepareCall ("{? = call getOrganizacaoByEmailColaborador(?)}");
        css.registerOutParameter(1, Types.INTEGER);
        css.setString(2, emailGestor.toString());
        css.executeUpdate();

        int orgID = css.getInt(1);

        try {
            conn.setAutoCommit(false);

            cs.setString(1, colaborador.getNome());
            cs.setString(2, colaborador.getEmail().toString());
            cs.setString(3, password);
            cs.setInt(4, Integer.parseInt(colaborador.getTelefone().toString()));
            cs.setString(5, colaborador.getFuncao());
            cs.setInt(6, orgID);

            cs.executeQuery();

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
     * @param email
     * @return 
     */
    public Colaborador getColaboradorByEmail(Email email) {
        for (Colaborador c : colaboradoresRegistados) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }

        throw new EmailNaoAssociadoException(email.toString() + " não está associado a nenhum colaborador");

    }

    /**
     * Method for obtaining a collaborator from a given organization.
     * @param organizacao
     * @return 
     */
    public ArrayList<Colaborador> getColaboradoresOrganizacao (Organizacao organizacao) {
        ArrayList<Colaborador> colaboradoresOrganizacao = new ArrayList<>();


        return colaboradoresOrganizacao;
    }

    /**
     * Method for listing collaborators.
     * @return 
     */
    public ArrayList<Colaborador> listarColaboradores() {
        return new ArrayList<>(this.colaboradoresRegistados);
    }

    public Colaborador criarColaborador(String nomeColaborador,int contactoColaborador,
                                        String emailColaborador, String funcao) {
        return new Colaborador(nomeColaborador, new Telefone(contactoColaborador),
                new Email(emailColaborador), funcao);
    }

}
