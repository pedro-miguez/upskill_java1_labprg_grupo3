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
 *
 * @author Grupo 3
 */
public class RegistarFreelancerController {
    
    private AuthenticationController authController = new AuthenticationController();

    public boolean registarFreelancer(String nomeFreelancer, int contactoFreelancer, String emailFreelancer, int nifFreelancer,
                                      List<ReconhecimentoCT> reconhecimento, List<HabilitacaoAcademica> habilitacoes) throws SQLException {

        Plataforma plataforma = Plataforma.getInstance();
        RepositorioFreelancer repoFlr = RepositorioFreelancer.getInstance();

        Freelancer freelancer = repoFlr.criarFreelancer(nomeFreelancer, contactoFreelancer, emailFreelancer, nifFreelancer,
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
