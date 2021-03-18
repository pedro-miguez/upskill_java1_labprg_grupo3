package application;

import domain.*;
import persistence.RepositorioCandidatura;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible to remove application.
 * 
 * @author Grupo 3
 */
public class RemoverCandidaturaController {

    /**
     * Boolean method that checks whether the application has been successfully removed.
     * 
     * @param anuncio
     * @param emailFreelancer
     * @param valorPretendido
     * @param nrDias
     * @param textoApresentacao
     * @param textoMotivacao
     * @return boolean
     * @throws SQLException 
     */
    public boolean removerCandidatura(Anuncio anuncio, String emailFreelancer, 
                                        double valorPretendido, int nrDias,
                                        String textoApresentacao, 
                                        String textoMotivacao) throws SQLException{

        RepositorioFreelancer repositorioFreelancer = RepositorioFreelancer.getInstance();

        RepositorioCandidatura repoCandidatura = RepositorioCandidatura.getInstance();
        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));

        Candidatura candidatura = repoCandidatura.criarCandidatura(anuncio, 
                                                                    freelancer, 
                                                                    Data.dataAtual(), 
                                                                    valorPretendido, 
                                                                    nrDias, 
                                                                    textoApresentacao, 
                                                                    textoMotivacao);

        return repoCandidatura.deleteCandidatura(candidatura);
    }
}
