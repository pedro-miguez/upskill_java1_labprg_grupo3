package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;

/**
 * Current class is the one responsible to connect the GUI with the methods responsible for registering new
 * organizations.
 */
public class RegistarOrganizacaoController {

    private AuthenticationController authController = new AuthenticationController();

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

        Organizacao org = new Organizacao(nomeOrg, new NIF(nif), new Website(website),
                new Telefone(telefone), new Email(email) , new EnderecoPostal(rua, localidade, codigoPostal));

        Colaborador gestor = new Colaborador(nomeGestor, new Telefone(telefoneGestor), new Email(emailGestor),
                org, Funcao.GESTOR);

        if (!Plataforma.getInstance().getRepoOrg().addOrganizacao(org)) {
            return false;
        } else if (!Plataforma.getInstance().getRepoOrg().addGestor(gestor, org)) {
            return false;
        } else if(!Plataforma.getInstance().getRepoColab().addColaborador(gestor)) {
            return false;
        } else return authController.registarGestorComoUtilizador(gestor);

    }
}
