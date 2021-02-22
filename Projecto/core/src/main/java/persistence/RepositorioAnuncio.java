package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import jdk.nashorn.internal.codegen.CompilerConstants;
import network.ConnectionHandler;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioAnuncio {

    private static RepositorioAnuncio instance;
    private ConnectionHandler connectionHandler;

    public static RepositorioAnuncio getInstance() {
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

            CallableStatement cs2 = conn.prepareCall("SELECT idTipoRegimento FROM TipoRegimento where designacao = ?");
            cs2.setString(1, anuncio.getTipoRegimento().getDesignacao());

            int idTipoRegimento = cs2.getInt("idTipoRegimento");

            CallableStatement cs3 = conn.prepareCall("{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?, ?)}");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            cs3.setInt(1, idTarefa);
            cs3.setInt(2, idTipoRegimento);
            long dataInicioPub = Date.parse(anuncio.getDataInicioPublicitacao().toAnoMesDiaString());
            Date sqlDate = new java.sql.Date(dataInicioPub);
            cs3.setDate(3, sqlDate);
            long dataFimPub = Date.parse(anuncio.getDataFimPublicitacao().toAnoMesDiaString());
            Date sqlDate2 = new java.sql.Date(dataFimPub);
            cs3.setDate(4, sqlDate2);
            long dataInicioCand = Date.parse(anuncio.getDataInicioCandidatura().toAnoMesDiaString());
            Date sqlDate3 = new java.sql.Date(dataInicioCand);
            cs3.setDate(5, sqlDate3);
            long dataFimCand = Date.parse(anuncio.getDataFimCandidatura().toAnoMesDiaString());
            Date sqlDate4 = new java.sql.Date(dataFimCand);
            cs3.setDate(6, sqlDate4);
            long dataInicioSer = Date.parse(anuncio.getDataInicioSeriacao().toAnoMesDiaString());
            Date sqlDate5 = new java.sql.Date(dataInicioSer);
            cs3.setDate(7, sqlDate5);
            long dataFimSer = Date.parse(anuncio.getDataFimSeriacao().toAnoMesDiaString());
            Date sqlDate6 = new java.sql.Date(dataFimSer);
            cs3.setDate(8, sqlDate6);
            

            cs3.executeQuery();

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

    public Anuncio getAnuncioByRefTarefaIdOrg(Tarefa tarefa, TipoRegimento tipoRegimento) {

        try {
            Connection conn = connectionHandler.openConnection();
            String refTarefa = tarefa.getCodigoUnico().toString();

            CallableStatement cs1 = conn.prepareCall("SELECT idOrganizacao FROM tarefa WHERE referenciaTarefa = ?");
            cs1.setString(1, refTarefa);

            int idOrganizacao = cs1.getInt("idOrganizacao");

            CallableStatement cs2 = conn.prepareCall("{? = getAnunciobyRefTarefa_IdOrg(?, ?)}");
            cs2.registerOutParameter(1, Types.INTEGER);
            cs2.setString(2, refTarefa);
            cs2.setInt(3, idOrganizacao);

            int idAnuncio = cs2.getInt(1);

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Anuncio where idAnuncio = ?");
            pstmt.setInt(1, idAnuncio);

            return montarAnuncio(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum freelancer com esse email.");
        }
    }


    private Anuncio montarAnuncio(ResultSet row) throws SQLException {
        Connection conn = connectionHandler.openConnection();
        Anuncio anuncio = null;


        try {
            row.next();

            //montar tarefa
            PreparedStatement pstmt = conn.prepareStatement("SELECT idTarefa FROM Anuncio WHERE idAnuncio = ?");
            pstmt.setInt(1,row.getInt("idAnuncio"));
            ResultSet rSetTarefa = pstmt.executeQuery();
            int idTarefa = rSetTarefa.getInt("idTarefa");

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Tarefa WHERE idTarefa = ?");
            pstmt2.setInt(1,idTarefa);

            Tarefa tarefa = RepositorioTarefa.getInstance().montarTarefa(pstmt2.executeQuery());

            //montar tipoRegimento

            PreparedStatement pstmt3 = conn.prepareStatement("SELECT idTipoRegimento FROM Anuncio WHERE idAnuncio = ?");
            pstmt3.setInt(1,row.getInt("idAnuncio"));
            ResultSet rSetTipoRegimento = pstmt3.executeQuery();
            int idTipoRegimento = rSetTipoRegimento.getInt("idTipoRegimento");

            PreparedStatement pstmt4 = conn.prepareStatement("SELECT * FROM TipoRegimento WHERE idTipoRegimento = ?");
            pstmt4.setInt(1, idTipoRegimento);

            String designacao = pstmt4.executeQuery().getString("designacao");
            String regras = pstmt4.executeQuery().getString("descricaoRegras");

            TipoRegimento tipoRegimento = new TipoRegimento(designacao, regras);

            //Datas Anúncio

            Date datasql = row.getDate("dataInicioPublicitacao");
            String[] dataString = datasql.toString().split("-");
            Data dataInicioPub = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                    , Integer.parseInt(dataString[2]));
            Date datasql2 = row.getDate("dataFimPublicitacao");
            String[] dataString2 = datasql2.toString().split("-");
            Data dataFimPub = new Data(Integer.parseInt(dataString2[0]), Integer.parseInt(dataString2[1])
                    , Integer.parseInt(dataString2[2]));
            Date datasql3 = row.getDate("dataInicioCandidatura");
            String[] dataString3 = datasql3.toString().split("-");
            Data dataInicioCand = new Data(Integer.parseInt(dataString3[0]), Integer.parseInt(dataString3[1])
                    , Integer.parseInt(dataString3[2]));
            Date datasql4 = row.getDate("dataFimCandidatura");
            String[] dataString4 = datasql4.toString().split("-");
            Data dataFimCand = new Data(Integer.parseInt(dataString4[0]), Integer.parseInt(dataString4[1])
                    , Integer.parseInt(dataString4[2]));
            Date datasql5 = row.getDate("dataInicioSeriacao");
            String[] dataString5 = datasql5.toString().split("-");
            Data dataInicioSer = new Data(Integer.parseInt(dataString5[0]), Integer.parseInt(dataString5[1])
                    , Integer.parseInt(dataString5[2]));
            Date datasql6 = row.getDate("dataFimSeriacao");
            String[] dataString6 = datasql6.toString().split("-");
            Data dataFimSer = new Data(Integer.parseInt(dataString6[0]), Integer.parseInt(dataString6[1])
                    , Integer.parseInt(dataString6[2]));

            anuncio = new Anuncio(tarefa, tipoRegimento, dataInicioPub, dataFimPub, dataInicioCand, dataFimCand, dataInicioSer, dataFimSer);
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        if (anuncio != null) {
            return anuncio;
        } else {
            throw new FetchingProblemException("Problema ao montar anúncio");
        }

    }

    public ArrayList<Anuncio> montarListaAnuncios(ResultSet rows) throws SQLException {
        Connection conn = connectionHandler.openConnection();
        ArrayList<Anuncio> listaAnuncios = new ArrayList<>();

        try {
            while (rows.next()) {

                //Montar Tarefa

                PreparedStatement pstmt = conn.prepareStatement("SELECT idTarefa FROM Anuncio WHERE idAnuncio = ?");
                pstmt.setInt(1,rows.getInt("idAnuncio"));
                ResultSet rSetTarefa = pstmt.executeQuery();
                int idTarefa = rSetTarefa.getInt("idTarefa");

                PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Tarefa WHERE idTarefa = ?");
                pstmt2.setInt(1,idTarefa);

                Tarefa tarefa = RepositorioTarefa.getInstance().montarTarefa(pstmt2.executeQuery());

                //Montar TipoRegimento

                PreparedStatement pstmt3 = conn.prepareStatement("SELECT idTipoRegimento FROM Anuncio WHERE idAnuncio = ?");
                pstmt3.setInt(1,rows.getInt("idAnuncio"));
                int idTipoRegimento = pstmt3.executeQuery().getInt("idTipoRegimento");

                PreparedStatement pstmt4 = conn.prepareStatement("SELECT * FROM TipoRegimento WHERE idTipoRegimento = ?");
                pstmt4.setInt(1, idTipoRegimento);

                String designacao = pstmt4.executeQuery().getString("designacao");
                String regras = pstmt4.executeQuery().getString("descricaoRegras");

                TipoRegimento tipoRegimento = new TipoRegimento(designacao, regras);

                //Datas Anúncio

                Date datasql = rows.getDate(2);
                String[] dataString = datasql.toString().split("-");
                Data dataInicioPub = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));
                Date datasql2 = rows.getDate(2);
                String[] dataString2 = datasql2.toString().split("-");
                Data dataFimPub = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));
                Date datasql3 = rows.getDate(2);
                String[] dataString3 = datasql3.toString().split("-");
                Data dataInicioCand = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));
                Date datasql4 = rows.getDate(2);
                String[] dataString4 = datasql4.toString().split("-");
                Data dataFimCand = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));
                Date datasql5 = rows.getDate(2);
                String[] dataString5 = datasql5.toString().split("-");
                Data dataInicioSer = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));
                Date datasql6 = rows.getDate(2);
                String[] dataString6 = datasql6.toString().split("-");
                Data dataFimSer = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));

                listaAnuncios.add(new Anuncio(tarefa, tipoRegimento, dataInicioPub, dataFimPub, dataInicioCand, dataFimCand, dataInicioSer, dataFimSer));
            }

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        return listaAnuncios;
    }

    public Anuncio criarAnuncio(Tarefa tarefa, TipoRegimento tipoRegimento, LocalDate dataInicioPub,
                                LocalDate dataFimPub, LocalDate dataInicioCand, LocalDate dataFimCand,
                                LocalDate dataInicioSer, LocalDate dataFimSer){
        return new Anuncio(tarefa, tipoRegimento, new Data(dataInicioPub.getYear(), dataInicioPub.getMonth().getValue(),
                dataInicioPub.getDayOfMonth()), new Data(dataFimPub.getYear(), dataFimPub.getMonth().getValue(),
                dataFimPub.getDayOfMonth()), new Data(dataInicioCand.getYear(), dataInicioCand.getMonth().getValue(),
                dataInicioCand.getDayOfMonth()), new Data(dataFimCand.getYear(), dataFimCand.getMonth().getValue(),
                dataFimCand.getDayOfMonth()), new Data(dataInicioSer.getYear(), dataInicioSer.getMonth().getValue(),
                dataInicioSer.getDayOfMonth()), new Data(dataFimSer.getYear(), dataFimSer.getMonth().getValue(),
                dataFimSer.getDayOfMonth()));
    }

}
