/*
package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioProcessoSeriacao {

    private static RepositorioProcessoSeriacao instance;


    public static RepositorioProcessoSeriacao getInstance() {
        if (instance == null) {
            instance = new RepositorioProcessoSeriacao();
        }
        return instance;
    }

    private RepositorioProcessoSeriacao() {

    }

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

            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
            csFreelancerIdByEmail.setString(2, candidatura.getFreelancer().getEmail().toString());
            csFreelancerIdByEmail.executeUpdate();

            int idFreelancer = csFreelancerIdByEmail.getInt(1);


            CallableStatement csCreateCandidatura = conn.prepareCall("{CALL createCandidatura(?, ?, ?, ?, ?, ?, ?)}");


            csCreateCandidatura.setInt(1, idAnuncio);
            csCreateCandidatura.setInt(2, idFreelancer);
            csCreateCandidatura.setDate(3, candidatura.getDataCandidatura().getDataSQL());
            csCreateCandidatura.setDouble(4, candidatura.getValorPretendido());
            csCreateCandidatura.setInt(5, candidatura.getNrDias());
            csCreateCandidatura.setString(6, candidatura.getTxtApresentacao());
            csCreateCandidatura.setString(7, candidatura.getTxtMotivacao());

            csCreateCandidatura.executeQuery();

            conn.commit();
            csIdOrg.close();
            csIdAnuncio.close();
            csFreelancerIdByEmail.close();
            csCreateCandidatura.close();
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

    public Candidatura getCandidaturaByAnuncioFreelancer(Anuncio anuncio, String emailFreelancer) {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

            int nif = Integer.parseInt(anuncio.getTarefa().getOrganizacao().getNIF().toString());

            CallableStatement csIdOrg= conn.prepareCall("SELECT idOrganizacao FROM Organizacao WHERE NIF = ?");
            csIdOrg.setInt(1, nif);
            ResultSet rSetIdOrg = csIdOrg.executeQuery();
            rSetIdOrg.next();

            int idOrganizacao = rSetIdOrg.getInt("idOrganizacao");
            String referenciaTarefa = anuncio.getTarefa().getCodigoUnico().toString();

            CallableStatement csIdAnuncio = conn.prepareCall("{? = call getAnunciobyRefTarefa_IdOrg(?, ?)}");
            csIdAnuncio.registerOutParameter(1, Types.INTEGER);
            csIdAnuncio.setString(2, referenciaTarefa );
            csIdAnuncio.setInt(3, idOrganizacao);
            csIdAnuncio.executeUpdate();

            int idAnuncio = csIdAnuncio.getInt(1);

            Freelancer freelancer = RepositorioFreelancer.getInstance().getFreelancerByEmail(new Email(emailFreelancer));

            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
            csFreelancerIdByEmail.setString(2, freelancer.getEmail().toString());
            csFreelancerIdByEmail.executeUpdate();

            int idFreelancer = csFreelancerIdByEmail.getInt(1);

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Candidatura where idAnuncio = ? AND idFreelancer = ?");
            pstmt.setInt(1, idAnuncio);
            pstmt.setInt(2, idFreelancer);

            Candidatura candidatura = montarCandidatura(pstmt.executeQuery());

            csFreelancerIdByEmail.close();
            csIdAnuncio.close();
            csIdOrg.close();
            rSetIdOrg.close();
            pstmt.close();

            return candidatura;
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum anuncio associado a esta tarefa.");
        }
    }

    public ArrayList<Candidatura> getAllCandidaturas() {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

            PreparedStatement pstmtCandidaturas = conn.prepareStatement("SELECT * FROM Candidatura");
            ResultSet rSetCandidaturas = pstmtCandidaturas.executeQuery();

            ArrayList<Candidatura> listaCandidaturas = montarListaCandidaturas(rSetCandidaturas);
            pstmtCandidaturas.close();
            rSetCandidaturas.close();
            return listaCandidaturas;

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new FetchingProblemException("Problemas ao montar a lista de candidaturas");
        }
    }

    public ArrayList<Candidatura> getAllCandidaturasFreelancer(Email emailFreelancer) {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
            csFreelancerIdByEmail.setString(2, emailFreelancer.toString());
            csFreelancerIdByEmail.executeUpdate();

            int idFreelancer = csFreelancerIdByEmail.getInt(1);

            PreparedStatement pstmtCandidaturas = conn.prepareStatement("SELECT * FROM Candidatura where idFreelancer = ?");
            pstmtCandidaturas.setInt(1, idFreelancer);
            ResultSet rSetCandidaturas = pstmtCandidaturas.executeQuery();

            ArrayList<Candidatura> listaCandidaturas = montarListaCandidaturas(rSetCandidaturas);

            csFreelancerIdByEmail.close();
            pstmtCandidaturas.close();
            rSetCandidaturas.close();

            return listaCandidaturas;

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
            e.getErrorCode();
            throw new FetchingProblemException("Problemas ao montar a lista de candidaturas");
        }
    }



    private Candidatura montarCandidatura(ResultSet row) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();
        Candidatura candidatura = null;


        try {
            if (row.getRow() < 1) {
                row.next();
            }

            //montar anuncio

            int idAnuncio = row.getInt("idAnuncio");

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM Anuncio WHERE idAnuncio = ?");
            pstmt2.setInt(1, idAnuncio);
            ResultSet rSetAnuncio = pstmt2.executeQuery();

            Anuncio anuncio = RepositorioAnuncio.getInstance().montarAnuncio(rSetAnuncio);

            //montar freelancer

            PreparedStatement pstmt3 = conn.prepareStatement("SELECT * FROM Freelancer WHERE idFreelancer = ?");
            pstmt3.setInt(1, row.getInt("idFreelancer"));
            ResultSet rSetFreelancer = pstmt3.executeQuery();

            Freelancer freelancer = RepositorioFreelancer.getInstance().montarFreelancer(rSetFreelancer);

            //montar atributos
            Date datasql = row.getDate("dataCandidatura");
            String[] dataString = datasql.toString().split("-");
            Data dataCandidatura = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                    , Integer.parseInt(dataString[2]));

            double valorPretendido = row.getDouble("valorPretendido");
            int nrDias = row.getInt("nrDias");
            String txtApresentacao = row.getString("txtApresentacao");
            String txtMotivacao = row.getString("txtMotivacao");


            candidatura = new Candidatura(anuncio, freelancer, dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao);

            pstmt2.close();
            pstmt3.close();
            rSetAnuncio.close();
            rSetFreelancer.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        if (candidatura != null) {
            return candidatura;
        } else {
            throw new FetchingProblemException("Problema ao montar candidatura");
        }

    }



    public ArrayList<Candidatura> montarListaCandidaturas(ResultSet rows) throws SQLException {
        ArrayList<Candidatura> listaCandidaturas = new ArrayList<>();

        try {
            while (rows.next()) {
                Candidatura candidatura = montarCandidatura(rows);
                listaCandidaturas.add(candidatura);
            }

            rows.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        return listaCandidaturas;
    }


    public Candidatura criarCandidatura(Anuncio anuncio, Freelancer freelancer, LocalDate dataCandidatura,
                                        double valorPretendido,
                                        int nrDias,
                                        String txtApresentacao,
                                        String txtMotivacao) {

        return new Candidatura(anuncio, freelancer,  new Data(dataCandidatura.getYear(), dataCandidatura.getMonth().getValue(),
                dataCandidatura.getDayOfMonth()), valorPretendido, nrDias, txtApresentacao, txtMotivacao);
    }

}
*/
