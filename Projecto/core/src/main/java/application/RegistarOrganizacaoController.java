package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;

import java.sql.SQLException;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for registering new organizations.
 */
public class RegistarOrganizacaoController {


    /**
     * Organization registry boolean.
     *
     * @param nomeOrg        as organization name
     * @param nif            as finances identification
     * @param website        as website
     * @param telefone       as phone nr
     * @param email          as email
     * @param rua            as street
     * @param localidade     as location
     * @param codigoPostal   as postcode
     * @param nomeGestor     as manager name
     * @param telefoneGestor as manager phone nr
     * @param emailGestor    as manager email
     * @return the boolean
     */
    public boolean registarOrganizacao(String nomeOrg, int nif, String website, 
                                        int telefone, String email, String rua, 
                                        String localidade, String codigoPostal,
                                        String nomeGestor, int telefoneGestor, 
                                        String emailGestor) throws SQLException {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioOrganizacao repoOrg = RepositorioOrganizacao.getInstance();
        RepositorioColaborador repoColab = RepositorioColaborador.getInstance();
        AuthenticationController authController = new AuthenticationController();

        Organizacao org = repoOrg.criarOrganizacao(nomeOrg, nif, website, telefone, email,
                                                    rua, localidade, codigoPostal);

        Colaborador gestor = repoColab.criarColaborador(nomeGestor, telefoneGestor, 
                                                        emailGestor, "gestor");

        String password = authController.registarGestorComoUtilizador(gestor);
        System.out.println(password);

        if (!password.equals("failed")) {
            return repoOrg.insertOrganizacao(org, gestor);
        } else {
            return false;
        }

    }
}
