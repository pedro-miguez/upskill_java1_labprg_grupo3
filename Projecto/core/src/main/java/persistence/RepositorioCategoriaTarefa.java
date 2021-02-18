package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.NomeNaoAssociadoException;
import oracle.jdbc.pool.OracleDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * task categories.
 */
public class RepositorioCategoriaTarefa implements Serializable {

    private static RepositorioCategoriaTarefa instance;

    private List<CategoriaTarefa> categoriasTarefas;

    public Connection openConnection() throws SQLException {
        OracleDataSource ods = new OracleDataSource();
        String url = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
        ods.setURL(url);
        ods.setUser("UPSKILL_BD_TURMA1_14");
        ods.setPassword("qwerty");
        return  ods.getConnection();
    }

    /**
     * Task categories that will be added to the repository.
     */
    private RepositorioCategoriaTarefa(){
        categoriasTarefas = new ArrayList<>();
    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioCategoriaTarefa getInstance(){
        if (instance == null){
            instance = new RepositorioCategoriaTarefa();
        }
        return instance;
    }

    /**
     * Boolean method that checks if an task category exists in the repository, 
     * otherwise it is added to it.
     * @param categoriaTarefa the object CategoriaTarefa to add to the repository
     * @return 
     */
    public boolean createCategoriaTarefa(CategoriaTarefa categoriaTarefa) throws SQLException {
        Connection conn = openConnection();

        CallableStatement cs = conn.prepareCall ("{CALL createCategoriaTarefa(?, ?)}");
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

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
        return createCaracterizacoesCompetenciaTecnica(categoriaTarefa);
    }


    public boolean createCaracterizacoesCompetenciaTecnica(CategoriaTarefa categoriaTarefa) throws SQLException {
        Connection conn = openConnection();
        PreparedStatement pstmt = conn.prepareStatement("select idCategoria from CategoriaTarefa where " +
                "descricao = ? and " +
                "idareaatividade = ?");
        pstmt.setString(1, categoriaTarefa.getDescricao());
        pstmt.setString(2, categoriaTarefa.getAreaAtividade().getCodigoUnico().toString());
        int idCategoriaTarefa = pstmt.executeQuery().getInt(1);

        ArrayList<CaracterizacaoCompTec> listaCompetencias = categoriaTarefa.getCompetenciasTecnicas();

        try {
            conn.setAutoCommit(false);
            CallableStatement cs = conn.prepareCall ("{CALL createCaraterizacaoCompetenciaTecnica(?, ?, ?, ?)}");

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
     * @param descricao the name of a task category to search for
     * @return the matching task category
     */
    public CategoriaTarefa getCategoriaTarefaByDescricaoAndAreaAtividade(String descricao, AreaAtividade areaAtividade){
        try {
            Connection conn = openConnection();
            String idAreaAtividade = areaAtividade.getCodigoUnico().toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CategoriaTarefa where descricao = ? AND idAreaAtividade = ?");
            pstmt.setString(1, descricao);
            pstmt.setString(2, idAreaAtividade);

            return montarCategoriaTarefa(pstmt.executeQuery(), areaAtividade);
        } catch (SQLException e) {
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
     * @return 
     */
    public ArrayList<CategoriaTarefa> listarCategoriasTarefa() throws SQLException {
        Connection conn = openConnection();

        ArrayList<CategoriaTarefa> listaTodasCategorias = new ArrayList<>();
        ArrayList<AreaAtividade> listaTodasAreas = listarAreasAtividade();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CategoriaTarefa where idAreaAtividade = ?");

        for (AreaAtividade a: listaTodasAreas) {
            pstmt.setString(1, a.getCodigoUnico().toString());

            listaTodasCategorias.addAll(montarListaCategoriasTarefa(pstmt.executeQuery(), a));
        }


        return  listaTodasCategorias;
    }

    private CategoriaTarefa montarCategoriaTarefa(ResultSet row, AreaAtividade areaAtividade) throws SQLException {
        row.next();
        Connection conn = openConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CaraterizacaoCompetenciaTecnica where idCategoria = ?");
        int idCategoriaTarefa = row.getInt(1);
        pstmt.setInt(1, idCategoriaTarefa);
        String descricao = row.getString(3);
        ArrayList <CaracterizacaoCompTec> competencias = montarlistaCaracterizacaoCompetenciaTecnica(pstmt.executeQuery(), areaAtividade);

        return new CategoriaTarefa(areaAtividade, descricao, competencias);
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



    private ArrayList<CategoriaTarefa> montarListaCategoriasTarefa(ResultSet rows, AreaAtividade areaAtividade) throws SQLException {
        ArrayList<CategoriaTarefa> listaCategorias = new ArrayList<>();
        Connection conn = openConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CaraterizacaoCompetenciaTecnica where idCategoria = ?");
        while(rows.next()) {
            int idCategoriaTarefa = rows.getInt(1);
            pstmt.setInt(1, idCategoriaTarefa);
            String descricao = rows.getString(3);
            ArrayList <CaracterizacaoCompTec> competencias = montarlistaCaracterizacaoCompetenciaTecnica(pstmt.executeQuery(), areaAtividade);
            listaCategorias.add(new CategoriaTarefa(areaAtividade, descricao, competencias));
            pstmt.clearParameters();
        }

        return listaCategorias;

    }

    private ArrayList<CaracterizacaoCompTec> montarlistaCaracterizacaoCompetenciaTecnica(ResultSet rows, AreaAtividade areaAtividade) throws SQLException {
            Connection conn = openConnection();

            ArrayList<CaracterizacaoCompTec> competencias = new ArrayList<>();
            boolean obrigatorio;
            CompetenciaTecnica competencia;
            GrauProficiencia grau;

            while(rows.next()) {

                //montar competencia tecnica
                PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM Competenciatecnica where idCompetenciaTecnica = ?");
                int idCompetenciatecnica = rows.getInt(1);
                pstmt1.setInt(1, idCompetenciatecnica);
                competencia = montarCompetenciaTecnica(pstmt1.executeQuery(), areaAtividade);

                //obrigatoriedade
                if (rows.getString(3).equals("OBR")) {
                    obrigatorio = true;
                } else {
                    obrigatorio = false;
                }

                //montar grau proficiencia
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ? AND nivel = ?");
                pstmt2.setInt(1, idCompetenciatecnica);
                pstmt2.setInt(2, rows.getInt(4));
                ResultSet linhaGrau = pstmt2.executeQuery();
                grau = new GrauProficiencia(linhaGrau.getInt(2), linhaGrau.getString(3));
                competencias.add(new CaracterizacaoCompTec(competencia, obrigatorio, grau));
            }

            conn.close();
            return competencias;

    }

    private CompetenciaTecnica montarCompetenciaTecnica(ResultSet row, AreaAtividade areaAtividade) throws SQLException {
        row.next();
        Connection conn = openConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ?");
        CodigoUnico idCompetenciaTecnica = new CodigoUnico(row.getString(1));
        pstmt.setString(1, idCompetenciaTecnica.toString());
        String descricaoBreve = row.getString(3);
        String descricaoDetalhada = row.getString(4);
        ArrayList <GrauProficiencia> graus = montarListaGrauProficiencia(pstmt.executeQuery());

        conn.close();
        return new CompetenciaTecnica(idCompetenciaTecnica, areaAtividade, descricaoBreve, descricaoDetalhada, graus);
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

}
