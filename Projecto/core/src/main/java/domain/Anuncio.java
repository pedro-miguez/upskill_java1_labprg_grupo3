/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 * Current class represents the tool for creating new advertisement so that 
 * tasks can be announced.
 *
 * @author Grupo 3
 */
public class Anuncio implements Serializable {
    

    private Tarefa tarefa;

    private TipoRegimento tipoRegimento;

    private Data dataInicioPublicitacao;
    private Data dataFimPublicitacao;
    private Data dataInicioCandidatura;
    private Data dataFimCandidatura;
    private Data dataInicioSeriacao;
    private Data dataFimSeriacao;

    public Anuncio (Tarefa tarefa, TipoRegimento tipoRegimento, Data dataInicioPublicitacao,
                    Data dataFimPublicitacao, Data dataInicioCandidatura,
                    Data dataFimCandidatura, Data dataInicioSeriacao, Data dataFimSeriacao) {

        this.dataInicioPublicitacao = dataInicioPublicitacao;
        this.dataFimPublicitacao = dataFimPublicitacao;
        this.dataInicioCandidatura = dataInicioCandidatura;
        this.dataFimCandidatura = dataFimCandidatura;
        this.dataInicioSeriacao = dataInicioSeriacao;
        this.dataFimSeriacao = dataFimSeriacao;
        this.tarefa = tarefa;
        this.tipoRegimento = tipoRegimento;
        
    }

    public void setTipoRegimento(TipoRegimento tipoRegimento) {
        this.tipoRegimento = tipoRegimento;
    }


    public void setDataInicioPublicitacao(Data dataInicioPublicitacao) {
        this.dataInicioPublicitacao = dataInicioPublicitacao;
    }

    public void setDataFimPublicitacao(Data dataFimPublicitacao) {
        if ((dataFimPublicitacao.getDia() < dataInicioPublicitacao.getDia()) && 
            (dataFimPublicitacao.getMes() < dataInicioPublicitacao.getMes()) &&
            (dataFimPublicitacao.getAno() < dataInicioPublicitacao.getAno())) {
            throw new IllegalArgumentException("A data de fim de publicitação "
                    + "não deve ser inferior à data de início de publicitação!");
        } else {
            this.dataFimPublicitacao = dataFimPublicitacao;
        }
         
    }

    public void setDataInicioCandidatura(Data dataInicioCandidatura) {
        this.dataInicioCandidatura = dataInicioCandidatura;
    }

    public void setDataFimCandidatura(Data dataFimCandidatura) {
        if ((dataFimCandidatura.getDia() < dataInicioCandidatura.getDia()) && 
            (dataFimCandidatura.getMes() < dataInicioCandidatura.getMes()) &&
            (dataFimCandidatura.getAno() < dataInicioCandidatura.getAno())) {
            throw new IllegalArgumentException("A data de fim de candidatura "
                    + "não deve ser inferior à data de início de candidatura!");
        } else {
            this.dataFimCandidatura = dataFimCandidatura;
        }
    }

    
    public void setDataInicioSeriacao(Data dataInicioSeriacao) {
        this.dataInicioSeriacao = dataInicioSeriacao;
    }

    public void setDataFimSeriacao(Data dataFimSeriacao) {
        
        if ((dataFimSeriacao.getDia() < dataInicioSeriacao.getDia()) && 
            (dataFimSeriacao.getMes() < dataInicioSeriacao.getMes()) &&
            (dataFimSeriacao.getAno() < dataInicioSeriacao.getAno())) {
            throw new IllegalArgumentException("A data de fim de seriação "
                    + "não deve ser inferior à data de início de seriação!");
        } else {
            this.dataFimSeriacao = dataFimSeriacao;
        }
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

    public Tarefa getTarefa() {
        return tarefa;
    }

    public TipoRegimento getTipoRegimento() {
        return tipoRegimento;
    }
    
    

    public String toString(){
        return String.format("ID Tarefa: %s%nData de Inicio da Publicitacao: %s%n" +
                "Data de Fim de Publicitacao: %s%nData de Inicio da Candidatura: %s%n" +
                "Data de Fim da Candidatura: %s%nData de Inicio de Seriacao: %s%n" +
                "Data de Fim de Seriacao: %s", this.tarefa.getCodigoUnico(),
                this.dataInicioPublicitacao, this.dataFimPublicitacao,
                this.dataInicioCandidatura, this.dataFimCandidatura,
                this.dataInicioSeriacao, this.dataFimSeriacao);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio)) return false;

        Anuncio anuncio = (Anuncio) o;

        return getTarefa().equals(anuncio.tarefa);
    }

}
