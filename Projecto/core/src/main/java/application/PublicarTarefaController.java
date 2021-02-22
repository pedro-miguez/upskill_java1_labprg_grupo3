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
 *
 * @author Grupo 3
 */
public class PublicarTarefaController {
    
    public boolean publicarTarefa(Tarefa tarefa, TipoRegimento tipoRegimento,
                                  Data dataInicioPublicitacao, LocalDate dataFimPublicitacao,
                                    LocalDate dataInicioCandidatura, LocalDate dataFimCandidatura,
                                    LocalDate dataInicioSeriacao, LocalDate dataFimSeriacao) 
                                throws SQLException {
        
        RepositorioAnuncio repAnuncio = RepositorioAnuncio.getInstance();
        
        
        Anuncio anuncio = repAnuncio.criarAnuncio(tarefa, tipoRegimento, 
                            dataInicioPublicitacao, dataFimPublicitacao, 
                            dataInicioCandidatura, dataFimCandidatura, 
                            dataInicioSeriacao, dataFimSeriacao);
                
        return repAnuncio.insertAnuncio(anuncio);
        
    }
    
}
