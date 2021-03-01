package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for registering new
 * organizations.
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
    public boolean registarOrganizacao(String nomeOrg, int nif, String website, int telefone,
                                       String email, String rua, String localidade, String codigoPostal,
                                       String nomeGestor, int telefoneGestor, String emailGestor) {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioOrganizacao repoOrg = plataforma.getRepoOrg();
        RepositorioColaborador repoColab = plataforma.getRepoColab();
        AuthenticationController authController = new AuthenticationController();

        Organizacao org = repoOrg.criarOrganizacao(nomeOrg, nif, website, telefone, email,
                rua, localidade, codigoPostal);

        Colaborador gestor = repoColab.criarGestor(nomeGestor, telefoneGestor, emailGestor, org);

        if (!repoOrg.addOrganizacao(org)) {
            return false;
        } else if (!repoOrg.addGestor(gestor, org)) {
            return false;
        } else if(!repoColab.addColaborador(gestor)) {
            return false;
        } else return authController.registarGestorComoUtilizador(gestor);

    }
}
