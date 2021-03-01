package application;

import domain.*;
import persistence.RepositorioCandidatura;
import persistence.RepositorioColaborador;
import persistence.RepositorioProcessoSeriacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for serialize application.
 * 
 * @author Grupo 3
 */
public class SeriarCandidaturaController {

    /**
     * Checks if it is automatic serialization.
     * 
     * @param anuncio
     * @return the boolean
     */
    public boolean isSeriacaoAutomatica (Anuncio anuncio){
        return anuncio.isSeriacaoAutomatica();
    }

    /**
     * Get the serial applications by value from the list 'Candidatura'.
     * 
     * @param anuncio
     * @return candidaturas
     */
    public ArrayList<Candidatura> candidaturasSeriadasPorValor(Anuncio anuncio){
        ArrayList<Candidatura> candidaturas = RepositorioCandidatura.getInstance().getCandidaturasByAnuncio(anuncio);

        Collections.sort(candidaturas);
        return candidaturas;
    }

    /**
     * Obtains from the list 'Candidatura' all the applications for selecting.
     * 
     * @param anuncio
     * @return RepositorioCandidatura.getInstance().getCandidaturasByAnuncio(anuncio)
     */
    public ArrayList<Candidatura> getAllCandidaturasPorSelecionar(Anuncio anuncio) {
        return RepositorioCandidatura.getInstance().getCandidaturasByAnuncio(anuncio);
    }

    /**
     * Obtains from the list 'Colaborador' all the collaborators by organization.
     * 
     * @param emailColaborador
     * @return RepositorioColaborador.getInstance().getColaboradoresOrganizacaoByEmail
     */
    public ArrayList<Colaborador> getAllColaboradoresOrganizacao(String emailColaborador) throws SQLException {
        ArrayList<Colaborador> lista = RepositorioColaborador.getInstance().getColaboradoresOrganizacaoByEmail(new Email(emailColaborador));

        Colaborador colaborador = RepositorioColaborador.getInstance().getColaboradorByEmail(new Email(emailColaborador));

        lista.remove(colaborador);
        return lista;
    }

    /**
     * Boolean method which creates a serialization process.
     * 
     * @param candidaturas
     * @param colaboradores
     * @param emailColaborador
     * @return RepositorioProcessoSeriacao.getInstance().insertProcessoSeriacao
     * @throws SQLException 
     */
    public boolean criarProcessoSeriacao(List<Candidatura> candidaturas,
                                         List<Colaborador> colaboradores, 
                                         String emailColaborador) throws SQLException {
        ArrayList<Classificacao> classificacoes = new ArrayList<>();

        colaboradores.add(RepositorioColaborador.getInstance().getColaboradorByEmail(new Email(emailColaborador)));

        int lugar = 0;
        for (Candidatura c : candidaturas) {
            classificacoes.add(new Classificacao(c.getAnuncio(), c.getFreelancer(), ++lugar));
        }
        ProcessoSeriacao processoSeriacao = RepositorioProcessoSeriacao.getInstance().criarProcessoSeriacao(
                classificacoes.get(1).getAnuncio(), classificacoes, colaboradores);

        return RepositorioProcessoSeriacao.getInstance().insertProcessoSeriacao(processoSeriacao);
    }


}
