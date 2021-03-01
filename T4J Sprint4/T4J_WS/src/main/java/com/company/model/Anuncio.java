/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Current class implements the tool for creating new advertisement so that 
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

    /**
     * Instantiates a new advertisement.
     *
     * @param tarefa                 as the task
     * @param tipoRegimento          as the type of regiment
     * @param dataInicioPublicitacao as the advertisement start date
     * @param dataFimPublicitacao    as the advertisement end date
     * @param dataInicioCandidatura  as the application start date
     * @param dataFimCandidatura     as the application end date
     * @param dataInicioSeriacao     as the serialization start date
     * @param dataFimSeriacao        as the serialization end date
     */
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

    
    /**
     * Sets the type of regiment.
     * @param tipoRegimento
     */
    public void setTipoRegimento(TipoRegimento tipoRegimento) {
        this.tipoRegimento = tipoRegimento;
    }


    /**
     * Sets the advertisement start date.
     * @param dataInicioPublicitacao
     */
    public void setDataInicioPublicitacao(Data dataInicioPublicitacao) {
        this.dataInicioPublicitacao = dataInicioPublicitacao;
    }

    /**
     * Sets the advertisement end date.
     * @param dataFimPublicitacao
     */
    public void setDataFimPublicitacao(Data dataFimPublicitacao) {
    /*    if ((dataFimPublicitacao.getDia() < dataInicioPublicitacao.getDia()) &&
            (dataFimPublicitacao.getMes() < dataInicioPublicitacao.getMes()) &&
            (dataFimPublicitacao.getAno() < dataInicioPublicitacao.getAno())) {
            throw new IllegalArgumentException("A data de fim de publicitação "
                    + "não deve ser inferior à data de início de publicitação!");
        } else {
            this.dataFimPublicitacao = dataFimPublicitacao;
        }*/
         
    }

    /**
     * Sets the application start date.
     * @param dataInicioCandidatura
     */
    public void setDataInicioCandidatura(Data dataInicioCandidatura) {
/*        if (((dataFimPublicitacao.getDia() < dataInicioCandidatura.getDia()) &&
                (dataFimPublicitacao.getMes() < dataInicioCandidatura.getMes()) &&
                (dataFimPublicitacao.getAno() < dataInicioCandidatura.getAno())) ||
        ((dataInicioPublicitacao.getDia() > dataInicioCandidatura.getDia()) &&
                (dataInicioPublicitacao.getMes() > dataInicioCandidatura.getMes()) &&
                (dataInicioPublicitacao.getAno() > dataInicioCandidatura.getAno()))) {
            throw new IllegalArgumentException("A data de inicio e fim de candidatura "
                    + "deve estar compreendida entre as datas de publicitacao!");
        } else{
            this.dataInicioCandidatura = dataInicioCandidatura;
        }*/
    }


    /**
     * Sets the application end date.
     * @param dataFimCandidatura
     */
    public void setDataFimCandidatura(Data dataFimCandidatura) {
/*        if (((dataFimCandidatura.getDia() < dataInicioCandidatura.getDia()) &&
            (dataFimCandidatura.getMes() < dataInicioCandidatura.getMes()) &&
            (dataFimCandidatura.getAno() < dataInicioCandidatura.getAno())) ||
                ((dataFimCandidatura.getDia() > dataFimPublicitacao.getDia()) &&
                        (dataFimCandidatura.getMes() > dataFimPublicitacao.getMes()) &&
                        (dataFimCandidatura.getAno() > dataFimPublicitacao.getAno()))) {
            throw new IllegalArgumentException("A data de fim de candidatura "
                    + "não deve ser inferior à data de início de candidatura nem superior a data de fimde publicitacao!");
        } else {
            this.dataFimCandidatura = dataFimCandidatura;
        }*/
    }

    /**
     * Sets the serialization start date.
     * @param dataInicioSeriacao
     */
    public void setDataInicioSeriacao(Data dataInicioSeriacao) {
 /*       if ((dataInicioSeriacao.getDia() < dataFimCandidatura.getDia()) &&
                (dataInicioSeriacao.getMes() < dataFimCandidatura.getMes()) &&
                (dataInicioSeriacao.getAno() < dataFimCandidatura.getAno())) {
            throw new IllegalArgumentException("A data de inicio da seriacao nao deve "
                    + "ser inferior a data final para candidaturas!");
        } else {
            this.dataInicioSeriacao = dataInicioSeriacao;
        }*/

    }

    /**
     * Sets the serialization end date.
     * @param dataFimSeriacao
     */
    public void setDataFimSeriacao(Data dataFimSeriacao) {
        
/*        if (((dataFimSeriacao.getDia() < dataInicioSeriacao.getDia()) &&
            (dataFimSeriacao.getMes() < dataInicioSeriacao.getMes()) &&
            (dataFimSeriacao.getAno() < dataInicioSeriacao.getAno())) ||
                ((dataFimSeriacao.getDia() > dataFimPublicitacao.getDia()) &&
                        (dataFimSeriacao.getMes() > dataFimPublicitacao.getMes()) &&
                        (dataFimSeriacao.getAno() > dataFimPublicitacao.getAno()))) {
            throw new IllegalArgumentException("A data de fim de seriação "
                    + "não deve ser inferior à data de início de seriação ou superior a data de fim de publicitacao!");
        } else {
            this.dataFimSeriacao = dataFimSeriacao;
        }*/
    }

    /**
     * Gets the advertisement start date.
     *
     * @return the advertisement start date.
     */
    public Data getDataInicioPublicitacao() {
        return this.dataInicioPublicitacao;
    }
    
    /**
     * Gets the advertisement end date.
     *
     * @return the advertisement end date.
     */
    public Data getDataFimPublicitacao() {
        return this.dataFimPublicitacao;
    }

    /**
     * Gets the application start date.
     *
     * @return the application start date.
     */
    public Data getDataInicioCandidatura() {
        return this.dataInicioCandidatura;
    }
    
    /**
     * Gets the application end date.
     *
     * @return the application end date.
     */
    public Data getDataFimCandidatura() {
        return this.dataFimCandidatura;
    }
    
    /**
     * Gets the serialization start date.
     *
     * @return the serialization start date.
     */
    public Data getDataInicioSeriacao() {
        return this.dataInicioSeriacao;
    }
    
    /**
     * Gets the serialization end date.
     *
     * @return the serialization end date.
     */
    public Data getDataFimSeriacao() {
        return this.dataFimSeriacao;
    }

    /**
     * Gets the task.
     *
     * @return the task.
     */
    public Tarefa getTarefa() {
        return tarefa;
    }

    /**
     * Gets the type of regiment.
     *
     * @return the type of regiment.
     */
    public TipoRegimento getTipoRegimento() {
        return tipoRegimento;
    }

    
    /**
     * Checks existing skills on the freelancer technical skills recognition list.
     * 
     * @param reconhecimentosFreelancer
     * @return boolean
     */
    public boolean verificaCompetencias(List<ReconhecimentoCT> reconhecimentosFreelancer) {
        ArrayList<CompetenciaTecnica> competenciasObrigatorias = new ArrayList<>();
        for (CaracterizacaoCompTec cct : this.getTarefa().getCategoria().getCompetenciasTecnicas()) {
            if (cct.isObrigatorio()) {
                competenciasObrigatorias.add(cct.getCompetenciaTecnica());
            }
        }

        if (competenciasObrigatorias.size() == 0) {
            return true;
        }

        ArrayList<CompetenciaTecnica> competenciasFreelancer = new ArrayList<>();
        for (ReconhecimentoCT rct : reconhecimentosFreelancer) {
            competenciasFreelancer.add(rct.getCompetenciaTecnica());
        }

        for (CompetenciaTecnica ctec : competenciasObrigatorias) {
            if (!competenciasFreelancer.contains(ctec)) {
                return false;
            }
        }

        int counter = 0;

        for (CaracterizacaoCompTec cct : this.getTarefa().getCategoria().getCompetenciasTecnicas()) {
            for (ReconhecimentoCT rct : reconhecimentosFreelancer) {
                if (cct.isObrigatorio()
                        && cct.getCompetenciaTecnica().equals(rct.getCompetenciaTecnica())
                        && cct.getGrauProficiencia().getNivel() <= rct.getGrauProficiencia().getNivel()) {
                    counter++;
                    break;
                }
            }
        }



        return counter == competenciasObrigatorias.size();

    }
    
    
    /**
     * Returns a string representation of the object advertisement and its attributes.
     *
     * The result should be a concise but informative representation that is easy
     * for a person to read.
     * It is overriden by all subclasses.
     *
     * @return  a string representation of the object.
     *
     */
    public String toString(){
        return String.format("ID Tarefa: %s%nTipo de Regimento: %s%nCusto Estimado: %s POT's%nDuração Estimada: %d dias %nData de Inicio da Publicitacao: %s%n" +
                "Data de Fim de Publicitacao: %s%nData de Inicio da Candidatura: %s%n" +
                "Data de Fim da Candidatura: %s%nData de Inicio de Seriacao: %s%n" +
                "Data de Fim de Seriacao: %s", this.tarefa.getCodigoUnico(),
                this.tipoRegimento.getDesignacao(), this.tarefa.getCusto(),
                this.tarefa.getDuracaoDias(),
                this.dataInicioPublicitacao, this.dataFimPublicitacao,
                this.dataInicioCandidatura, this.dataFimCandidatura,
                this.dataInicioSeriacao, this.dataFimSeriacao);
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
        if (!(o instanceof Anuncio)) return false;

        Anuncio anuncio = (Anuncio) o;

        return getTarefa().equals(anuncio.tarefa);
    }

    public boolean isSeriacaoAutomatica() {
            return this.tipoRegimento.getDesignacao().equalsIgnoreCase("Seriação Automática");
    }
}
