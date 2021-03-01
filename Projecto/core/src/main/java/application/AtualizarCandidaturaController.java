package application;

import domain.*;
import persistence.RepositorioAnuncio;
import persistence.RepositorioCandidatura;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;
import java.util.ArrayList;

public class AtualizarCandidaturaController {

    public boolean atualizarCandidatura(Anuncio anuncio, String emailFreelancer, double valorPretendido, int nrDias,
                                        String textoApresentacao, String textoMotivacao) throws SQLException {
        RepositorioFreelancer repositorioFreelancer = RepositorioFreelancer.getInstance();

        RepositorioCandidatura repoCandidatura = RepositorioCandidatura.getInstance();
        Freelancer freelancer = repositorioFreelancer.getFreelancerByEmail(new Email(emailFreelancer));

        Candidatura candidatura = repoCandidatura.criarCandidatura(anuncio, freelancer, Data.dataAtual(), valorPretendido, nrDias, textoApresentacao, textoMotivacao);

        return repoCandidatura.updateCandidatura(candidatura);

    }

    public ArrayList<Candidatura> getCandidaturasAbertasFreelancer(String emailFreelancer) {
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
