package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;

public class RegistarOrganizacaoController {

    public boolean registarOrganizacao(String nomeOrg, int nif, String website, int telefone,
                                       String email, String rua, String localidade, String codigoPostal,
                                       String nomeGestor, int telefoneGestor, String emailGestor) {
        Organizacao org = new Organizacao(nomeOrg, new NIF(nif), new Website(website),
                new Telefone(telefone), new Email(email) , new EnderecoPostal(rua, localidade, codigoPostal));

        Colaborador gestor = new Colaborador(nomeGestor, new Telefone(telefoneGestor), new Email(emailGestor),
                org, Funcao.GESTOR);

        if (!RepositorioOrganizacao.getInstance().addOrganizacao(org)) {
            return false;
        } else if (!RepositorioOrganizacao.getInstance().addGestor(gestor, org)) {
            return false;
        } else if(!RepositorioColaborador.getInstance().addColaborador(gestor)) {
            return false;
        } else return RepositorioOrganizacao.getInstance().registarGestorComoUtilizador(gestor);

    }
}
