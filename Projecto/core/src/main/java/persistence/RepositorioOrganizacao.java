package persistence;

import application.UsersAPI;
import domain.*;
import exceptions.EmailNaoAssociadoException;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * organization.
 */
public class RepositorioOrganizacao implements Serializable {

    private static RepositorioOrganizacao instance;

    /**
     * Organizations that will be added (registered) to the repository.
     */
    private RepositorioOrganizacao() throws SQLException {

    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioOrganizacao getInstance() throws SQLException {
        if(instance == null){
            instance = new RepositorioOrganizacao();
        }
        return instance;
    }

    public Connection openConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        return  ods.getConnection();
    }

    /**
     * Boolean method that checks if an organization exists in the repository, 
     * otherwise it is added to it.
     * @param organizacao
     * @return 
     */
    public boolean createOrganizacao(Organizacao organizacao, Colaborador gestor, String password) throws SQLException {

        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        Connection conn = openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createOrganizacao(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        System.out.println(1);
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            cs.setInt(1, Integer.parseInt(organizacao.getNIF().toString()));
            cs.setString(2, organizacao.getEmail().toString());
            cs.setString(3, organizacao.getNome());
            cs.setInt(4, Integer.parseInt(organizacao.getTelefone().toString()));
            cs.setString(5, organizacao.getWebsite().toString());
            cs.setString(6, gestor.getNome());
            cs.setString(7, gestor.getEmail().toString());
            cs.setString(8, password);
            cs.setInt(9, Integer.parseInt(gestor.getTelefone().toString()));
            //rs = cs.executeQuery();

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
        //ADICIONAR GETORGANIZACAO TO STRING;
        return false;

    }

    /**
     * Boolean method that checks if a manager exists in the organization, 
     * otherwise it is added (as being a collaborator).
     * @param colaborador
     * @param organizacao
     * @return 
     */



    /**
     * Method for obtaining an organization through its manager.
     * @param colaborador
     * @return 
     */
    public Organizacao getOrganizacaoByGestor(Colaborador colaborador) {
        throw new GestorNaoRelacionadoANenhumaOrgException("Não existe nenhuma organização associada a este gestor");
    }

    public Organizacao getOrganizacaoByEmail(Email email) {
        throw new EmailNaoAssociadoException("Não existe nenhuma organização associada a este e-mail.");
    }

    /**
     * Method for listing (registering) organizations.
     * @return 
     */

    public Organizacao criarOrganizacao(String nomeOrg, int nif, String website, int telefone,
                                        String email, String rua, String localidade, String codigoPostal) {
        return new Organizacao(nomeOrg, new NIF(nif), new Website(website),
                new Telefone(telefone), new Email(email) , new EnderecoPostal(rua, localidade, codigoPostal));
    }


}
