package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import jdk.nashorn.internal.codegen.CompilerConstants;
import network.ConnectionHandler;

import java.sql.*;
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

            CallableStatement cs2 = conn.prepareCall("{CALL createAnuncio(?, ?, ?, ?, ?, ?, ?)}");
            ResultSet rs = null;


            conn.setAutoCommit(false);

            cs2.setInt(1, idTarefa);
            long dataInicioPub = Date.parse(anuncio.getDataInicioPublicitacao().toAnoMesDiaString());
            Date sqlDate = new java.sql.Date(dataInicioPub);
            cs2.setDate(3, sqlDate);
            long dataFimPub = Date.parse(anuncio.getDataFimPublicitacao().toAnoMesDiaString());
            Date sqlDate2 = new java.sql.Date(dataFimPub);
            cs2.setDate(4, sqlDate2);
            long dataInicioCand = Date.parse(anuncio.getDataInicioCandidatura().toAnoMesDiaString());
            Date sqlDate3 = new java.sql.Date(dataInicioCand);
            cs2.setDate(5, sqlDate3);
            long dataFimCand = Date.parse(anuncio.getDataFimCandidatura().toAnoMesDiaString());
            Date sqlDate4 = new java.sql.Date(dataFimCand);
            cs2.setDate(6, sqlDate4);
            long dataInicioSer = Date.parse(anuncio.getDataInicioSeriacao().toAnoMesDiaString());
            Date sqlDate5 = new java.sql.Date(dataInicioSer);
            cs2.setDate(7, sqlDate5);
            long dataFimSer = Date.parse(anuncio.getDataFimSeriacao().toAnoMesDiaString());
            Date sqlDate6 = new java.sql.Date(dataFimSer);
            cs2.setDate(8, sqlDate6);

            //Sempre que se insere um anúncio fazer update à tarefa para alterar o estado da tarefa... trigger

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

    public ArrayList<Anuncio> montarListaAnuncios(ResultSet rows, Tarefa tarefa, TipoRegimento tipoRegimento) throws SQLException {
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

                Tarefa tarefa1 = RepositorioTarefa.getInstance().montarTarefa(pstmt2.executeQuery());

                //Montar TipoRegimento

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

                listaAnuncios.add(new Anuncio(tarefa1, tipoRegimento, dataInicioPub, dataFimPub, dataInicioCand, dataFimCand, dataInicioSer, dataFimSer));
            }

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        return listaAnuncios;
    }

}
