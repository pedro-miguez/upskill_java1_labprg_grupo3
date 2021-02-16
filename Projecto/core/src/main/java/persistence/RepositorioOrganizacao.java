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
    private Connection conn;

    /**
     * Organizations that will be added (registered) to the repository.
     */
    private RepositorioOrganizacao() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        conn = ods.getConnection();
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
        conn = ods.getConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createOrganizacao(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        System.out.println(1);
        ResultSet rs = null;

        try {
            System.out.println(1.1);
            conn.setAutoCommit(false);

            System.out.println(1.2);

            cs.setInt(1, Integer.parseInt(organizacao.getNIF().toString()));
            System.out.println(2);
            cs.setString(2, organizacao.getEmail().toString());
            System.out.println(3);
            cs.setString(3, organizacao.getNome());
            System.out.println(4);
            cs.setInt(4, Integer.parseInt(organizacao.getTelefone().toString()));
            System.out.println(5);
            cs.setString(5, organizacao.getWebsite().toString());
            System.out.println(6);
            cs.setString(6, gestor.getNome());
            System.out.println(7);
            cs.setString(7, gestor.getEmail().toString());
            System.out.println(8);
            cs.setString(8, password);
            System.out.println(9);
            cs.setInt(9, Integer.parseInt(gestor.getTelefone().toString()));
            System.out.println(10);
            //rs = cs.executeQuery();
            cs.executeQuery();
            System.out.println(11);
            conn.commit();
            System.out.println(12);
            return true;
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    excep.getErrorCode();
                }
            }
        }

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
