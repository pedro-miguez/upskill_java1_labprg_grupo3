/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
import persistence.RepositorioColaborador;

/**
 *
 * @author Grupo 3
 */
public class RegistarColaboradorController {

    AuthenticationController authController = new AuthenticationController();


    public boolean Colaborador(String nomeColaborador,String contactoColaborador, String emailColaborador, String gestorEmail) {

        Colaborador collab = Plataforma.getInstance().getRepoColab().getColaboradorByEmail(new Email(gestorEmail));

        Organizacao org = Plataforma.getInstance().getRepoOrg().getOrganizacaoByGestor(collab);
        
        Colaborador colaborador = new Colaborador(nomeColaborador, new Telefone(Integer.parseInt(contactoColaborador)),
                new Email(emailColaborador), org, Funcao.COLABORADOR);
        
        if (!Plataforma.getInstance().getRepoColab().addColaborador(colaborador)) {
            return false;
        } else return authController.registarColaboradorComoUtilizador(colaborador);
        
    }
    
}
