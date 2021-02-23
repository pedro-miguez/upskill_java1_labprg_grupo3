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
public class AnuncioTest {

    @Test
    public void testCreateAnuncioValido() {

        Tarefa tarefa = new Tarefa(new CodigoUnico("TAR-01"), "Progamador Jogo",
                "É necessário progamador para um jogo",
                "É necessário programador em java para desenvolvimento de um jogo", 100, 1500.00f, categoriaTarefa, org);

        Anuncio a = new Anuncio (tarefa, new TipoRegimento()
                                    new Data(2021, 02, 18),
                                    new Data(2021, 02, 18),
                                    new Data(2021, 02, 22),
                                    new Data(2021, 02, 22),
                                    new Data(2021, 03, 01),
                                    new Data(2021, 06, 30));
        
        Data expected = new Data(2021, 02, 18);
        
        Data result = a.getDataInicioPublicitacao();
        
        assertEquals(expected, result);
        
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateAnuncioComIdTarefaInvalido() {
        
        Anuncio a = new Anuncio (new CodigoUnico("ANC-000"),
                                    new Data(2021, 02, 18),
                                    new Data(2021, 02, 18),
                                    new Data(2021, 02, 22),
                                    new Data(2021, 02, 22),
                                    new Data(2021, 03, 01),
                                    new Data(2021, 06, 30));
        
    }

}
