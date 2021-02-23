/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;
import java.util.List;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for registering new freelancers.
 *
 * @author Grupo 3
 */
public class RegistarFreelancerController {
    
    private AuthenticationController authController = new AuthenticationController();


    /**
     * Freelancer Registry boolean.
     *
     * @param nomeFreelancer as freelancer name
     * @param telefone       as freelancer contact
     * @param email          as freelancer email
     * @param nif            as freelancer nif
     * @param reconhecimento as freelancer tec skills
     * @param habilitacoes   as freelancer qualifications
     * @return the boolean
     */
    public boolean registarFreelancer(String nomeFreelancer, int contactoFreelancer, 
                                      String emailFreelancer, int nifFreelancer,
                                      List<ReconhecimentoCT> reconhecimento, 
                                      List<HabilitacaoAcademica> habilitacoes) throws SQLException {


        RepositorioFreelancer repoFlr = RepositorioFreelancer.getInstance();


        Freelancer freelancer = repoFlr.criarFreelancer(nomeFreelancer, contactoFreelancer, 
                                                        emailFreelancer, nifFreelancer,
                                                        reconhecimento, habilitacoes );

        String password = authController.registarFreelancerComoUtilizador(freelancer);
        System.out.println(password);

        if (!password.equals("failed")) {
            return repoFlr.insertUtilizadorFreelancer(freelancer, password);
        } else {
            return false;
        }
    }
    
}
