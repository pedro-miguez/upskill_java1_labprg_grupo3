/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;

/**
 *
 * @author Grupo 3
 */
public class RegistarFreelancerController {
    
    private AuthenticationController authController = new AuthenticationController();

    /**
     * Freelancer Registry boolean.
     *
     * @param nomeFreelancer     as freelancer name
     * @param nifFreelancer as freelancer contact
     * @param moradaFreelancer    as freelancer adress
     * @param contactoFreelancer         as freelancer contact
     * @param emailFreelancer         as freelancer email
     * @return the boolean
     */

    public boolean registarFreelancer(String nomeFreelancer, int nifFreelancer, 
                                        String moradaFreelancer, 
                                        int contactoFreelancer, 
                                        String emailFreelancer) throws SQLException {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioFreelancer repoFlr = RepositorioFreelancer.getInstance();

        Freelancer freelancer = repoFlr.criarFreelancer(nomeFreelancer, 
                                                        nifFreelancer, 
                                                        moradaFreelancer, 
                                                        contactoFreelancer,
                                                        emailFreelancer);

        String password = authController.registarFreelancerComoUtilizador(freelancer);
        System.out.println(password);

        if (!password.equals("failed")) {
            return repoFlr.insertUtilizadorFreelancer(freelancer, password);
        } else {
            return false;
        }
    }
    
}
