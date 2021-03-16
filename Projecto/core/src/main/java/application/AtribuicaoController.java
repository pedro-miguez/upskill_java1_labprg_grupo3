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
 *
 * @author Grupo 3
 */
public class AtribuicaoController {
    
    public boolean isAtribuicaoObrigatoria (Anuncio anuncio) {
        
        return anuncio.isSeriacaoAutomatica();
        
    }
    
    public boolean criarProcessoAtribuicao (List<Candidatura> candidaturas,
                                         List<Colaborador> colaboradores, 
                                         String emailColaborador)
                                                        throws SQLException {
        
        colaboradores.add(RepositorioColaborador.getInstance().getColaboradorByEmail(new Email(emailColaborador)));
        
        Atribuicao processoAtribuicao = RepositorioAtribuicao.getInstance().criarProcessoAtribuicao();
        
        return RepositorioAtribuicao.getInstance().insertProcessoAtribuicao(processoAtribuicao);
        
    }
    
}
