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
 * collaborator.
 */
public class RepositorioFreelancer implements Serializable {

    private static RepositorioFreelancer instance;
    private ConnectionHandler connectionHandler;

    /**
     * Collaborators that will be added to the repository.
     */
    private RepositorioFreelancer() {
        connectionHandler = new ConnectionHandler();

    }

    /**
     * Static method that returns a unique reference to the class object, which
     * implements a singleton.
     *
     * @return
     */
    public static RepositorioFreelancer getInstance() {
        if (instance == null) {
            instance = new RepositorioFreelancer();
        }
        return instance;
    }


    public boolean insertUtilizadorFreelancer(Freelancer freelancer, String password) throws SQLException {
        Connection conn = connectionHandler.openConnection();

        try {
            conn.setAutoCommit(false);

            CallableStatement csCreateFreelancer = conn.prepareCall("{CALL createUtilizadorFreelancer(?, ?, ?, ? ,?)}");
            csCreateFreelancer.setString(1, freelancer.getNome());
            csCreateFreelancer.setString(2, freelancer.getEmail().toString());
            csCreateFreelancer.setString(3, password);
            csCreateFreelancer.setInt(4, Integer.parseInt(freelancer.getTelefone().toString()));
            csCreateFreelancer.setInt(5, Integer.parseInt(freelancer.getNif().toString()));
            csCreateFreelancer.executeQuery();


            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
            csFreelancerIdByEmail.setString(2, freelancer.getEmail().toString());
            csFreelancerIdByEmail.executeUpdate();

            int idFreelancer = csFreelancerIdByEmail.getInt("idFreelancer");

            CallableStatement csCreateHabilitacaoAcademica = conn.prepareCall(
                    "{CALL createHabilitacaoAcademica(?, ?, ?, ? ,?)}");

            for (HabilitacaoAcademica ha : freelancer.getHabilitacoes()) {
                csCreateHabilitacaoAcademica.setInt(1, idFreelancer);
                csCreateHabilitacaoAcademica.setString(2, ha.getGrau());
                csCreateHabilitacaoAcademica.setString(3, ha.getDesignacaoCurso());
                csCreateHabilitacaoAcademica.setString(4, ha.getNomeInstituicao());
                csCreateHabilitacaoAcademica.setDouble(5, ha.getMediaCurso());
                csCreateHabilitacaoAcademica.executeQuery();

                csCreateHabilitacaoAcademica.clearParameters();
            }

            CallableStatement csCreateReconhecimentoCT = conn.prepareCall(
                    "{CALL createReconhecimentoCT(?, ?, ?, ? )}");

            for (ReconhecimentoCT rct : freelancer.getReconhecimento()) {
                csCreateReconhecimentoCT.setInt(1, idFreelancer);
                csCreateReconhecimentoCT.setString(2, rct.getCompetenciaTecnica().getCodigoUnico().toString());
                csCreateReconhecimentoCT.setInt(3, rct.getGrauProficiencia().getNivel());

                long dataReconhecimento = Date.parse(rct.getDataReconhecimento().toAnoMesDiaString());
                Date sqlDate = new java.sql.Date(dataReconhecimento);
                csCreateReconhecimentoCT.setDate(4, sqlDate);

                csCreateReconhecimentoCT.executeQuery();

                csCreateReconhecimentoCT.clearParameters();
            }

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
     * Method to obtain a collaborator through your email.
     *
     * @param email
     * @return
     */
    public Freelancer getFreelancerByEmail(Email email) throws SQLException {
        try {
            Connection conn = connectionHandler.openConnection();
            String emailFreelancer = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Freelancer where Email = ?");
            pstmt.setString(1, emailFreelancer);

            return montarFreelancer(pstmt.executeQuery());
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum freelancer com esse email.");
        }

    }

    public ArrayList<Colaborador> getColaboradoresOrganizacaoByEmail(Email email){
        try {
            Connection conn = connectionHandler.openConnection();

            CallableStatement cs = conn.prepareCall("SELECT idOrganizacao FROM Colaborador WHERE email = ?");
            cs.setString(1, email.toString());

            int orgID = cs.getInt("idOrganizacao");

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Colaborador WHERE idOrganizacao = ?");
            pstmt.setInt(1, orgID);

            return montarListaColaboradores(pstmt.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            e.getSQLState();
            e.getErrorCode();
            throw new FetchingProblemException("Erro ao listar colaboradores.");
        }
    }

    public Freelancer montarFreelancer(ResultSet row) throws SQLException {
        Freelancer freelancer = null;
        Connection conn = connectionHandler.openConnection();

        try {
            row.next();
            String nome = row.getString("nome");
            Telefone telefone = new Telefone(Integer.parseInt(row.getString("telefone")));
            Email email = new Email(row.getString("email"));
            NIF nif = new NIF(row.getInt("NIF"));

            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM HabilitacaoAcademica WHERE idFreelancer = ?");
            pstmt.setInt(1, row.getInt("idFreelancer"));
            ArrayList<HabilitacaoAcademica> habilitacaoAcademicas = montarListaHabilitacoesAcademicas(pstmt.executeQuery());


            PreparedStatement pstmt2 = conn.prepareStatement(
                    "SELECT * FROM ReconhecimentoCT WHERE idFreelancer = ?");
            pstmt.setInt(1, row.getInt("idFreelancer"));
            ArrayList<ReconhecimentoCT> reconhecimentoCTS = montarListaReconhecimentoCT(pstmt2.executeQuery());


            freelancer = new Freelancer(nome, telefone, email, nif, habilitacaoAcademicas, reconhecimentoCTS );
        } catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();

        }

        if (freelancer != null) {
            return freelancer;
        } else {
            throw new FetchingProblemException("Problema ao montar freelancer");
        }

    }

    private ArrayList<ReconhecimentoCT> montarListaReconhecimentoCT(ResultSet rows) {
        ArrayList<ReconhecimentoCT> listaReconhecimentoCT = new ArrayList<>();

        try {
            while (rows.next()) {
                CompetenciaTecnica ct =


                listaReconhecimentoCT.add(new ReconhecimentoCT(CompetenciaTecnica, GrauProficiencia, dataReconhecimento));
            }
        }catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return listaReconhecimentoCT;
    }

    public ArrayList<HabilitacaoAcademica> montarListaHabilitacoesAcademicas(ResultSet row) {

        ArrayList<HabilitacaoAcademica> listaHabilitacoes = new ArrayList<>();

        try {
            while (row.next()) {
                String grau = row.getString("grau");
                String designacaoCurso = row.getString("designacaoCurso");
                String nomeInstituicao = row.getString("nomeInstituicao");
                double mediaCurso = row.getDouble("mediaCurso");
                listaHabilitacoes.add(new HabilitacaoAcademica(grau, designacaoCurso, nomeInstituicao, mediaCurso));
            }
        }catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return listaHabilitacoes;
    }


    public Colaborador criarColaborador(String nomeColaborador, int contactoColaborador,
                                        String emailColaborador, String funcao) {
        return new Colaborador(nomeColaborador, new Telefone(contactoColaborador),
                new Email(emailColaborador), funcao);
    }

}
