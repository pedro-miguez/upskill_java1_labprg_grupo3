package persistence;

import domain.*;
import exceptions.FetchingProblemException;
import exceptions.NomeNaoAssociadoException;
import network.ConnectionHandler;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * task categories.
 */
public class RepositorioCategoriaTarefa implements Serializable {

    private static RepositorioCategoriaTarefa instance;
    private ConnectionHandler connectionHandler;

    /**
     * Task categories that will be added to the repository.
     */
    private RepositorioCategoriaTarefa() {
        connectionHandler = new ConnectionHandler();

    }

    /**
     * Static method that returns a unique reference to the class object, which
     * implements a singleton.
     *
     * @return
     */
    public static RepositorioCategoriaTarefa getInstance() {
        if (instance == null) {
            instance = new RepositorioCategoriaTarefa();
        }
        return instance;
    }

    /**
     * Boolean method that checks if an task category exists in the repository,
     * otherwise it is added to it.
     *
     * @param categoriaTarefa the object CategoriaTarefa to add to the repository
     * @return
     */
    public boolean insertCategoriaTarefa(CategoriaTarefa categoriaTarefa) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        try {
            conn.setAutoCommit(false);

            CallableStatement cs = conn.prepareCall("{CALL createCategoriaTarefa(?, ?)}");

            cs.setString(1, categoriaTarefa.getAreaAtividade().getCodigoUnico().toString());
            cs.setString(2, categoriaTarefa.getDescricao());
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
        return insertCaracterizacoesCompetenciaTecnica(categoriaTarefa);
    }


    public boolean insertCaracterizacoesCompetenciaTecnica(CategoriaTarefa categoriaTarefa) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        ArrayList<CaracterizacaoCompTec> listaCompetencias = categoriaTarefa.getCompetenciasTecnicas();


        try {
            PreparedStatement pstmt = conn.prepareStatement("select idCategoria from CategoriaTarefa where " +
                    "descricao = ? and " +
                    "idareaatividade = ?");
            pstmt.setString(1, categoriaTarefa.getDescricao());
            pstmt.setString(2, categoriaTarefa.getAreaAtividade().getCodigoUnico().toString());
            ResultSet result = pstmt.executeQuery();
            result.next();
            int idCategoriaTarefa = result.getInt(1);

            conn.setAutoCommit(false);

            CallableStatement cs = conn.prepareCall("{CALL createCaraterizacaoCompetenciaTecnica(?, ?, ?, ?)}");

            for (CaracterizacaoCompTec cpt : listaCompetencias) {
                cs.setString(1, cpt.getCodigoUnicoCompTec().toString());
                cs.setInt(2, idCategoriaTarefa);
                cs.setString(3, (cpt.isObrigatorio() ? "OBR" : "OPC"));
                cs.setInt(4, cpt.getGrauProficiencia().getNivel());

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
     * Method for obtaining a task category through its description.
     *
     * @param descricao the name of a task category to search for
     * @return the matching task category
     */
    public CategoriaTarefa getCategoriaTarefaByDescricaoAndAreaAtividade(String descricao, AreaAtividade areaAtividade) {
        try {
            Connection conn = connectionHandler.openConnection();
            String idAreaAtividade = areaAtividade.getCodigoUnico().toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CategoriaTarefa where descricao = ? AND idAreaAtividade = ?");
            pstmt.setString(1, descricao);
            pstmt.setString(2, idAreaAtividade);

            return montarCategoriaTarefa(pstmt.executeQuery(), areaAtividade);
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new NomeNaoAssociadoException("Não existe nenhuma categoria de tarefa com esse nome.");
        }
    }

    public CategoriaTarefa criarCategoriaTarefa(AreaAtividade areaAtividade, String descricao,
                                                List<CaracterizacaoCompTec> competenciasTecnicas) {
        return new CategoriaTarefa(areaAtividade,
                descricao, new ArrayList<>(competenciasTecnicas));
    }

    /**
     * Method for listing task categories.
     *
     * @return
     */
    public ArrayList<CategoriaTarefa> listarCategoriasTarefa() throws SQLException {
        Connection conn = connectionHandler.openConnection();
        ArrayList<CategoriaTarefa> listaTodasCategorias = new ArrayList<>();
        ArrayList<AreaAtividade> listaTodasAreas = RepositorioAreaAtividade.getInstance().listarAreasAtividade();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CategoriaTarefa where idAreaAtividade = ?");

            for (AreaAtividade a : listaTodasAreas) {
                pstmt.setString(1, a.getCodigoUnico().toString());

                for (CategoriaTarefa cat : montarListaCategoriasTarefa(pstmt.executeQuery(), a)) {
                    if (!listaTodasCategorias.contains(cat)) {
                        listaTodasCategorias.add(cat);
                    }
                }

                pstmt.clearParameters();
            }
            conn.close();
            pstmt.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return listaTodasCategorias;

    }

    public CategoriaTarefa montarCategoriaTarefa(ResultSet row, AreaAtividade areaAtividade) throws SQLException {

        Connection conn = connectionHandler.openConnection();
        CategoriaTarefa categoriaTarefa = null;

        try {
            row.next();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CaraterizacaoCompetenciaTecnica where idCategoria = ?");
            int idCategoriaTarefa = row.getInt(1);
            pstmt.setInt(1, idCategoriaTarefa);
            String descricao = row.getString(3);
            ArrayList<CaracterizacaoCompTec> competencias = montarlistaCaracterizacaoCompetenciaTecnica(pstmt.executeQuery(), areaAtividade);
            categoriaTarefa = new CategoriaTarefa(areaAtividade, descricao, competencias);
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        if (categoriaTarefa != null) {
            return categoriaTarefa;
        } else {
            throw new FetchingProblemException("Problema ao montar categoria de tarefa");
        }
    }



    public ArrayList<CategoriaTarefa> montarListaCategoriasTarefa(ResultSet rows, AreaAtividade areaAtividade) throws SQLException {
        Connection conn = connectionHandler.openConnection();
        ArrayList<CategoriaTarefa> listaCategorias = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CaraterizacaoCompetenciaTecnica where idCategoria = ?");
            while (rows.next()) {
                int idCategoriaTarefa = rows.getInt(1);
                pstmt.setInt(1, idCategoriaTarefa);
                String descricao = rows.getString(3);
                ArrayList<CaracterizacaoCompTec> competencias = montarlistaCaracterizacaoCompetenciaTecnica(pstmt.executeQuery(), areaAtividade);
                listaCategorias.add(new CategoriaTarefa(areaAtividade, descricao, competencias));
                pstmt.clearParameters();
            }

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }


        return listaCategorias;

    }

    public ArrayList<CaracterizacaoCompTec> montarlistaCaracterizacaoCompetenciaTecnica(ResultSet rows, AreaAtividade areaAtividade) throws SQLException {
        Connection conn = connectionHandler.openConnection();
        ArrayList<CaracterizacaoCompTec> competencias = new ArrayList<>();
        boolean obrigatorio;
        CompetenciaTecnica competencia;
        GrauProficiencia grau;
        try {
            while (rows.next()) {

                //montar competencia tecnica
                PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM CompetenciaTecnica where idCompetenciaTecnica = ?");
                String idCompetenciatecnica = rows.getString(1);
                pstmt1.setString(1, idCompetenciatecnica);
                competencia = RepositorioCompetenciaTecnica.getInstance().montarCompetenciaTecnica(pstmt1.executeQuery(), areaAtividade);

                //obrigatoriedade
                if (rows.getString(3).equals("OBR")) {
                    obrigatorio = true;
                } else {
                    obrigatorio = false;
                }

                //montar grau proficiencia
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ? AND nivel = ?");
                pstmt2.setString(1, idCompetenciatecnica);
                pstmt2.setInt(2, rows.getInt(4));
                ResultSet linhaGrau = pstmt2.executeQuery();
                linhaGrau.next();
                grau = new GrauProficiencia(linhaGrau.getInt(2), linhaGrau.getString(3));
                competencias.add(new CaracterizacaoCompTec(competencia, obrigatorio, grau));
            }

            conn.close();
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return competencias;
    }





}
