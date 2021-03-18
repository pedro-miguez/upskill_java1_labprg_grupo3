
package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * serialization process.
 * 
 * @author Grupo 3
 */
public class RepositorioProcessoSeriacao {

    private static RepositorioProcessoSeriacao instance;


    /**
     * Static method that returns a unique reference to the class object.
     * 
     * @return instance
     */
    public static RepositorioProcessoSeriacao getInstance() {
        if (instance == null) {
            instance = new RepositorioProcessoSeriacao();
        }
        return instance;
    }

    private RepositorioProcessoSeriacao() {

    }

    /**
     * Boolean method that checks if a serialization process exists in the 
     * repository, otherwise it is added to it.
     * 
     * @param processoSeriacao
     * @return boolean
     * @throws SQLException 
     */
    public boolean insertProcessoSeriacao(ProcessoSeriacao processoSeriacao) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {

            conn.setAutoCommit(false);
            int nif = Integer.parseInt(processoSeriacao.getAnuncio().getTarefa().getOrganizacao().getNIF().toString());

            CallableStatement csIdOrg= conn.prepareCall("SELECT idOrganizacao FROM Organizacao WHERE NIF = ?");
            csIdOrg.setInt(1, nif);
            ResultSet rSetIdOrg = csIdOrg.executeQuery();
            rSetIdOrg.next();

            int idOrganizacao = rSetIdOrg.getInt("idOrganizacao");
            String referenciaTarefa = processoSeriacao.getAnuncio().getTarefa().getCodigoUnico().toString();

            CallableStatement csIdAnuncio = conn.prepareCall("{? = call getAnunciobyRefTarefa_IdOrg(?, ?)}");
            csIdAnuncio.registerOutParameter(1, Types.INTEGER);
            csIdAnuncio.setString(2, referenciaTarefa );
            csIdAnuncio.setInt(3, idOrganizacao);
            csIdAnuncio.executeUpdate();

            int idAnuncio = csIdAnuncio.getInt(1);

            PreparedStatement pstmtIdTipoRegimento = conn.prepareCall("Select idTipoRegimento from Anuncio where idAnuncio = ?");
            pstmtIdTipoRegimento.setInt(1, idAnuncio);
            ResultSet rSetIdTipoRegimento = pstmtIdTipoRegimento.executeQuery();
            rSetIdTipoRegimento.next();

            int idTipoRegimento = rSetIdTipoRegimento.getInt(1);

            CallableStatement csCreateProcessoSeriacao = conn.prepareCall("{CALL createProcessoSeriacao(?, ?, ?)}");
            csCreateProcessoSeriacao.setInt(1, idAnuncio);
            csCreateProcessoSeriacao.setInt(2, idTipoRegimento);
            csCreateProcessoSeriacao.setDate(3, processoSeriacao.getData().getDataSQL());

            csCreateProcessoSeriacao.executeQuery();


            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            CallableStatement csCreateClassificacao = conn.prepareCall("{CALL createClassificacao(?, ?, ?)}");
            for (Classificacao classificacao : processoSeriacao.getClassificacao()) {

                csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
                csFreelancerIdByEmail.setString(2, classificacao.getCandidatura().getFreelancer().getEmail().toString());
                csFreelancerIdByEmail.executeUpdate();

                int idFreelancer = csFreelancerIdByEmail.getInt(1);
                int lugar = classificacao.getLugar();

                csCreateClassificacao.setInt(1, idAnuncio);
                csCreateClassificacao.setInt(2, idFreelancer);
                csCreateClassificacao.setInt(3, lugar);

                csCreateClassificacao.executeQuery();

                csFreelancerIdByEmail.clearParameters();
                csCreateClassificacao.clearParameters();

            }

            CallableStatement csIdColaborador = conn.prepareCall("SELECT idColaborador from Colaborador where email = ?");
            CallableStatement csCreateProcessoSeriacaoColaborador = conn.prepareCall("{CALL createProcessoSeriacaoColaborador(?, ?, ?)}");
            ResultSet rSetIdColaborador = null;
            for (Colaborador colaborador : processoSeriacao.getColaboradores()) {

                csIdColaborador.setString(1, colaborador.getEmail().toString());
                rSetIdColaborador = csIdColaborador.executeQuery();
                rSetIdColaborador.next();

                int idColaborador = rSetIdColaborador.getInt(1);

                csCreateProcessoSeriacaoColaborador.setInt(1, idAnuncio);
                csCreateProcessoSeriacaoColaborador.setInt(2, idColaborador);
                csCreateProcessoSeriacaoColaborador.setInt(3, idOrganizacao);

                csCreateProcessoSeriacaoColaborador.executeQuery();

                csIdColaborador.clearParameters();
                csCreateClassificacao.clearParameters();

            }

            conn.commit();

            csIdOrg.close();
            csIdAnuncio.close();
            csCreateProcessoSeriacao.close();
            csIdAnuncio.close();
            csCreateClassificacao.close();
            csFreelancerIdByEmail.close();
            csCreateProcessoSeriacaoColaborador.close();

            pstmtIdTipoRegimento.close();

            rSetIdTipoRegimento.close();
            if (rSetIdColaborador != null) rSetIdColaborador.close();
            rSetIdOrg.close();
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

        return false;
    }

    /**
     * Gets a list of serialization processes by manager.
     * 
     * @param emailGestor
     * @return processosSeriacao
     * @throws FetchingProblemException 
     */
    public ArrayList<ProcessoSeriacao> getProcessosSeriacaoByGestor(String emailGestor) throws FetchingProblemException {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();
            ArrayList<ProcessoSeriacao> processosSeriacao = new ArrayList<>();

            CallableStatement cs = conn.prepareCall ("{? = call getOrganizacaoByEmailColaborador(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, emailGestor);
            cs.executeUpdate();

            int orgID = cs.getInt(1);

            PreparedStatement pstmtTarefasOrganizacao = conn.prepareCall("Select idTarefa from Tarefa where idOrganizacao = ?");
            pstmtTarefasOrganizacao.setInt(1, orgID);
            ResultSet rSetIdsTarefaOrganizacao = pstmtTarefasOrganizacao.executeQuery();

            PreparedStatement pstmtAnunciosAtribuidos = conn.prepareCall("Select idTarefa from Atribuicao");
            ResultSet rSetAnunciosAtribuidos = pstmtAnunciosAtribuidos.executeQuery();

            ArrayList<Integer> idsTarefasAtribuidas = new ArrayList<>();


            while (rSetAnunciosAtribuidos.next()) idsTarefasAtribuidas.add(rSetAnunciosAtribuidos.getInt(1));

            while(rSetIdsTarefaOrganizacao.next()) {

                if (idsTarefasAtribuidas.contains(rSetIdsTarefaOrganizacao.getInt(1))) {
                    break;
                }

                PreparedStatement pstmtAnunciosSeriacaoOrg = conn.prepareCall("Select idAnuncio from Anuncio where idTarefa = ? AND (? between dataInicioSeriacao AND dataFimSeriacao)");
                pstmtAnunciosSeriacaoOrg.setInt(1, rSetIdsTarefaOrganizacao.getInt("idtarefa"));
                pstmtAnunciosSeriacaoOrg.setDate(2, Data.dataAtual().getDataSQL());
                ResultSet rSetAnunciosSeriacaoOrg = pstmtAnunciosSeriacaoOrg.executeQuery();

                if(rSetAnunciosSeriacaoOrg.next()) {
                    PreparedStatement pstmtProcessoSeriacaoIdAnuncio = conn.prepareCall("Select * from ProcessoSeriacao where idAnuncio = ?");
                    pstmtProcessoSeriacaoIdAnuncio.setInt(1, rSetAnunciosSeriacaoOrg.getInt("idanuncio"));
                    ResultSet rSetProcessoSeriacaoIdAnuncio = pstmtProcessoSeriacaoIdAnuncio.executeQuery();

                    if (rSetProcessoSeriacaoIdAnuncio.next()) {
                        processosSeriacao.add(montarProcessoSeriacao(rSetProcessoSeriacaoIdAnuncio, false));
                    }

                    pstmtAnunciosSeriacaoOrg.clearParameters();
                    pstmtProcessoSeriacaoIdAnuncio.clearParameters();
                    pstmtAnunciosSeriacaoOrg.close();
                    pstmtProcessoSeriacaoIdAnuncio.close();
                    rSetAnunciosSeriacaoOrg.close();
                    rSetProcessoSeriacaoIdAnuncio.close();
                }
            }

            pstmtTarefasOrganizacao.close();
            rSetIdsTarefaOrganizacao.close();

            return processosSeriacao;

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new FetchingProblemException("Problemas ao montar a lista de processos de seriação");
        }
    }

    private ArrayList<ProcessoSeriacao> montarListaProcessoSeriacao(ResultSet rows) throws SQLException {
        ArrayList<ProcessoSeriacao> processoSeriacao = new ArrayList<>();

        try {
            while (rows.next()) {
                processoSeriacao.add(montarProcessoSeriacao(rows, false));
            }
            rows.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return processoSeriacao;
    }

    private ProcessoSeriacao montarProcessoSeriacao(ResultSet row, boolean unico) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();
        ProcessoSeriacao processoSeriacao = null;


        try {
            if (unico) {
                row.next();
            }

            //montar anuncio

            int idAnuncio = row.getInt("idAnuncio");

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Anuncio WHERE idAnuncio = ?");
            pstmt2.setInt(1, idAnuncio);
            ResultSet rSetAnuncio = pstmt2.executeQuery();

            Anuncio anuncio = RepositorioAnuncio.getInstance().montarAnuncio(rSetAnuncio, true);

            //montar lista classificacoes

            ArrayList<Classificacao> classificacoes = new ArrayList<>();

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Classificacao WHERE idAnuncio = ?");
            pstmt.setInt(1, idAnuncio);
            ResultSet rsClassificacao = pstmt.executeQuery();

            ResultSet rsFreelancer = null;
            Candidatura candidatura = null;
            while(rsClassificacao.next()){
                PreparedStatement pstmtFreelancer = conn.prepareCall("Select * from Candidatura where idFreelancer = ?");
                pstmtFreelancer.setInt(1, rsClassificacao.getInt("idFreelancer"));
                rsFreelancer = pstmtFreelancer.executeQuery();
                candidatura = RepositorioCandidatura.getInstance().montarCandidatura(rsFreelancer, true);
                int lugar = rsClassificacao.getInt("lugar");
                classificacoes.add(new Classificacao(candidatura, lugar));
                pstmt.clearParameters();
                pstmtFreelancer.clearParameters();
            }

            //montar lista colaboradores

            PreparedStatement psListaIdColaboradores = conn.prepareStatement("SELECT idColaborador FROM ProcessoSeriacaoColaborador WHERE idAnuncio = ?");
            psListaIdColaboradores.setInt(1, idAnuncio);
            ResultSet rsIdColaboradores = psListaIdColaboradores.executeQuery();

            ArrayList<Colaborador> colaboradores = new ArrayList<>();

            ResultSet rsColaboradores=null;

            while(rsIdColaboradores.next()){
                PreparedStatement psListaColaboradores = conn.prepareStatement(" SELECT * FROM Colaborador WHERE idColaborador = ?");
                psListaColaboradores.setInt(1, rsIdColaboradores.getInt(1));
                rsColaboradores = psListaColaboradores.executeQuery();
                colaboradores = RepositorioColaborador.getInstance().montarListaColaboradores(rsColaboradores);
                psListaColaboradores.clearParameters();
            }

            Date dataSql =row.getDate("dataRealizacao");
            String[] dataString = dataSql.toString().split("-");
            Data dataProcessoSeriacao = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                    , Integer.parseInt(dataString[2]));

            processoSeriacao = new ProcessoSeriacao(anuncio, classificacoes, colaboradores);

            pstmt.close();
            pstmt2.close();
            rSetAnuncio.close();
            rsIdColaboradores.close();
            psListaIdColaboradores.close();
            if (rsColaboradores != null) {
                rsColaboradores.close();
            }
            rsClassificacao.close();
            if (rsFreelancer != null) {
                rsFreelancer.close();
            }

            if (unico) row.close();


        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        if (processoSeriacao != null) {
            return processoSeriacao;
        } else {
            throw new FetchingProblemException("Problema ao montar processo de seriacao");
        }

    }


    /**
     * Creates a serialization process.
     * 
     * @param anuncio
     * @param classificacao
     * @param colaboradores
     * @return new ProcessoSeriacao
     */
    public ProcessoSeriacao criarProcessoSeriacao(Anuncio anuncio, List<Classificacao> classificacao,
            List<Colaborador> colaboradores) {
        return new ProcessoSeriacao(anuncio, classificacao, colaboradores);
    }

}

