package persistence;

import java.sql.Types;

import domain.*;
import exceptions.EmailNaoAssociadoException;
import exceptions.GestorNaoRelacionadoANenhumaOrgException;
import jdk.internal.org.objectweb.asm.Type;
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

        Connection conn = openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createOrganizacao(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            cs.setInt(1, Integer.parseInt(organizacao.getNIF().toString()));
            cs.setString(2, organizacao.getEmail().toString());
            cs.setString(3, organizacao.getNome());
            cs.setInt(4, Integer.parseInt(organizacao.getTelefone().toString()));
            cs.setString(5, organizacao.getWebsite().toString());
            cs.setString(6, organizacao.getEnderecoPostal().getLocal());
            cs.setString(7, organizacao.getEnderecoPostal().getCodPostal());
            cs.setString(8, organizacao.getEnderecoPostal().getLocalidade());
            cs.setString(9, gestor.getNome());
            cs.setString(10, gestor.getEmail().toString());
            cs.setString(11, password);
            cs.setInt(12, Integer.parseInt(gestor.getTelefone().toString()));
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
     * Method for obtaining an organization through its manager.
     * @param colaborador
     * @return 
     */
    public Organizacao getOrganizacaoByGestor(Colaborador colaborador) {
        throw new GestorNaoRelacionadoANenhumaOrgException("Não existe nenhuma organização associada a este gestor");
    }

    public Organizacao getOrganizacaoByEmail(Email email) {
        try {
            Connection conn = openConnection();
            CallableStatement cs = conn.prepareCall ("{? = call getOrganizacaoByEmailColaborador(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, email.toString());
            cs.executeUpdate();

            int orgID = cs.getInt(1);

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Organizacao where idOrganizacao = ?");
            pstmt.setInt(1, orgID);

            return montarOrganizacao(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new EmailNaoAssociadoException("Não existe nenhuma organização associada a este e-mail.");
        }
    }

    public Organizacao montarOrganizacao(ResultSet row) throws SQLException {
        row.next();
        NIF nif = new NIF(Integer.parseInt(row.getString(2)));
        Email email = new Email(row.getString(3));
        String nomeorg = row.getString(5);
        Telefone telefone = new Telefone(Integer.parseInt(row.getString(6)));
        Website website = new Website(row.getString(7));

        PreparedStatement pstmt = openConnection().prepareStatement("SELECT * FROM EnderecoPostal where idOrganizacao = ?");
        pstmt.setInt(1, row.getInt(1));

        ResultSet rset = pstmt.executeQuery();

        rset.next();
        EnderecoPostal enderecoPostal = new EnderecoPostal(rset.getString(2), rset.getString(4), rset.getString(3));

        return new Organizacao(nomeorg, nif, website, telefone, email, enderecoPostal);
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
