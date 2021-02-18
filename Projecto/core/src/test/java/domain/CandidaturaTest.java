/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Grupo 3
 */
public class CandidaturaTest {
    
    @Test
    public void testCreateCandidaturaValida() {
        
        Candidatura cand = new Candidatura(new CodigoUnico("CDT-02"),
                            new CodigoUnico("FRL-21"),
                            new Data(2021, 02, 18),
                            1200, 90, "Projeto novo", "Novos desafios");
        
        int expected = 90;
        
        int result = cand.getNrDias();
        
        assertEquals(expected, result);
        
    }
    
}
