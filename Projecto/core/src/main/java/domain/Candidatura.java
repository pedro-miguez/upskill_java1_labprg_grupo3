/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Grupo 3
 */
public class Candidatura implements Serializable {
    
    private CodigoUnico idAnuncio;
    
    private CodigoUnico idFreelancer;
    
    private Data dataCandidatura;
    
    private int valorPretendido;
    private int nrDias;
    private String txtApresentacao;
    private String txtMotivacao;
    
    
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
    
    
    public CodigoUnico getIdAnuncio() {
        return this.idAnuncio;
    }
    
    public CodigoUnico getIdFreelancer() {
        return this.idFreelancer;
    }
    
    public Data getDataCandidatura() {
        return this.dataCandidatura;
    }

    
    public int getValorPretendido() {
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
    
    
    private void setValorPretendido(int valorPretendido) {
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
    
    
    public String toString(){
        return String.format("ID Anúncio: %s%nID Freelancer: %s%n" +
                "Data Candidatura: %s%nValor pretendido: %s" +
                "%nN.º de dias: %d Apresentação:%n" + 
                "Motivação: %s", this.idAnuncio, this.idFreelancer,
                this.dataCandidatura, this.valorPretendido, this.nrDias,
                this.txtApresentacao, this.txtMotivacao);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidatura)) return false;

        Candidatura candid = (Candidatura) o;

        if (!getIdAnuncio().equals(candid.getIdAnuncio())) return false;
        return getIdFreelancer().equals(candid.getIdFreelancer());
    }
    
}
