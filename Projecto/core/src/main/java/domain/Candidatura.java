/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 * Current class implements the tool for creating new application so that 
 * freelancers can apply.
 *
 * @author Grupo 3
 */
public class Candidatura implements Serializable {
    
    private CodigoUnico idAnuncio; //Mudar para private Anuncio anuncio
    
    private CodigoUnico idFreelancer; //mudar para private Freelancer freelancer
    
    private Data dataCandidatura;
    
    private int valorPretendido;
    private int nrDias;
    private String txtApresentacao;
    private String txtMotivacao;
    
    /**
     * Instantiates a new application.
     *
     * @param idAnuncio       as the unique-code of the advertisement
     * @param idFreelancer    as the unique-code of the freelancer
     * @param dataCandidatura as the application date
     * @param valorPretendido as the target value
     * @param nrDias          as the number of days
     * @param txtApresentacao as the presentation text
     * @param txtMotivacao    as the motivation text
     * 
     */
    public Candidatura (CodigoUnico idAnuncio, CodigoUnico idFreelancer, 
                        Data dataCandidatura, int valorPretendido, int nrDias,
                        String txtApresentacao, String txtMotivacao) {
        
        this.idAnuncio = idAnuncio;
        this.idFreelancer = idFreelancer;
        this.dataCandidatura = dataCandidatura;
        
        setValorPretendido(valorPretendido);
        setNrDias(nrDias);
        setTxtApresentacao(txtApresentacao);
        setTxtMotivacao(txtMotivacao);
        
    }
    
    /**
     * Gets the unique-code of the advertisement.
     *
     * @return idAnuncio.
     */
    public CodigoUnico getIdAnuncio() {
        return this.idAnuncio;
    }
    
    /**
     * Gets the unique-code of the freelancer.
     *
     * @return idFreelancer.
     */
    public CodigoUnico getIdFreelancer() {
        return this.idFreelancer;
    }
    
    /**
     * Gets the application date.
     *
     * @return dataCandidatura.
     */
    public Data getDataCandidatura() {
        return this.dataCandidatura;
    }

    /**
     * Gets the target value.
     *
     * @return valorPretendido.
     */
    public int getValorPretendido() {
        return valorPretendido;
    }
    
    /**
     * Gets the number of days.
     *
     * @return nrDias.
     */
    public int getNrDias() {
        return nrDias;
    }
    
    /**
     * Gets the presentation text.
     *
     * @return txtApresentacao.
     */
    public String getTxtApresentacao() {
        return txtApresentacao;
    }
    
    /**
     * Gets the motivation text.
     *
     * @return txtMotivacao.
     */
    public String getTxtMotivacao() {
        return txtMotivacao;
    }
    
    
    /**
     * Sets the application date.
     * @param dataCandidatura 
     */
    public void setDataCandidatura(Data dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }
    
    /**
     * Sets the target value.
     * @param valorPretendido 
     */
    private void setValorPretendido(int valorPretendido) {
        if (valorPretendido > 0) {
            this.valorPretendido = valorPretendido;
        } else
            throw new IllegalArgumentException("Deve introduzir um valor maior que zero.");
    }
    
    
    /**
     * Sets the number of days.
     * @param nrDias 
     */
    private void setNrDias(int nrDias) {
        if (nrDias > 0) {
            this.nrDias = nrDias;
        } else
            throw new IllegalArgumentException("Deve introduzir um valor maior que zero.");
    }
    
    /**
     * Sets the presentation text.
     * @param txtApresentacao 
     */
    private void setTxtApresentacao(String txtApresentacao) {
        if (txtApresentacao == null || txtApresentacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Apresentação inválida!! Este campo não pode estar vazio.");
        } else
            this.txtApresentacao = txtApresentacao;
    }
    
    /**
     * Sets the motivation text.
     * @param txtMotivacao 
     */
    private void setTxtMotivacao(String txtMotivacao) {
        if (txtMotivacao == null || txtMotivacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Motivação inválida!! Este campo não pode estar vazio.");
        } else
            this.txtMotivacao = txtMotivacao;
    }
    
    
    /**
     * Returns a string representation of the object application and its attributes.
     *
     * The result should be a concise but informative representation that is easy
     * for a person to read.
     * It is overriden by all subclasses.
     *
     * @return  a string representation of the object.
     *
     */
    public String toString(){
        return String.format("ID Anúncio: %s%nID Freelancer: %s%n" +
                "Data Candidatura: %s%nValor pretendido: %s" +
                "%nN.º de dias: %d Apresentação:%n" + 
                "Motivação: %s", this.idAnuncio, this.idFreelancer,
                this.dataCandidatura, this.valorPretendido, this.nrDias,
                this.txtApresentacao, this.txtMotivacao);
    }
    
    
    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     *
     * @param   o   the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidatura)) return false;

        Candidatura candid = (Candidatura) o;

        if (!getIdAnuncio().equals(candid.getIdAnuncio())) return false;
        return getIdFreelancer().equals(candid.getIdFreelancer());
    }
    
}
