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
    
    private Anuncio idAnuncio;
    
    private Freelancer idFreelancer;
    
    private Data dataCandidatura;
    
    private int valorPretendido;
    private int nrDias;
    private String txtApresentacao;
    private String txtMotivacao;
    
    
    public Candidatura (Anuncio idAnuncio, Freelancer idFreelancer, Data dataCandidatura,
                        int valorPretendido, int nrDias, String txtApresentacao, String txtMotivacao) {
        
        this.idAnuncio = idAnuncio;
        this.idFreelancer = idFreelancer;
        this.dataCandidatura = dataCandidatura;
        
        setValorPretendido(valorPretendido);
        setNrDias(nrDias);
        setTxtApresentacao(txtApresentacao);
        setTxtMotivacao(txtMotivacao);
        
    }
    
    
    private void setValorPretendido(int valorPretendido) {
        if (valorPretendido > 0) {
            this.valorPretendido = valorPretendido;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
    }
    
    private void setNrDias(int nrDias) {
        if (nrDias > 0) {
            this.nrDias = nrDias;
        } else
            throw new IllegalArgumentException("Deve de introduzir um valor maior que zero.");
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
    
}
