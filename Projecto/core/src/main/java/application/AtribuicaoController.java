/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.*;
import persistence.RepositorioCandidatura;
import persistence.RepositorioColaborador;
import persistence.RepositorioAtribuicao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Current class is the one responsible to connect the GUI with the methods 
 * responsible for the process of assignment of the task to the freelancer.
 *
 * @author Grupo 3
 */
public class AtribuicaoController {
    
    public boolean isAtribuicaoObrigatoria (Anuncio anuncio) {
        
        return anuncio.isSeriacaoAutomatica();
        
    }
    
    public boolean criarProcessoAtribuicao (List<Candidatura> candidaturas,
                                         List<Colaborador> gestor, 
                                         String emailGestor)
                                                        throws SQLException {
        
        gestor.add(RepositorioColaborador.getInstance().getColaboradorByEmail(new Email(emailGestor)));
        
        Atribuicao processoAtribuicao = RepositorioAtribuicao.getInstance().criarProcessoAtribuicao();
        
        return RepositorioAtribuicao.getInstance().insertProcessoAtribuicao(processoAtribuicao);
        
    }
    
}
