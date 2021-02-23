package application;

import domain.Anuncio;
import domain.Candidatura;
import domain.Freelancer;
import persistence.RepositorioCandidatura;

import java.sql.SQLException;
import java.time.LocalDate;

public class EfetuarCandidaturaController {

    public boolean efetuarCandidatura(Anuncio anuncio, Freelancer freelancer, LocalDate dataCandidatura,
                                      double valorPretendido, int nrDias, String txtApresentacao, String txtMotivacao) throws SQLException {

        RepositorioCandidatura repoCandidatura = RepositorioCandidatura.getInstance();

        Candidatura candidatura = repoCandidatura.criarCandidatura(anuncio, freelancer, dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao);

        return repoCandidatura.insertCandidatura(candidatura);

    }
}
