package domain;

import java.util.ArrayList;
import java.util.List;

public class ProcessoSeriacao {

    private Anuncio anuncio;
    private Data data;
    private List<Classificacao> classificacao;
    private List<Colaborador> colaboradores;

    public ProcessoSeriacao(Anuncio anuncio, List<Classificacao> classificacao, List<Colaborador> colaboradores) {
        this.anuncio = anuncio;
        this.classificacao = new ArrayList<>(classificacao);
        this.colaboradores = new ArrayList<>(colaboradores);
        this.data = Data.dataAtual();
    }


    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Data getData() {
        return data;
    }

    public List<Classificacao> getClassificacao() {
        return new ArrayList<>(classificacao);
    }



    public List<Colaborador> getColaboradores() {
        return new ArrayList<>(colaboradores);
    }


}
