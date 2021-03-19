package domain;

import javax.ejb.Local;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Current class represents the tool for creating new assignment process.
 * 
 * @author Grupo 3
 */
public class Atribuicao implements Serializable {

    private Data dataInicio;
    private Data dataFim;
    private Data dataAtribuicao;
    private Classificacao classificacao;
    //private String nrUnico;
    //private int counter = 0;


    /**
     * Instantiates a new rating.
     * @param classificacao
     */
    public Atribuicao(Classificacao classificacao, LocalDate dataInicio) {
        setClassificacao(classificacao);
        setDataAtribuicao(Data.dataAtual());
        setDataInicio(dataInicio);
        setDataFim(dataInicio);
        //++counter;
        //nrUnico = getClassificacao().getCandidatura().getDataCandidatura().getAno()+"-"+ counter;
    }


    /**
     * Gets the start date.
     * 
     * @return dataInicio
     */
    public Data getDataInicio() {
        return dataInicio;
    }


    /**
     * Sets the start date.
     * 
     * @param dataInicio 
     */
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = new Data(dataInicio.getYear(), dataInicio.getMonth().getValue(),
                dataInicio.getDayOfMonth());
    }


    /**
     * Gets the assignment date.
     * 
     * @return dataAtribuicao
     */
    public Data getDataAtribuicao() {
        return dataAtribuicao;
    }


    /**
     * Sets the assignment date.
     * 
     * @param dataAtribuicao 
     */
    public void setDataAtribuicao(Data dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }


    /**
     * Gets the end date.
     * 
     * @return dataFim
     */
    public Data getDataFim() {
        return dataFim;
    }


    /**
     * Sets the end date.
     * 
     * @param dataInicio
     */
    public void setDataFim(LocalDate dataInicio) {
        LocalDate dataFim = dataInicio.plusDays(this.classificacao.getCandidatura().getNrDias());
        this.dataFim = new Data (dataFim.getYear(), dataFim.getMonthValue(), dataFim.getDayOfMonth());
    }

    /**
     * Gets the rating.
     * 
     * @return classificacao
     */
    public Classificacao getClassificacao() {
        return classificacao;
    }

    /**
     * Sets the rating.
     * 
     * @param classificacao 
     */
    public void setClassificacao(Classificacao classificacao) {
        if (classificacao != null)
            this.classificacao = classificacao;
        else
            throw new IllegalArgumentException("Classificacao Inválida");
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * 
     * @param o
     * @return {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atribuicao)) return false;
        Atribuicao that = (Atribuicao) o;
        return getDataInicio().equals(that.getDataInicio()) && getDataFim().equals(that.getDataFim()) &&
                getClassificacao().equals(that.getClassificacao()) && getDataAtribuicao().equals(that.dataAtribuicao);
    }

    /**
     * Returns a string representation of the object assignment and its attributes.
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Atribuicao{" +
                "data de atribuicao= " + dataAtribuicao +
                "data de inicio=" + dataInicio +
                ", data fim=" + dataFim +
                ", tarefa=" + classificacao.getCandidatura().getAnuncio().getTarefa().getDesignacao() +
                '}';
    }
}
