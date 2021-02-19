package persistence;

import domain.Anuncio;
import domain.AreaAtividade;
import domain.CodigoUnico;
import domain.Tarefa;
import exceptions.CodigoNaoAssociadoException;
import jdk.nashorn.internal.codegen.CompilerConstants;
import network.ConnectionHandler;

import java.sql.*;

public class RepositorioAnuncio {

    private static RepositorioAnuncio instance;
    private ConnectionHandler connectionHandler;

    public static RepositorioAnuncio getInstance(){
        if (instance == null) {
            instance = new RepositorioAnuncio();
        }
        return instance;
    }

    public boolean insertAnuncio(Anuncio anuncio) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        try {

            CallableStatement cs1 = conn.prepareCall("SELECT idTarefa FROM Tarefa WHERE referenciaTarefa = ?");
            cs1.setString(1, anuncio.getTarefa().getCodigoUnico().toString());

            int idTarefa = cs1.getInt("idTarefa");

            CallableStatement cs2 = conn.prepareCall("{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?)}");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            cs2.setInt(1, idTarefa);
            long dataInicioPub = Date.parse(anuncio.getDataInicioPublicitacao().toAnoMesDiaString());
            Date sqlDate = new java.sql.Date(dataInicioPub);
            cs2.setDate(2, sqlDate);
            long dataFimPub = Date.parse(anuncio.getDataFimPublicitacao().toAnoMesDiaString());
            Date sqlDate2 = new java.sql.Date(dataFimPub);
            cs2.setDate(3, sqlDate2);
            long dataInicioCand = Date.parse(anuncio.getDataInicioCandidatura().toAnoMesDiaString());
            Date sqlDate3 = new java.sql.Date(dataInicioCand);
            cs2.setDate(4, sqlDate3);
            long dataFimCand = Date.parse(anuncio.getDataFimCandidatura().toAnoMesDiaString());
            Date sqlDate4 = new java.sql.Date(dataFimCand);
            cs2.setDate(5,sqlDate4);
            long dataInicioSer = Date.parse(anuncio.getDataInicioSeriacao().toAnoMesDiaString());
            Date sqlDate5 = new java.sql.Date(dataInicioSer);
            cs2.setDate(6, sqlDate5);
            long dataFimSer = Date.parse(anuncio.getDataFimSeriacao().toAnoMesDiaString());
            Date sqlDate6 = new java.sql.Date(dataFimSer);
            cs2.setDate(7, sqlDate6);

            cs2.executeQuery();

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

    public Anuncio getAnuncioByRefTarefaIdOrg(Tarefa tarefa){

        try {
            Connection conn = connectionHandler.openConnection();
            String refTarefa = tarefa.getCodigoUnico().toString();

            CallableStatement cs1 = conn.prepareCall("SELECT idOrganizacao FROM tarefa WHERE referenciaTarefa = ?");
            cs1.setString(1, refTarefa);

            int idOrganizacao = cs1.getInt("idOrganizacao");

            CallableStatement cs2 = conn.prepareCall("{getAnunciobyRefTarefa_IdOrg(?, ?)}");

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Freelancer where Email = ?");
            pstmt.setString(1, emailFreelancer);

            return montarFreelancer(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum freelancer com esse email.");
        }
    }

}
