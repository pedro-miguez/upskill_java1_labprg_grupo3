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
        
        int expected = 17;
        
        int result1 = data.getDia();
        
        assertEquals (expected, result1);
    }
    
    @Test
    public void testMesValido () {
        
        Data data = new Data (2021, 02, 17);
        
        int expected = 2;
        
        int result2 = data.getMes();
        
        assertEquals (expected, result2);
    }
    
    @Test
    public void testAnoValido () {
        
        Data data = new Data (2021, 02, 17);
        
        int expected = 2021;
        
        int result3 = data.getAno();
        
        assertEquals (expected, result3);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void diaInvalido(){
        new Data(2021, 02, 40);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void mesInvalido(){
        new Data(2021, 17, 17);
    }
}
