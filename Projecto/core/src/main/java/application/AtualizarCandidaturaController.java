package application;

import domain.Candidatura;
import domain.Data;
import domain.Email;
import persistence.RepositorioCandidatura;

import java.util.ArrayList;

public class AtualizarCandidaturaController {

    public boolean atualizarCandidatura(){
        return true;
    }

    public ArrayList<Candidatura> getCandidaturasAbertasFreelancer(String emailFreelancer) {
        ArrayList<Candidatura> candidaturasFreelancer = RepositorioCandidatura.getInstance().getAllCandidaturasFreelancer(new Email(emailFreelancer));

        for (Candidatura c : candidaturasFreelancer) {
            if ((Data.dataAtual().isMaior(c.getAnuncio().getDataFimCandidatura()) ||
                    !(Data.dataAtual().isMaior(c.getAnuncio().getDataInicioCandidatura())))) {
                candidaturasFreelancer.remove(c);
            }
        }

        return candidaturasFreelancer;
    }
}
