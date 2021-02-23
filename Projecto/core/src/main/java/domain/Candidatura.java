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
    
    private Anuncio anuncio;
    
    private Freelancer freelancer;
    
    private Data dataCandidatura;
    
    private double valorPretendido;
    private int nrDias;
    private String txtApresentacao;
    private String txtMotivacao;
    
    /**
     * Instantiates a new application.
     *
     * @param anuncio         as the advertisement associated with this
     * @param freelancer      as the freelancer associated with this
     * @param dataCandidatura as the application date
     * @param valorPretendido as the target value
     * @param nrDias          as the number of days
     * @param txtApresentacao as the presentation text
     * @param txtMotivacao    as the motivation text
     * 
     */
    public Candidatura (Anuncio anuncio, Freelancer freelancer,
                        Data dataCandidatura, double valorPretendido, int nrDias,
                        String txtApresentacao, String txtMotivacao) {
        
        this.anuncio = anuncio;
        this.freelancer = freelancer;
        this.dataCandidatura = dataCandidatura;
        
        setValorPretendido(valorPretendido);
        setNrDias(nrDias);
        setTxtApresentacao(txtApresentacao);
        setTxtMotivacao(txtMotivacao);
        
    }
    
    
    public Anuncio getAnuncio() {
        return this.anuncio;
    }
    
    public Freelancer getFreelancer() {
        return this.freelancer;
    }
    
    public Data getDataCandidatura() {
        return this.dataCandidatura;
    }

    
    public double getValorPretendido() {
        return valorPretendido;
    }
    
    public int getNrDias() {
        return nrDias;
    }
    
    public String getTxtApresentacao() {
        return txtApresentacao;
    }
    
    public String getTxtMotivacao() {
        return txtMotivacao;
    }
    
    
    public void setDataCandidatura(Data dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }
    
    
    private void setValorPretendido(double valorPretendido) {
        if (valorPretendido > 0) {
            this.valorPretendido = valorPretendido;
        } else
            throw new IllegalArgumentException("Deve introduzir um valor maior que zero.");
    }
    
    private void setNrDias(int nrDias) {
        if (nrDias > 0) {
            this.nrDias = nrDias;
        } else
            throw new IllegalArgumentException("Deve introduzir um valor maior que zero.");
    }
    
    private void setTxtApresentacao(String txtApresentacao) {
        if (txtApresentacao == null || txtApresentacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Apresentação inválida!! Este campo não pode estar vazio.");
        } else
            this.txtApresentacao = txtApresentacao;
    }
    
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
        return String.format("ID Anúncio: %s%nEmail Freelancer: %s%n" +
                "Data Candidatura: %s%nValor pretendido: %s" +
                "%nN.º de dias: %d%nApresentação:%s%n" +
                "Motivação: %s", this.anuncio.getTarefa().getCodigoUnico(), this.freelancer.getEmail(),
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

        if (this.anuncio.equals(candid.getAnuncio())) return false;
        return this.freelancer.equals(candid.getFreelancer());
    }
    
}
