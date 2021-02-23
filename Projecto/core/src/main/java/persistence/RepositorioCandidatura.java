package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepositorioCandidatura {

    private static RepositorioCandidatura instance;


    public static RepositorioCandidatura getInstance() {
        if (instance == null) {
            instance = new RepositorioCandidatura();
        }
        return instance;
    }

    private RepositorioCandidatura() {

    }

    public boolean insertCandidatura(Candidatura candidatura) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {

            int nif = Integer.parseInt(candidatura.getAnuncio().getTarefa().getOrganizacao().getNIF().toString());

            CallableStatement csIdOrg= conn.prepareCall("SELECT idOrganizacao FROM Organizacao WHERE NIF = ?");
            csIdOrg.setInt(1, nif);
            ResultSet rSetIdOrg = csIdOrg.executeQuery();
            rSetIdOrg.next();

            int idOrganizacao = rSetIdOrg.getInt("idOrganizacao");
            String referenciaTarefa = candidatura.getAnuncio().getTarefa().getCodigoUnico().toString();

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

            conn.setAutoCommit(false);

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
            ResultSet rSetAnuncio = pstmt2.getResultSet();

            Anuncio anuncio = RepositorioAnuncio.getInstance().montarAnuncio(rSetAnuncio);

            //montar freelancer

            PreparedStatement pstmt3 = conn.prepareStatement("SELECT * FROM Freelancer WHERE idFreelancer = ?");
            pstmt3.setInt(1, row.getInt("idFreelancer"));
            ResultSet rSetFreelancer = pstmt3.executeQuery();
            rSetFreelancer.next();

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
