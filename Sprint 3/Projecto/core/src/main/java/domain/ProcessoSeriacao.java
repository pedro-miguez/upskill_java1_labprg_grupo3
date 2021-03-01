package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Current class represents the tool for creating new serialization process.
 * 
 * @author Grupo 3
 */
public class ProcessoSeriacao {

    private Anuncio anuncio;
    private Data data;
    private List<Classificacao> classificacao;
    private List<Colaborador> colaboradores;

    /**
     * Instantiates a new serialization process.
     * 
     * @param anuncio
     * @param classificacao
     * @param colaboradores 
     */
    public ProcessoSeriacao(Anuncio anuncio, List<Classificacao> classificacao, 
                                             List<Colaborador> colaboradores) {
        this.anuncio = anuncio;
        this.classificacao = new ArrayList<>(classificacao);
        this.colaboradores = new ArrayList<>(colaboradores);
        this.data = Data.dataAtual();
    }


    /**
     * Gets the advertisement.
     * @return anuncio
     */
    public Anuncio getAnuncio() {
        return anuncio;
    }

    /**
     * Sets the advertisement.
     * @param anuncio 
     */
    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    /**
     * Gets the date.
     * @return data
     */
    public Data getData() {
        return data;
    }

    /**
     * Gets a list of classifications.
     * @return ArrayList<>(classificacao)
     */
    public List<Classificacao> getClassificacao() {
        return new ArrayList<>(classificacao);
    }


    /**
     * Gets a list of collaborators.
     * @return ArrayList<>(colaboradores)
     */
    public List<Colaborador> getColaboradores() {
        return new ArrayList<>(colaboradores);
    }


}
