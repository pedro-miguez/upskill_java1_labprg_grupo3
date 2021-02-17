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
public class Anuncio implements Serializable {
    
    //private int idAnuncio;
    
    private Tarefa idTarefa;
    
    private Data dataInicioPublicitacao;
    private Data dataFimPublicitacao;
    private Data dataInicioCandidatura;
    private Data dataFimCandidatura;
    private Data dataInicioSeriacao;
    private Data dataFimSeriacao;
    
    
    public Anuncio (Tarefa idTarefa, Data dataInicioPublicitacao,
                    Data dataFimPublicitacao, Data dataInicioCandidatura,
                    Data dataFimCandidatura, Data dataInicioSeriacao, Data dataFimSeriacao) {
        
        //setIdAnuncio(idAnuncio);
        
        this.idTarefa = idTarefa;
        
        this.dataInicioPublicitacao = dataInicioPublicitacao;
        this.dataFimPublicitacao = dataFimPublicitacao;
        this.dataInicioCandidatura = dataInicioCandidatura;
        this.dataFimCandidatura = dataFimCandidatura;
        this.dataInicioSeriacao = dataInicioSeriacao;
        this.dataFimSeriacao = dataFimSeriacao;
        
    }
    
    
    public Tarefa getIdTarefa() {
        return this.idTarefa;
    }
    
    public Data getDataInicioPublicitacao() {
        return this.dataInicioPublicitacao;
    }
    
    public Data getDataFimPublicitacao() {
        return this.dataFimPublicitacao;
    }
    
    public Data getDataInicioCandidatura() {
        return this.dataInicioCandidatura;
    }
    
    public Data getDataFimCandidatura() {
        return this.dataFimCandidatura;
    }
    
    public Data getDataInicioSeriacao() {
        return this.dataInicioSeriacao;
    }
    
    public Data getDataFimSeriacao() {
        return this.dataFimSeriacao;
    }
    
    
    
    
    //getAnuncioByOrganizacao
    
}
