package persistence;

import domain.*;
import exceptions.CodigoNaoAssociadoException;
import exceptions.FetchingProblemException;
import network.ConnectionHandler;

import javax.xml.transform.Result;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating a repository to store information about
 * freelancer.
 */
public class RepositorioFreelancer implements Serializable {

    private static RepositorioFreelancer instance;


    /**
     * Freelancers that will be added to the repository.
     */
    private RepositorioFreelancer() {


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


    /**
     * Boolean method that checks if a freelancer exists in the repository,
     * otherwise it is added to it.
     * 
     * @param freelancer
     * @return boolean
     * @throws SQLException 
     */
    public boolean insertUtilizadorFreelancer(Freelancer freelancer) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();


        try {
            conn.setAutoCommit(false);

            CallableStatement csCreateFreelancer = conn.prepareCall("{CALL createFreelancer(?, ?, ?, ?)}");
            csCreateFreelancer.setString(1, freelancer.getNome());
            csCreateFreelancer.setString(2, freelancer.getEmail().toString());
            csCreateFreelancer.setInt(3, Integer.parseInt(freelancer.getTelefone().toString()));
            csCreateFreelancer.setInt(4, Integer.parseInt(freelancer.getNif().toString()));
            csCreateFreelancer.executeQuery();


            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
            csFreelancerIdByEmail.setString(2, freelancer.getEmail().toString());
            csFreelancerIdByEmail.executeUpdate();

            int idFreelancer = csFreelancerIdByEmail.getInt(1);

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

                //long dataReconhecimento = Date.parse(rct.getDataReconhecimento().toAnoMesDiaString());
                //Date sqlDate = Date.valueOf(rct.getDataReconhecimento().dataSQLtoString());


                csCreateReconhecimentoCT.setDate(4, rct.getDataReconhecimento().getDataSQL());

                csCreateReconhecimentoCT.executeQuery();

                csCreateReconhecimentoCT.clearParameters();
            }

            conn.commit();
            csCreateFreelancer.close();
            csCreateHabilitacaoAcademica.close();
            csCreateReconhecimentoCT.close();
            csFreelancerIdByEmail.close();
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
     * Method to obtain a freelancer through your email.
     *
     * @param email
     * @return montarFreelancer
     */
    public Freelancer getFreelancerByEmail(Email email) throws SQLException {
        try {
            Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();
            String emailFreelancer = email.toString();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Freelancer where Email = ?");
            pstmt.setString(1, emailFreelancer);
            ResultSet rSetFreelancer = pstmt.executeQuery();

            Freelancer freelancer =  montarFreelancer(rSetFreelancer, true);

            pstmt.close();
            rSetFreelancer.close();

            return freelancer;
        } catch (SQLException e) {
            throw new CodigoNaoAssociadoException("Não existe nenhum freelancer com esse email.");
        }

    }


    /**
     * Method to set an freelancer.
     * 
     * @param row
     * @return freelancer
     * @throws SQLException 
     */
    public Freelancer montarFreelancer(ResultSet row, boolean unico) throws SQLException {
        Freelancer freelancer = null;
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {
            if (row.getRow() < 1) {
                row.next();
            }
            String nome = row.getString("nome");
            Telefone telefone = new Telefone(Integer.parseInt(row.getString("telefone")));
            Email email = new Email(row.getString("email"));
            NIF nif = new NIF(row.getInt("NIF"));

            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM HabilitacaoAcademica WHERE idFreelancer = ?");
            pstmt.setInt(1, row.getInt("idFreelancer"));
            ResultSet rSetHabilitacao = pstmt.executeQuery();
            ArrayList<HabilitacaoAcademica> habilitacaoAcademicas = montarListaHabilitacoesAcademicas(rSetHabilitacao);


            PreparedStatement pstmt2 = conn.prepareStatement(
                    "SELECT * FROM ReconhecimentoCT WHERE idFreelancer = ?");
            pstmt2.setInt(1, row.getInt("idFreelancer"));

            ResultSet rSetCompTecs = pstmt2.executeQuery();
            ArrayList<ReconhecimentoCT> reconhecimentoCTS = montarListaReconhecimentoCT(rSetCompTecs);


            freelancer = new Freelancer(nome, telefone, email, nif, reconhecimentoCTS, habilitacaoAcademicas );

            pstmt.close();
            pstmt2.close();
            rSetCompTecs.close();
            rSetHabilitacao.close();

            if (unico) {
                row.close();
            }


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

    /**
     * Method to create a list of recognition of technical skills.
     * 
     * @param rows
     * @return listaReconhecimentoCT
     * @throws SQLException 
     */
    public ArrayList<ReconhecimentoCT> montarListaReconhecimentoCT(ResultSet rows) throws SQLException {
        ArrayList<ReconhecimentoCT> listaReconhecimentoCT = new ArrayList<>();
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {
            while (rows.next()) {
                //Montar Area Atividade
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT idAreaAtividade FROM CompetenciaTecnica WHERE idCompetenciaTecnica = ?");
                pstmt.setString(1, rows.getString("idCompetenciaTecnica"));
                ResultSet rSetAreaAtividade = pstmt.executeQuery();
                rSetAreaAtividade.next();
                String idAreaAtividade = rSetAreaAtividade.getString("idAreaAtividade");

                PreparedStatement pstmt2 = conn.prepareStatement(
                        "SELECT * FROM AreaAtividade WHERE idAreaAtividade = ?");
                pstmt2.setString(1, idAreaAtividade);
                ResultSet rSetAreaAtividade2 = pstmt2.executeQuery();

                AreaAtividade areaAtividade = RepositorioAreaAtividade.getInstance().montarAreaAtividade(rSetAreaAtividade2, true);

                //Montar Competência Técnica
                PreparedStatement pstmt3 = conn.prepareStatement(
                        "SELECT * FROM CompetenciaTecnica WHERE idCompetenciatecnica = ?");
                pstmt3.setString(1, rows.getString("idCompetenciaTecnica"));
                ResultSet rSetCompTec = pstmt3.executeQuery();


                CompetenciaTecnica ct = RepositorioCompetenciaTecnica.getInstance().montarCompetenciaTecnica(
                        rSetCompTec, areaAtividade, true);

                //Montar Grau Proficiência
                PreparedStatement pstmt4 = conn.prepareStatement("SELECT * FROM GrauProficiencia where idCompetenciaTecnica = ? AND nivel = ?");
                pstmt4.setString(1, rows.getString("idCompetenciaTecnica"));
                pstmt4.setInt(2, rows.getInt(3));
                ResultSet linhaGrau = pstmt4.executeQuery();
                linhaGrau.next();
                GrauProficiencia grauProficiencia = new GrauProficiencia(linhaGrau.getInt(2), linhaGrau.getString(3));

                //data
                java.sql.Date data = rows.getDate("dataReconhecimento");
                String[] dataString = data.toString().split("-");

                Data dataReconhecimento = new Data(Integer.parseInt(dataString[0]), Integer.parseInt(dataString[1])
                        , Integer.parseInt(dataString[2]));
                listaReconhecimentoCT.add(new ReconhecimentoCT(ct, grauProficiencia, dataReconhecimento));

                pstmt.clearParameters();
                pstmt2.clearParameters();
                pstmt3.clearParameters();
                pstmt4.clearParameters();
                pstmt.close();
                pstmt2.close();
                pstmt3.close();
                pstmt4.close();
                rSetAreaAtividade.close();
                rSetAreaAtividade2.close();
                rSetCompTec.close();
                linhaGrau.close();

            }
        }catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        rows.close();

        return listaReconhecimentoCT;
    }

    /**
     * Method to create a list of academic qualifications.
     * 
     * @param row
     * @return listaHabilitacoes
     */
    public ArrayList<HabilitacaoAcademica> montarListaHabilitacoesAcademicas(ResultSet row) {

        ArrayList<HabilitacaoAcademica> listaHabilitacoes = new ArrayList<>();

        try {
            while (row.next()) {
                String grau = row.getString("grau");
                String designacaoCurso = row.getString("designacaoCurso");
                String nomeInstituicao = row.getString("nomeInstituicao");
                double mediaCurso = row.getDouble("mediaCurso");
                listaHabilitacoes.add(new HabilitacaoAcademica(grau, designacaoCurso, 
                                                            nomeInstituicao, mediaCurso));
            }

            row.close();

        }catch (SQLException e) {
            e.getSQLState();
            e.printStackTrace();
        }

        return listaHabilitacoes;
    }


    /**
     * Method to create new Freelancer.
     * 
     * @param nomeFreelancer
     * @param contactoFreelancer
     * @param emailFreelancer
     * @param nifFreelancer
     * @param reconhecimento
     * @param habilitacoes
     * @return new Freelancer
     */
    public Freelancer criarFreelancer(String nomeFreelancer, int contactoFreelancer, 
                                      String emailFreelancer, int nifFreelancer,
                                      List<ReconhecimentoCT> reconhecimento, 
                                      List<HabilitacaoAcademica> habilitacoes) {


        return new Freelancer(nomeFreelancer, new Telefone(contactoFreelancer), 
                                new Email(emailFreelancer), new NIF(nifFreelancer),
                                reconhecimento, habilitacoes);
    }
}
