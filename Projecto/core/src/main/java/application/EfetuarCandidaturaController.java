package application;

import domain.*;
import persistence.RepositorioAnuncio;
import persistence.RepositorioCandidatura;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for setting new applications.
 * 
 * @author Grupo 3
 */
public class EfetuarCandidaturaController {

    RepositorioFreelancer repositorioFreelancer = RepositorioFreelancer.getInstance();
    RepositorioAnuncio  repositorioAnuncio = RepositorioAnuncio.getInstance();
    RepositorioCandidatura repoCandidatura = RepositorioCandidatura.getInstance();

    /**
     * Method which obtains from the list of 'Anuncios' the matched advertisements.
     * 
     * @param emailFreelancer
     * @return anunciosMatched
     * @throws SQLException 
     */
    public ArrayList<Anuncio> getAnunciosMatched(String emailFreelancer) throws SQLException {
        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));
        ArrayList<Anuncio> todosAnuncios = repositorioAnuncio.getAllAnunciosCandidatura();
        ArrayList<Candidatura> candidaturasFreelancers = repoCandidatura.getAllCandidaturasFreelancer(freelancer.getEmail());
        ArrayList<Anuncio> anunciosMatched = new ArrayList<>();

        for (Anuncio a : todosAnuncios) {
            if (a.verificaCompetencias(freelancer.getReconhecimento())) {
                boolean candidaturaExiste = false;
                for (Candidatura c : candidaturasFreelancers) {
                    if (c.getAnuncio().equals(a)) {
                        candidaturaExiste = true;
                        break;
                    }
                }
                if (!candidaturaExiste) {
                    anunciosMatched.add(a);
                }
            }
        }

        return anunciosMatched;
    }

    /**
     * Boolean method responsible to make an application.
     * 
     * @param anuncio
     * @param emailFreelancer
     * @param valorPretendido
     * @param nrDias
     * @param txtApresentacao
     * @param txtMotivacao
     * @return repoCandidatura
     * @throws SQLException 
     */
    public boolean efetuarCandidatura(Anuncio anuncio, String emailFreelancer,
                                      double valorPretendido, int nrDias, 
                                      String txtApresentacao, String txtMotivacao) 
                                                            throws SQLException {

        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));

        Candidatura candidatura = repoCandidatura.criarCandidatura(anuncio, 
                                                                    freelancer, 
                                                                    Data.dataAtual(), 
                                                                    valorPretendido, 
                                                                    nrDias, 
                                                                    txtApresentacao, 
                                                                    txtMotivacao);

        return repoCandidatura.insertCandidatura(candidatura);

    }
}
