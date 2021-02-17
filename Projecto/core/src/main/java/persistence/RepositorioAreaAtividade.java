package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.EmailNaoAssociadoException;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for creating a repository to store information about
 * activity areas.
 */
public class RepositorioAreaAtividade implements Serializable {

    private static RepositorioAreaAtividade instance;

    public Connection openConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        return  ods.getConnection();
    }

    /**
     * Activity areas that will be added to the repository.
     */
    private RepositorioAreaAtividade(){

    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioAreaAtividade getInstance(){
        if (instance == null){
            instance = new RepositorioAreaAtividade();
        }
        return instance;
    }

    /**
     * Boolean method that checks if an area of ​​activity exists in the repository, 
     * otherwise it is added to it.
     * @param areaAtividade
     * @return 
     */
    public boolean createAreaAtividade(AreaAtividade areaAtividade) throws SQLException {
        Connection conn = openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createAreaAtividade(?, ?, ?)}");
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            cs.setString(1, areaAtividade.getCodigoUnico().toString());
            cs.setString(2, areaAtividade.getDescricao());
            cs.setString(3, areaAtividade.getDescDetalhada());

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
     * Method for obtaining an area of ​​activity using its unique code.
     * @param codigoUnico
     * @return 
     */
    public AreaAtividade getAreaAtividadeByCodUnico(CodigoUnico codigoUnico){
        try {
            Connection conn = openConnection();
            String idAreaAtividade = codigoUnico.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM AreaAtividade where idAreaAtividade = ?");
            pstmt.setString(1, idAreaAtividade);

            return montarAreaAtividade(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhuma área de atividade com esse código único.");
        }
    }

    public ArrayList<AreaAtividade> listarAreasAtividade() throws SQLException {
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM AreaAtividade");

            return montarListaAreaAtividade(pstmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
            e.getErrorCode();
            throw new SQLException("Erro ao listar áreas de atividade.");
        }
    }

    public AreaAtividade montarAreaAtividade(ResultSet row) throws SQLException {
        row.next();
        CodigoUnico idAreaAtividade = new CodigoUnico(row.getString(1));
        String descricaoBreve = row.getString(2);
        String descricaoDetalhada = row.getString(3);

        return new AreaAtividade(idAreaAtividade, descricaoBreve, descricaoDetalhada);
    }

    public ArrayList<AreaAtividade> montarListaAreaAtividade(ResultSet row) throws SQLException {
        ArrayList<AreaAtividade> listaAreas = new ArrayList<>();

        while(row.next()) {
            CodigoUnico idAreaAtividade = new CodigoUnico(row.getString(1));
            String descricaoBreve = row.getString(2);
            String descricaoDetalhada = row.getString(3);
            listaAreas.add(new AreaAtividade(idAreaAtividade, descricaoBreve, descricaoDetalhada));
        }

        return listaAreas;
    }



    public AreaAtividade criarAreaAtividade(String codigoUnico, String descricao, String descricaoDetalhada) {
        return new AreaAtividade(new CodigoUnico(codigoUnico), descricao, descricaoDetalhada);
    }


}
