package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
    public boolean createCompetenciaTecnica(CompetenciaTecnica competenciaTecnica){
        if(this.competenciasTecnicas.contains(competenciaTecnica)){
            return false;
        } else {
            this.competenciasTecnicas.add(competenciaTecnica);
            return true;
        }
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
        for (CompetenciaTecnica ct: competenciasTecnicas) {
            if(ct.getCodigoUnico() != null && ct.getCodigoUnico().equals(codigoUnico)) {
                return ct;
            }
        }
        throw new CodigoNaoAssociadoException("Não existe competência técnica com esse código único.");
    }

    /**
     * Method for obtaining technical skills on the list by their area of ​​activity.
     * @param areaAtividade
     * @return 
     */
    public ArrayList<CompetenciaTecnica> getCompetenciasTecnicasByAreaAtividade(AreaAtividade areaAtividade) {
        ArrayList<CompetenciaTecnica> competenciasAreaAtividade = new ArrayList<>();

        for (CompetenciaTecnica ct : competenciasTecnicas) {
            if (ct.getAreaAtividade().equals(areaAtividade)) {
                competenciasAreaAtividade.add(ct);
            }
        }

        return competenciasAreaAtividade;
    }

    public CompetenciaTecnica criarCompetenciaTecnica(String codigoUnico, AreaAtividade areaAtividade,
                                                      String descricao, String descDetalhada, List<GrauProficiencia> graus) {
        return new CompetenciaTecnica(new CodigoUnico(codigoUnico),
                areaAtividade,
                descricao, descDetalhada, graus);
    }

    /**
     * Method for listing technical skills.
     * @return 
     */
    public ArrayList<CompetenciaTecnica> listarCompetenciasTecnicas(){
        return  new ArrayList<>(this.competenciasTecnicas);
    }


}
