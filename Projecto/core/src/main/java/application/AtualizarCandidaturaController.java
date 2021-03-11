package application;

import domain.*;
import exceptions.FetchingProblemException;
import persistence.RepositorioAnuncio;
import persistence.RepositorioCandidatura;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for update application.
 * 
 * @author Grupo 3
 */
public class AtualizarCandidaturaController {

    /**
     * Boolean method which checks if the application was updated.
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
    public boolean atualizarCandidatura(Anuncio anuncio, String emailFreelancer, 
                                        double valorPretendido, int nrDias,
                                        String textoApresentacao, String textoMotivacao) 
                                        throws SQLException {
        
        RepositorioFreelancer repositorioFreelancer = RepositorioFreelancer.getInstance();

        RepositorioCandidatura repoCandidatura = RepositorioCandidatura.getInstance();
        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));

        Candidatura candidatura = repoCandidatura.criarCandidatura(anuncio, freelancer, 
                                                                    Data.dataAtual(), 
                                                                    valorPretendido, 
                                                                    nrDias, textoApresentacao, 
                                                                    textoMotivacao);

        return repoCandidatura.updateCandidatura(candidatura);

    }

    /**
     * Gets an list of opened applications (of the freelancer).
     * 
     * @param emailFreelancer
     * @return candidaturasAbertas
     * @throws FetchingProblemException 
     */
    public ArrayList<Candidatura> getCandidaturasAbertasFreelancer(String emailFreelancer) 
                                                        throws FetchingProblemException {
        
        ArrayList<Candidatura> candidaturasFreelancer = RepositorioCandidatura.getInstance().getAllCandidaturasFreelancer(new Email(emailFreelancer));
        ArrayList<Candidatura> candidaturasAbertas = new ArrayList<>();

        for (Candidatura c : candidaturasFreelancer) {
            if (!(Data.dataAtual().isMaior(c.getAnuncio().getDataFimCandidatura()) &&
                    (Data.dataAtual().isMaior(c.getAnuncio().getDataInicioCandidatura())))) {
                candidaturasAbertas.add(c);
            }
        }

        return candidaturasAbertas;
    }
}
