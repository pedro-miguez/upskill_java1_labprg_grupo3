package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import network.ConnectionHandler;
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
    private ConnectionHandler connectionHandler;

    /**
     * Technical skills that will be added to the repository.
     */

    private RepositorioCompetenciaTecnica(){
        connectionHandler = new ConnectionHandler();
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
    public boolean insertCompetenciaTecnica(CompetenciaTecnica competenciaTecnica) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createCompetenciaTecnica(?, ?, ?, ?)}");

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

        return insertGrausProficiencia(competenciaTecnica);
    }

    public boolean insertGrausProficiencia(CompetenciaTecnica compTec) throws SQLException {
        Connection conn = connectionHandler.openConnection();
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
    public CompetenciaTecnica getCompetenciaTecnicaByCodUnico(CodigoUnico codigoUnico, AreaAtividade areaAtividade){
        try {
            Connection conn = connectionHandler.openConnection();
            String idCompetenciaTecnica = codigoUnico.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CompetenciaTecnica where idCompetenciaTecnica = ?");
            pstmt.setString(1, idCompetenciaTecnica);

            return montarCompetenciaTecnica(pstmt.executeQuery(), areaAtividade);
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
            Connection conn = connectionHandler.openConnection();
            String idAreaAtividade = areaAtividade.getCodigoUnico().toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CompetenciaTecnica where idAreaAtividade = ?");
            pstmt.setString(1, idAreaAtividade);

            return montarListaCompetenciaTecnica(pstmt.executeQuery(), areaAtividade);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
            e.getErrorCode();
            throw new SQLException("Erro ao listar competências técnicas.");
        }
    }

    private CompetenciaTecnica montarCompetenciaTecnica(ResultSet row, AreaAtividade areaAtividade) throws SQLException {
        Connection conn = connectionHandler.openConnection();
        CompetenciaTecnica competenciaTecnica = null;

        try {
            row.next();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ?");
            CodigoUnico idCompetenciaTecnica = new CodigoUnico(row.getString(1));
            pstmt.setString(1, idCompetenciaTecnica.toString());
            String descricaoBreve = row.getString(3);
            String descricaoDetalhada = row.getString(4);
            ArrayList <GrauProficiencia> graus = montarListaGrauProficiencia(pstmt.executeQuery());
            competenciaTecnica = new CompetenciaTecnica(idCompetenciaTecnica, areaAtividade, descricaoBreve, descricaoDetalhada, graus);
            conn.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }

        return competenciaTecnica;
    }

    private ArrayList<CompetenciaTecnica> montarListaCompetenciaTecnica(ResultSet rows, AreaAtividade areaAtividade) throws SQLException {
        ArrayList<CompetenciaTecnica> listaCompetencias = new ArrayList<>();
        Connection conn = connectionHandler.openConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ?");
            while (rows.next()) {
                CodigoUnico idCompetenciaTecnica = new CodigoUnico(rows.getString(1));
                pstmt.setString(1, idCompetenciaTecnica.toString());
                String descricaoBreve = rows.getString(3);
                String descricaoDetalhada = rows.getString(4);
                ArrayList<GrauProficiencia> graus = montarListaGrauProficiencia(pstmt.executeQuery());
                listaCompetencias.add(new CompetenciaTecnica(idCompetenciaTecnica, areaAtividade, descricaoBreve, descricaoDetalhada, graus));
                pstmt.clearParameters();
                pstmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }


        return listaCompetencias;

    }

    private ArrayList<GrauProficiencia> montarListaGrauProficiencia(ResultSet rows) throws SQLException {
        ArrayList<GrauProficiencia> listaGraus = new ArrayList<>();

        try {
            while(rows.next()) {
                int nivel = rows.getInt(2);
                String designacao = rows.getString(3);
                listaGraus.add(new GrauProficiencia(nivel, designacao));
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }


        return listaGraus;

    }

    public CompetenciaTecnica criarCompetenciaTecnica(String codigoUnico, AreaAtividade areaAtividade,
                                                      String descricao, String descDetalhada, List<GrauProficiencia> graus) {
        return new CompetenciaTecnica(new CodigoUnico(codigoUnico), areaAtividade, descricao, descDetalhada, graus);
    }


}
