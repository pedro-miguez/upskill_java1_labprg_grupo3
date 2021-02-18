package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import network.ConnectionHandler;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class responsible for creating a repository to store information about
 * Task.
 */
public class RepositorioTarefa implements Serializable {

    private static RepositorioTarefa instance;
    private ConnectionHandler connectionHandler;

    public ArrayList<Tarefa> tarefasRegistadas;

    /**
     * Tasks that will be added (registered) in the repository.
     */
    private RepositorioTarefa() {
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Static method that returns a unique reference to the class object, which 
     * implements a singleton.
     * @return 
     */
    public static RepositorioTarefa getInstance() {
        if (instance == null) {
            instance = new RepositorioTarefa();
        }
        return instance;
    }

    /**
     * Boolean method that checks if a task exists in the repository, otherwise 
     * it is added to it.
     * @param tarefa
     * @return 
     */
    public boolean insertTarefa(Tarefa tarefa, String colaboradorEmail) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        try {
            conn.setAutoCommit(false);

            CallableStatement cs1 = conn.prepareCall("{? = call getOrganizacaoByEmailColaborador(?)}");
            cs1.registerOutParameter(1, Types.INTEGER);
            cs1.setString(2, colaboradorEmail.toString());
            cs1.executeUpdate();
            int orgID = cs1.getInt(1);

            PreparedStatement pstmt = conn.prepareStatement("select idCategoria from CategoriaTarefa where " +
                    "descricao = ? and " +
                    "idareaatividade = ?");
            pstmt.setString(1, tarefa.getCategoria().getDescricao());
            pstmt.setString(2, tarefa.getCategoria().getAreaAtividade().getCodigoUnico().toString());
            ResultSet result = pstmt.executeQuery();
            result.next();
            int idCategoriaTarefa = result.getInt(1);

            CallableStatement cs2 = conn.prepareCall("{CALL createTarefa(?, ?, ?, ?, ?, ?, ?, ?)}");
            cs1.setString(1, tarefa.getCodigoUnico().toString());
            cs1.setString(2, tarefa.getDesignacao());
            cs1.setString(3, tarefa.getDescricaoInformal());
            cs1.setString(4, tarefa.getDescricaoTecnica());
            cs1.setInt(5, tarefa.getDuracaoDias());
            cs1.setFloat(6, tarefa.getCusto());
            cs1.setInt(7, orgID);
            cs1.setInt(7, idCategoriaTarefa);
            cs1.executeQuery();

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
     * Method for obtaining a task using its unique code.
     * @param codigoUnico
     * @return 
     */
    public Tarefa getTarefaByCodigoUnico(CodigoUnico codigoUnico, String emailColaborador) {
        try {
            Connection conn = connectionHandler.openConnection();

            CallableStatement cs1 = conn.prepareCall("{? = call getOrganizacaoByEmailColaborador(?)}");
            cs1.registerOutParameter(1, Types.INTEGER);
            cs1.setString(2, emailColaborador.toString());
            cs1.executeUpdate();
            int idOrganizacao = cs1.getInt(1);

            String referenciaTarefa = codigoUnico.toString();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Tarefa where idOrganizacao = ? AND referenciaTarefa = ?");
            pstmt.setInt(1, idOrganizacao);
            pstmt.setString(2, referenciaTarefa);

            return montarTarefa(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhuma área de atividade com esse código único.");
        }
    }

    private Tarefa montarTarefa(ResultSet row) {
        Tarefa tarefa = null;

        try {
            row.next();
            CodigoUnico referenciaTarefa = new CodigoUnico(row.getString("referenciaTarefa"));
            String designacao = row.getString("designacao");
            String descricaoInformal = row.getString("descricaoInformal");
            String descricaoTecnica = row.getString("descricaoTecnica");
            int estimativaDuracao = row.getInt("estimativaDuracao");
            float estimativaCusto = row.getFloat("estimativaCusto");
            

            PreparedStatement pstmt = connectionHandler.openConnection().prepareStatement("SELECT * FROM EnderecoPostal where idOrganizacao = ?");
            pstmt.setInt(1, row.getInt(1));

            ResultSet rset = pstmt.executeQuery();

            rset.next();
            EnderecoPostal enderecoPostal = new EnderecoPostal(rset.getString(2), rset.getString(4), rset.getString(3));

            pstmt.close();
            org = new Organizacao(nomeorg, nif, website, telefone, email, enderecoPostal);

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }

        if (tarefa != null) {
            return tarefa;
        } else {
            throw new FetchingProblemException("Problema ao montar organização");
        }
    }

    public ArrayList<Tarefa> getTarefasOrganizacao (Organizacao organizacao) {
        ArrayList<Tarefa> tarefasOrganizacao = new ArrayList<>();

        for (Tarefa t : tarefasRegistadas) {
            if (t.getOrganizacao().equals(organizacao)) {
                tarefasOrganizacao.add(t);
            }
        }
        return tarefasOrganizacao;
    }

    public Tarefa criarTarefa(String codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                              int duracaoHoras, float custo, CategoriaTarefa categoriaTarefa, Organizacao org) {
        return new Tarefa(new CodigoUnico(codigoUnico), designacao, descricaoInformal, descricaoTecnica, duracaoHoras, custo
                , categoriaTarefa, org);
    }

    /**
     * Method for listing (registering) tasks.
     * @return 
     */
    public ArrayList<Tarefa> listarTarefas() {
        return new ArrayList<>(this.tarefasRegistadas);
    }

    /**
     * Method to check if two objects (in this case, tasks) are equals.
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositorioTarefa)) return false;

        RepositorioTarefa that = (RepositorioTarefa) o;

        return tarefasRegistadas.equals(that.tarefasRegistadas);
    }

}
