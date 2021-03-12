package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class responsible for creating a repository to store information about
 * applications.
 * 
 * @author Grupo 3
 */
public class RepositorioCandidatura {

    private static RepositorioCandidatura instance;

    /**
     * Static method that returns a unique reference to the class object.
     * 
     * @return instance
     */
    public static RepositorioCandidatura getInstance() {
        if (instance == null) {
            instance = new RepositorioCandidatura();
        }
        return instance;
    }

    private RepositorioCandidatura() {

    }

    /**
     * Boolean method that checks if a application exists in the repository,
     * otherwise it is added to it.
     * 
     * @param candidatura
     * @return boolean
     * @throws SQLException 
     */
    public boolean insertCandidatura(Candidatura candidatura) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {

            conn.setAutoCommit(false);
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


    /**
     * Checks if the application was updated.
     * 
     * @param candidatura
     * @return boolean
     * @throws SQLException 
     */
    public boolean updateCandidatura(Candidatura candidatura) throws SQLException {

        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {

            conn.setAutoCommit(false);
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


            CallableStatement csUpdateCandidatura = conn.prepareCall("Update Candidatura set dataCandidatura = ?, " +
                    "valorPretendido = ?, " +
                    "nrdias = ?," +
                    "txtApresentacao = ?," +
                    "txtMotivacao = ?" +
                    "where idFreelancer = ? AND idAnuncio = ?");

            csUpdateCandidatura.setDate(1, Data.dataAtual().getDataSQL());
            csUpdateCandidatura.setDouble(2, candidatura.getValorPretendido());
            csUpdateCandidatura.setInt(3, candidatura.getNrDias());
            csUpdateCandidatura.setString(4, candidatura.getTxtApresentacao());
            csUpdateCandidatura.setString(5, candidatura.getTxtMotivacao());
            csUpdateCandidatura.setInt(6, idFreelancer);
            csUpdateCandidatura.setInt(7, idAnuncio);

            csUpdateCandidatura.executeQuery();

            conn.commit();
            csIdOrg.close();
            csIdAnuncio.close();
            csFreelancerIdByEmail.close();
            csUpdateCandidatura.close();
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
     * Checks if the application was deleted.
     * 
     * @param candidatura
     * @return boolean
     * @throws SQLException 
     */
    public boolean deleteCandidatura(Candidatura candidatura) throws SQLException {

        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {

            conn.setAutoCommit(false);
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

            //adicionar tabela para candidaturas retiradas

            CallableStatement deleteCandidatura = conn.prepareCall("Delete from Candidatura where idAnuncio = ? and " +
                    "idFreelancer = ?");
            deleteCandidatura.setInt(1, idAnuncio);
            deleteCandidatura.setInt(2, idFreelancer);

            deleteCandidatura.executeQuery();

            PreparedStatement insertCandidaturaRetirada = conn.prepareStatement("INSERT INTO CandidaturaRetirada(idAnuncio, " +
                    "idFreelancer, dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao) values (?, ?, ?, ?, ?, ?, ?)");

            insertCandidaturaRetirada.setInt(1, idAnuncio);
            insertCandidaturaRetirada.setInt(2, idFreelancer);
            insertCandidaturaRetirada.setDate(3, candidatura.getDataCandidatura().getDataSQL());
            insertCandidaturaRetirada.setDouble(4,candidatura.getValorPretendido());
            insertCandidaturaRetirada.setInt(5, candidatura.getNrDias());
            insertCandidaturaRetirada.setString(6, candidatura.getTxtApresentacao());
            insertCandidaturaRetirada.setString(7, candidatura.getTxtMotivacao());

            insertCandidaturaRetirada.executeQuery();

            conn.commit();
            csIdOrg.close();
            csIdAnuncio.close();
            csFreelancerIdByEmail.close();
            deleteCandidatura.close();
            rSetIdOrg.close();
            insertCandidaturaRetirada.close();
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
     * Gets an application by its advertisement of the freelancer.
     * 
     * @param anuncio
     * @param emailFreelancer
     * @return candidatura
     */
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

            Candidatura candidatura = montarCandidatura(pstmt.executeQuery(), true);

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


    /**
     * Gets from the list 'Candidatura' the applications by its advertisements.
     * 
     * @param anuncio
     * @return listaCandidaturas
     */
    public ArrayList<Candidatura> getCandidaturasByAnuncio(Anuncio anuncio) {
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

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Candidatura where idAnuncio = ?");
            pstmt.setInt(1, idAnuncio);
            ResultSet rSetCandidaturas = pstmt.executeQuery();

            ArrayList<Candidatura> listaCandidaturas = montarListaCandidaturas(rSetCandidaturas);

            csIdAnuncio.close();
            csIdOrg.close();
            rSetIdOrg.close();
            rSetCandidaturas.close();
            pstmt.close();

            return listaCandidaturas;
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existem candidaturas associadas a este anuncio.");
        }
    }


    /**
     * Gets from the list 'Candidatura' all the applications. 
     * 
     * @return listaCandidaturas
     */
    public ArrayList<Candidatura> getAllCandidaturas() throws FetchingProblemException {

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


    /**
     * Gets from the list 'Candidatura' all the applications of the freelancer.
     * 
     * @param emailFreelancer
     * @return listaCandidaturas
     */
    public ArrayList<Candidatura> getAllCandidaturasFreelancer(Email emailFreelancer) throws FetchingProblemException {

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

    private Candidatura montarCandidatura(ResultSet row, boolean unico) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();
        Candidatura candidatura = null;


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

            //montar freelancer

            PreparedStatement pstmt3 = conn.prepareStatement("SELECT * FROM Freelancer WHERE idFreelancer = ?");
            pstmt3.setInt(1, row.getInt("idFreelancer"));
            ResultSet rSetFreelancer = pstmt3.executeQuery();

            Freelancer freelancer = RepositorioFreelancer.getInstance().montarFreelancer(rSetFreelancer, true);

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


            if (unico) row.close();


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


    /**
     * Sets a list of applications, adding a appplication to the list 'Candidatura'.
     * 
     * @param rows
     * @return listaCandidaturas
     * @throws SQLException 
     */
    public ArrayList<Candidatura> montarListaCandidaturas(ResultSet rows) throws SQLException {
        ArrayList<Candidatura> listaCandidaturas = new ArrayList<>();

        try {
            while (rows.next()) {
                Candidatura candidatura = montarCandidatura(rows, false);
                listaCandidaturas.add(candidatura);
            }

            rows.close();

        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }
        return listaCandidaturas;
    }


    /**
     * Creates a (new) application.
     * 
     * @param anuncio
     * @param freelancer
     * @param dataCandidatura
     * @param valorPretendido
     * @param nrDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @return new Candidatura
     */
    public Candidatura criarCandidatura(Anuncio anuncio, Freelancer freelancer, Data dataCandidatura,
                                        double valorPretendido,
                                        int nrDias,
                                        String txtApresentacao,
                                        String txtMotivacao) {

        return new Candidatura(anuncio, freelancer,  dataCandidatura , valorPretendido, nrDias, txtApresentacao, txtMotivacao);
    }

}
