/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
import persistence.RepositorioColaborador;
import persistence.RepositorioCompetenciaTecnica;
import persistence.RepositorioOrganizacao;

/**
 *  Current class is the one responsible to connect the GUI with the methods responsible for registering new
 *  collaborators.
 *
 * @author Grupo 3
 */
public class RegistarColaboradorController {

     private AuthenticationController authController = new AuthenticationController();

    /**
     * Collaborator Registry boolean.
     *
     * @param nomeColaborador     as collaborator name
     * @param contactoColaborador as collaborator contact
     * @param emailColaborador    as collaborator email
     * @param gestorEmail         as manager email
     * @return the boolean
     */

    public boolean registarColaborador(String nomeColaborador, int contactoColaborador, String emailColaborador, String gestorEmail) {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioColaborador repoColab = plataforma.getRepoColab();
        RepositorioOrganizacao repoOrg = plataforma.getRepoOrg();

        Colaborador gestor = repoColab.getColaboradorByEmail(new Email(gestorEmail));

        Organizacao org = repoOrg.getOrganizacaoByGestor(gestor);
        
        Colaborador colaborador = repoColab.criarColaborador(nomeColaborador, contactoColaborador, emailColaborador, org);

        if (!repoColab.addColaborador(colaborador)) {
            return false;
        } else return authController.registarColaboradorComoUtilizador(colaborador);
    }
}
