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
public class DataTest {
    
    @Test
    public void testDiaValido () {
        
        Data data = new Data (2021, 02, 17);
        
        String expected = "Quarta-feira, 17 de Fevereiro de 2021";
        
        int result = data.getDia();
        
        assertEquals (expected, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void diaInvalido(){
        new Data(2021, 02, 40);
    }
    
}
