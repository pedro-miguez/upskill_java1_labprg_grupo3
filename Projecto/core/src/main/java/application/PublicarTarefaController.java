/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
//import persistence.RepositorioOrganizacao;
//import persistence.RepositorioTarefa;

import java.sql.SQLException;
import java.time.LocalDate;
import persistence.RepositorioAnuncio;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for setting new task publishing.
 *
 * @author Grupo 3
 */
public class PublicarTarefaController {
    /**
     * Setting new task publishing boolean.
     *
     * @param tarefa                 as task
     * @param tipoRegimento          as type of regiment
     * @param dataInicioPublicitacao as the advertisement start date
     * @param dataFimPublicitacao    as the advertisement end date
     * @param dataInicioCandidatura  as the application start date
     * @param dataFimCandidatura     as the application end date
     * @param dataInicioSeriacao     as the serialization start date
     * @param dataFimSeriacao        as the serialization end date
     * @return the boolean
     */
    public boolean publicarTarefa(Tarefa tarefa, TipoRegimento tipoRegimento,
                                    Data dataInicioPublicitacao, LocalDate dataFimPublicitacao,
                                    LocalDate dataInicioCandidatura, LocalDate dataFimCandidatura,
                                    LocalDate dataInicioSeriacao, LocalDate dataFimSeriacao, 
                                    String emailColaborador)
                                                            throws SQLException {
        
        RepositorioAnuncio repAnuncio = RepositorioAnuncio.getInstance();
        
        
        Anuncio anuncio = repAnuncio.criarAnuncio(tarefa, tipoRegimento, 
                            dataInicioPublicitacao, dataFimPublicitacao, 
                            dataInicioCandidatura, dataFimCandidatura, 
                            dataInicioSeriacao, dataFimSeriacao);
                
        return repAnuncio.insertAnuncio(anuncio, emailColaborador);
        
    }
    
}
