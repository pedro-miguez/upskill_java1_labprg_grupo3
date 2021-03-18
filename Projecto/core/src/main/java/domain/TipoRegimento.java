package domain;

import java.util.ArrayList;
import java.util.List;


public interface TipoRegimento {

    public ArrayList<Classificacao> seriarCandidaturas(List<Candidatura> candidaturas);

    public ArrayList<Classificacao> listarClassificacoesAtribuicao (ProcessoSeriacao processoSeriacao);

    public String getDesignacao();

}
