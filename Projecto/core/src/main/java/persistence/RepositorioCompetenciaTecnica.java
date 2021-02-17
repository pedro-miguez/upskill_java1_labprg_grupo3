package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * technical competence.
 */
public class RepositorioCompetenciaTecnica implements Serializable {

    private static RepositorioCompetenciaTecnica instance;

    private List<CompetenciaTecnica> competenciasTecnicas;

    /**
     * Technical skills that will be added to the repository.
     */
    private RepositorioCompetenciaTecnica(){
        competenciasTecnicas = new ArrayList<>();
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
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioCompetenciaTecnica getInstance(){
        if (instance == null){
            instance = new RepositorioCompetenciaTecnica();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a technical competence exists in the repository, 
     * otherwise it is added to it.
     * @param competenciaTecnica
     * @return 
     */
    public boolean createCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) throws SQLException {
        Connection conn = openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createCompetenciaTecnica(?, ?, ?, ?)}");
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            cs.setString(1, competenciaTecnica.getCodigoUnico().toString());
            cs.setString(2, competenciaTecnica.getDescricao());
            cs.setString(3, competenciaTecnica.getDescDetalhada());
            cs.setString(4, competenciaTecnica.getAreaAtividade().getCodigoUnico().toString());

            cs.executeQuery();

            conn.commit();

            conn.close();
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
        return createGrausProficiencia(competenciaTecnica);
    }

    public boolean createGrausProficiencia(CompetenciaTecnica compTec) throws SQLException {
        Connection conn = openConnection();
        String idCompetenciaTecnica = compTec.getCodigoUnico().toString();
        ArrayList<GrauProficiencia> listaGraus = compTec.getGraus();

        try {
            conn.setAutoCommit(false);
            CallableStatement cs = conn.prepareCall ("{CALL createGrauProficiencia(?, ?, ?)}");

            for (GrauProficiencia gp : listaGraus) {
                cs.setString(1, idCompetenciaTecnica);
                cs.setInt(2, gp.getNivel());
                cs.setString(3, gp.getDesginacao());

                cs.executeQuery();
                cs.clearParameters();
            }
            cs.close();
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
     * Method for obtaining technical competence through its unique code.
     * @param codigoUnico
     * @return 
     */
    public CompetenciaTecnica getCompetenciaTecnicaByCodUnico(CodigoUnico codigoUnico){
        try {
            Connection conn = openConnection();
            String idCompetenciaTecnica = codigoUnico.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CompetenciaTecnica where idCompetenciaTecnica = ?");
            pstmt.setString(1, idCompetenciaTecnica);

            return montarCompetenciaTecnica(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhuma Competência Técnica com esse código único.");
        }
    }

    /**
     * Method for obtaining technical skills on the list by their area of activity.
     * @param areaAtividade
     * @return 
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) throws SQLException {
        try {
            Connection conn = openConnection();
            String idAreaAtividade = areaAtividade.getCodigoUnico().toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CompetenciaTecnica where idAreaAtividade = ?");
            pstmt.setString(1, idAreaAtividade);

            return montarListaCompetenciaTecnica(pstmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
            e.getErrorCode();
            throw new SQLException("Erro ao listar competências técnicas.");
        }
    }

    private CompetenciaTecnica montarCompetenciaTecnica(ResultSet row, AreaAtividade areaAtividade) throws SQLException {
        row.next();
        Connection conn = openConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ?");

        CodigoUnico idCompetenciaTecnica = new CodigoUnico(row.getString(1));
        String descricaoBreve = row.getString(3);
        String descricaoDetalhada = row.getString(4);
        ArrayList <GrauProficiencia>

        return new ;
    }

    private ArrayList<CompetenciaTecnica> montarListaCompetenciaTecnica(ResultSet rows) {
    }

    private ArrayList<GrauProficiencia> montarListaGrauProficiencia(ResultSet rows) throws SQLException {
        ArrayList<GrauProficiencia> listaGraus = new ArrayList<>();

        while(rows.next()) {
            int nivel = rows.getInt(2);
            String designacao = rows.getString(3);
            listaGraus.add(new GrauProficiencia(nivel, designacao));
        }

        return listaGraus;
    }

    public CompetenciaTecnica criarCompetenciaTecnica(String codigoUnico, AreaAtividade areaAtividade,
                                                      String descricao, String descDetalhada, List<GrauProficiencia> graus) {
        return new CompetenciaTecnica(new CodigoUnico(codigoUnico), areaAtividade, descricao, descDetalhada, graus);
    }


}
