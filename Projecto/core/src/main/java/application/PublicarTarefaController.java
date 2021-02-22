/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
import persistence.RepositorioOrganizacao;
import persistence.RepositorioTarefa;

import java.sql.SQLException;
import persistence.RepositorioAnuncio;

/**
 *
 * @author Grupo 3
 */
public class PublicarTarefaController {
    
    public boolean publicarTarefa(Tarefa tarefa, TipoRegimento tipoRegimento,
                                    Data dataInicioPublicitacao, Data dataFimPublicitacao,
                                    Data dataInicioCandidatura, Data dataFimCandidatura,
                                    Data dataInicioSeriacao, Data dataFimSeriacao) 
                                throws SQLException {
        
        RepositorioAnuncio repAnuncio = RepositorioAnuncio.getInstance();
        
        RepositorioTarefa repTarefa = RepositorioTarefa.getInstance();
        
        Tarefa tarefa = repTarefa.getTarefasPublishedOrganizacao(organizacao);
        
        Anuncio anuncio = repositorioAnuncio
                
        return repositorioAnuncio.
        
    }
    
}
