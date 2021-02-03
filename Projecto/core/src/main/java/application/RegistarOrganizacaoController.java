package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;

public class RegistarOrganizacaoController {

    private AuthenticationController authController = new AuthenticationController();

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
