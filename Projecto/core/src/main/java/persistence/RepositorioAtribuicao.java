package persistence;

import domain.*;
import exceptions.FetchingProblemException;

import java.sql.*;
import java.util.List;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Class responsible for creating a repository to store information about
 * assignment process.
 * 
 * @author Grupo 3
 */
public class RepositorioAtribuicao {

    private static RepositorioAtribuicao instance;
    
    private RepositorioAtribuicao() {

    }

    public boolean insertAtribuicao(Atribuicao atribuicao) throws SQLException {
        Connection conn = Plataforma.getInstance().getConnectionHandler().getConnection();

        try {

            conn.setAutoCommit(false);

            int nif = Integer.parseInt(atribuicao.getClassificacao().getCandidatura().getAnuncio().getTarefa()
                    .getOrganizacao().getNIF().toString());

            CallableStatement csIdOrg= conn.prepareCall("SELECT idOrganizacao FROM Organizacao WHERE NIF = ?");
            csIdOrg.setInt(1, nif);
            ResultSet rSetIdOrg = csIdOrg.executeQuery();
            rSetIdOrg.next();

            int idOrganizacao = rSetIdOrg.getInt("idOrganizacao");
            String referenciaTarefa = atribuicao.getClassificacao().getCandidatura().getAnuncio().getTarefa()
                    .getCodigoUnico().toString();

            CallableStatement csIdAnuncio = conn.prepareCall("{? = call getAnunciobyRefTarefa_IdOrg(?, ?)}");
            csIdAnuncio.registerOutParameter(1, Types.INTEGER);
            csIdAnuncio.setString(2, referenciaTarefa );
            csIdAnuncio.setInt(3, idOrganizacao);
            csIdAnuncio.executeUpdate();

            int idAnuncio = csIdAnuncio.getInt(1);

            CallableStatement csFreelancerIdByEmail = conn.prepareCall("{? = call getFreelancerIDByEmail(?)}");
            csFreelancerIdByEmail.registerOutParameter(1, Types.INTEGER);
            csFreelancerIdByEmail.setString(2, atribuicao.getClassificacao().getCandidatura().
                    getFreelancer().getEmail().toString());
            csFreelancerIdByEmail.executeUpdate();

            int idFreelancer = csFreelancerIdByEmail.getInt(1);

            CallableStatement csCreateAtribuicao = conn.prepareCall("{call createAtribuicao(?,?,?,?,?)}");
            csCreateAtribuicao.setInt(1, idFreelancer);
            csCreateAtribuicao.setInt(2, idAnuncio);
            csCreateAtribuicao.setDate(3, atribuicao.getDataInicio().getDataSQL());
            csCreateAtribuicao.setDate(4, atribuicao.getDataFim().getDataSQL());
            csCreateAtribuicao.setDate(5, atribuicao.getDataAtribuicao().getDataSQL());

            csCreateAtribuicao.executeQuery();

            conn.commit();

            csIdAnuncio.close();
            csCreateAtribuicao.close();
            csFreelancerIdByEmail.close();
            csIdOrg.close();

        }catch (SQLException e) {
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


    public Atribuicao criarAtribuicao() {
        return new Atribuicao();
    }

}

