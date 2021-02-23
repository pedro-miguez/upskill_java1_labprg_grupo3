package application;

import domain.Anuncio;
import domain.Candidatura;
import domain.Email;
import domain.Freelancer;
import persistence.RepositorioAnuncio;
import persistence.RepositorioCandidatura;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EfetuarCandidaturaController {

    RepositorioFreelancer repositorioFreelancer = RepositorioFreelancer.getInstance();
    RepositorioAnuncio  repositorioAnuncio = RepositorioAnuncio.getInstance();
    RepositorioCandidatura repoCandidatura = RepositorioCandidatura.getInstance();

    public ArrayList<Anuncio> getAnunciosMatched(String emailFreelancer) throws SQLException {
        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));
        ArrayList<Anuncio> todosAnuncios = repositorioAnuncio.getAllAnuncios();
        ArrayList<Candidatura> candidaturasFreelancers = repoCandidatura.getAllCandidaturasFreelancer(freelancer.getEmail());
        ArrayList<Anuncio> anunciosMatched = new ArrayList<>();

        for (Anuncio a : todosAnuncios) {
            if (a.verificaCompetencias(freelancer.getReconhecimento())) {
                for (Candidatura c : candidaturasFreelancers) {
                    if (!c.getAnuncio().equals(a)) {
                        anunciosMatched.add(a);
                    }
                }
            }
        }

        return anunciosMatched;
    }

    public boolean efetuarCandidatura(Anuncio anuncio, String emailFreelancer, LocalDate dataCandidatura,
                                      double valorPretendido, int nrDias, String txtApresentacao, String txtMotivacao) throws SQLException {



        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));

        Candidatura candidatura = repoCandidatura.criarCandidatura(anuncio, freelancer, dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao);

        return repoCandidatura.insertCandidatura(candidatura);

    }
}
