package application;

import domain.*;
import persistence.RepositorioCandidatura;
import persistence.RepositorioFreelancer;

import java.sql.SQLException;

public class RemoverCandidaturaController {

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
