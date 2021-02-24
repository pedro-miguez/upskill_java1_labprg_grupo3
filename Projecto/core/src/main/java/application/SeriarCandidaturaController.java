package application;

import domain.*;
import persistence.RepositorioCandidatura;
import persistence.RepositorioColaborador;
import persistence.RepositorioProcessoSeriacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeriarCandidaturaController {

    public boolean isSeriacaoAutomatica (Anuncio anuncio){
        return anuncio.isSeriacaoAutomatica();
    }

    public ArrayList<Candidatura> candidaturasSeriadasPorValor(Anuncio anuncio){
        ArrayList<Candidatura> candidaturas = RepositorioCandidatura.getInstance().getCandidaturasByAnuncio(anuncio);

        Collections.sort(candidaturas);
        return candidaturas;
    }

    public ArrayList<Candidatura> getAllCandidaturasPorSelecionar(Anuncio anuncio) {
        return RepositorioCandidatura.getInstance().getCandidaturasByAnuncio(anuncio);
    }

    public ArrayList<Colaborador> getAllColaboradoresOrganizacao(String emailColaborador) {
        return RepositorioColaborador.getInstance().getColaboradoresOrganizacaoByEmail(new Email(emailColaborador));
    }

    public boolean criarProcessoSeriacao(List<Candidatura> candidaturas,
                                                  List<Colaborador> colaboradores) throws SQLException {
    ArrayList<Classificacao> classificacoes = new ArrayList<>();
    int lugar = 0;
        for (Candidatura c : candidaturas) {
            classificacoes.add(new Classificacao(c.getAnuncio(), c.getFreelancer(), ++lugar));
        }
        ProcessoSeriacao processoSeriacao = RepositorioProcessoSeriacao.getInstance().criarProcessoSeriacao(
                classificacoes.get(1).getAnuncio(), classificacoes, colaboradores);

        return RepositorioProcessoSeriacao.getInstance().insertProcessoSeriacao(processoSeriacao);
    }


}
