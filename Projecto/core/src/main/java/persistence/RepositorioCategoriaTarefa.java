package persistence;

import domain.*;
import exceptions.NomeNaoAssociadoException;
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
        for (CategoriaTarefa catt : categoriasTarefas) {
            if(catt.getDescricao() != null && catt.getDescricao().equals(descricao)) {
                return catt;
            }
        } throw new NomeNaoAssociadoException("Não existe nenhuma categoria de tarefa com esse nome.");
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
    public ArrayList<CategoriaTarefa> listarCategoriasTarefa(){
        return  new ArrayList<>(this.categoriasTarefas);
    }

}
