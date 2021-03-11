/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import domain.*;
import persistence.RepositorioColaborador;
//import utils.SendEmailSMTP;

import java.sql.SQLException;


/**
 *  Current class is the one responsible to connect the GUI with the methods 
 * responsible for registering new collaborators.
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

    public boolean registarColaborador(String nomeColaborador, int contactoColaborador,
                                       String emailColaborador, String funcao, 
                                       String gestorEmail) throws SQLException {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioColaborador repoColab = RepositorioColaborador.getInstance();

        Colaborador colaborador = repoColab.criarColaborador(nomeColaborador, 
                                    contactoColaborador, emailColaborador, funcao);

        String password = authController.registarColaboradorComoUtilizador(colaborador);

        //SendEmailSMTP.SendEmail(nomeColaborador, password);

        if (!password.equals("failed")) {
            return repoColab.insertUtilizadorColaborador(colaborador, password, gestorEmail);
        } else {
            return false;
        }
    }


}

