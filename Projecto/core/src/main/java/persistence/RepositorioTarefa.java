package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import exceptions.NomeNaoAssociadoException;
import network.ConnectionHandler;

import javax.xml.transform.Result;
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


    public ArrayList<Tarefa> montarTarefas(ResultSet rSetTarefa) {
        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        try {
            while (rSetTarefa.next()) {
                listaTarefas.add(montarTarefa(rSetTarefa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
        }

        if (listaTarefas.size() != 0) {
            return listaTarefas;
        } else {
            throw new FetchingProblemException("Lista de Tarefas vazia");
        }

    }

    public Tarefa montarTarefa(ResultSet rSetTarefa) {
        Tarefa tarefa = null;

        try {
            Connection conn = connectionHandler.openConnection();
            rSetTarefa.next();

            //Parâmetros da Tarefa disponíveis
            CodigoUnico referenciaTarefa = new CodigoUnico(rSetTarefa.getString("referenciaTarefa"));
            String designacao = rSetTarefa.getString("designacao");
            String descricaoInformal = rSetTarefa.getString("descricaoInformal");
            String descricaoTecnica = rSetTarefa.getString("descricaoTecnica");
            int estimativaDuracao = rSetTarefa.getInt("estimativaDuracao");
            float estimativaCusto = rSetTarefa.getFloat("estimativaCusto");


            //Construir Objecto Organização
            PreparedStatement pstmtOrg = connectionHandler.openConnection().prepareStatement("SELECT * FROM " +
                    "Organizacao where idOrganizacao = ?");
            pstmtOrg.setInt(1, rSetTarefa.getInt( "idOrganizacao"));
            ResultSet rSetOrg = pstmtOrg.executeQuery();
            rSetOrg.next();

            NIF nif = new NIF(Integer.parseInt(rSetOrg.getString("NIF")));
            Email email = new Email(rSetOrg.getString("email"));
            String nomeorg = rSetOrg.getString("nome");
            Telefone telefone = new Telefone(Integer.parseInt(rSetOrg.getString("telefone")));
            Website website = new Website(rSetOrg.getString("website"));

            PreparedStatement pstmtEndereco = connectionHandler.openConnection().prepareStatement("SELECT * FROM " +
                    "EnderecoPostal where idOrganizacao = ?");
            pstmtEndereco.setInt(1, rSetTarefa.getInt(1));

            ResultSet rsetEndereco = pstmtEndereco.executeQuery();

            rsetEndereco.next();
            EnderecoPostal enderecoPostal = new EnderecoPostal(rsetEndereco.getString("morada"),
                    rsetEndereco.getString("localidade"), rsetEndereco.getString("codigoPostal"));

            pstmtEndereco.close();
            Organizacao org = new Organizacao(nomeorg, nif, website, telefone, email, enderecoPostal);


            //Construir Objecto Categoria Tarefa
            PreparedStatement pstmtCategoriTarefa = conn.prepareStatement("SELECT * FROM CategoriaTarefa where idCategoria = ?");
            pstmtCategoriTarefa.setInt(1, rSetTarefa.getInt("idCategoria"));
            ResultSet rSetCategoriaTarefa = pstmtCategoriTarefa.executeQuery();
            rSetCategoriaTarefa.next();

            String descricao = rSetCategoriaTarefa.getString("descricao");

            PreparedStatement pstmtAreaAtividade = conn.prepareStatement("SELECT * FROM AreaAtividade where idAreaAtividade = ?");
            pstmtAreaAtividade.setString(1, rSetCategoriaTarefa.getString("idAreaAtividade"));
            ResultSet rSetAreaAtividade = pstmtAreaAtividade.executeQuery();
            rSetAreaAtividade.next();

            AreaAtividade areaAtividade = new AreaAtividade(
                    new CodigoUnico(rSetAreaAtividade.getString("idAreaAtividade")),
                    rSetAreaAtividade.getString("descricaoBreve"),
                    rSetAreaAtividade.getString("descricaoDetalhada"));

            PreparedStatement pstmtListaCaracterizacao = conn.prepareStatement("SELECT * FROM CaraterizacaoCompetenciaTecnica where idCategoria = ?");
            pstmtListaCaracterizacao.setInt(1, rSetTarefa.getInt("idCategoria"));
            ResultSet rSetListaCaracterizacao = pstmtListaCaracterizacao.executeQuery();

            ArrayList <CaracterizacaoCompTec> competencias = montarlistaCaracterizacaoCompetenciaTecnica(rSetListaCaracterizacao, areaAtividade);
            CategoriaTarefa categoriaTarefa = new  CategoriaTarefa(areaAtividade, descricao, competencias);

            tarefa = new Tarefa(referenciaTarefa, designacao, descricaoInformal,
                    descricaoTecnica, estimativaDuracao, estimativaCusto, categoriaTarefa, org);

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        if (tarefa != null) {
            return tarefa;
        } else {
            throw new FetchingProblemException("Problema ao montar tarefa");
        }
    }

    public ArrayList<Tarefa> getTarefasPrivadasOrganizacao (Organizacao organizacao) {
        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        try {
            Connection conn = connectionHandler.openConnection();
            NIF nif = organizacao.getNIF();
            PreparedStatement pstmtOrganizacao = conn.prepareStatement("SELECT idOrganizacao FROM Organizacao where NIF = ?");
            pstmtOrganizacao.setString(1, nif.toString());
            ResultSet rSetOrganizacao = pstmtOrganizacao.executeQuery();
            rSetOrganizacao.next();

            PreparedStatement pstmtTarefas = conn.prepareStatement("SELECT * FROM Tarefa where idOrganizacao = ? AND idEstadoTarefa = 1");
            pstmtTarefas.setInt(1, rSetOrganizacao.getInt("idOrganizacao"));
            ResultSet rSetTarefas = pstmtTarefas.executeQuery();

            return montarTarefas(rSetTarefas);

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new FetchingProblemException("Problemas ao ir buscar as tarefas desta organização.");
        }
    }

    public ArrayList<Tarefa> getTarefasPublishedOrganizacao (Organizacao organizacao) {
        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        try {
            Connection conn = connectionHandler.openConnection();
            NIF nif = organizacao.getNIF();
            PreparedStatement pstmtOrganizacao = conn.prepareStatement("SELECT idOrganizacao FROM Organizacao where NIF = ?");
            pstmtOrganizacao.setString(1, nif.toString());
            ResultSet rSetOrganizacao = pstmtOrganizacao.executeQuery();
            rSetOrganizacao.next();

            PreparedStatement pstmtTarefas = conn.prepareStatement("SELECT * FROM Tarefa where idOrganizacao = ? AND idEstadoTarefa = 2");
            pstmtTarefas.setInt(1, rSetOrganizacao.getInt("idOrganizacao"));
            ResultSet rSetTarefas = pstmtTarefas.executeQuery();

            return montarTarefas(rSetTarefas);

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new FetchingProblemException("Problemas ao ir buscar as tarefas desta organização.");
        }
    }

    public ArrayList<Tarefa> getTarefasOrganizacao (Organizacao organizacao) {
        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        try {
            Connection conn = connectionHandler.openConnection();
            NIF nif = organizacao.getNIF();
            PreparedStatement pstmtOrganizacao = conn.prepareStatement("SELECT idOrganizacao FROM Organizacao where NIF = ?");
            pstmtOrganizacao.setString(1, nif.toString());
            ResultSet rSetOrganizacao = pstmtOrganizacao.executeQuery();
            rSetOrganizacao.next();

            PreparedStatement pstmtTarefas = conn.prepareStatement("SELECT * FROM Tarefa where idOrganizacao = ?");
            pstmtTarefas.setInt(1, rSetOrganizacao.getInt("idOrganizacao"));
            ResultSet rSetTarefas = pstmtTarefas.executeQuery();

            return montarTarefas(rSetTarefas);

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new FetchingProblemException("Problemas ao ir buscar as tarefas desta organização.");
        }
    }

    public Tarefa criarTarefa(String codigoUnico, String designacao, String descricaoInformal, String descricaoTecnica,
                              int duracaoHoras, float custo, CategoriaTarefa categoriaTarefa, Organizacao org) {
        return new Tarefa(new CodigoUnico(codigoUnico), designacao, descricaoInformal, descricaoTecnica, duracaoHoras, custo
                , categoriaTarefa, org);
    }


    private ArrayList<CaracterizacaoCompTec> montarlistaCaracterizacaoCompetenciaTecnica(ResultSet rows, AreaAtividade areaAtividade) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        ArrayList<CaracterizacaoCompTec> competencias = new ArrayList<>();
        boolean obrigatorio;
        CompetenciaTecnica competencia;
        GrauProficiencia grau;

        while(rows.next()) {

            //montar competencia tecnica
            PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM CompetenciaTecnica where idCompetenciaTecnica = ?");
            String idCompetenciatecnica = rows.getString(1);
            pstmt1.setString(1, idCompetenciatecnica);
            competencia = montarCompetenciaTecnica(pstmt1.executeQuery(), areaAtividade);

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
        return competencias;

    }

    private CompetenciaTecnica montarCompetenciaTecnica(ResultSet row, AreaAtividade areaAtividade) throws SQLException {
        row.next();
        Connection conn = connectionHandler.openConnection();
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
