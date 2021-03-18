/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class responsible for testing the data.
 *
 * @author Grupo 3
 */
public class DataTest {
    
    //Tests a valid day.
    @Test
    public void testDiaValido () {
        
        Data data = new Data (2021, 02, 17);
        
        int expected = 17;
        
        int result1 = data.getDia();
        
        assertEquals (expected, result1);
    }
    
    //Tests a valid month.
    @Test
    public void testMesValido () {
        
        Data data = new Data (2021, 02, 17);
        
        int expected = 2;
        
        int result2 = data.getMes();
        
        assertEquals (expected, result2);
    }
    
    //Tests a valid year.
    @Test
    public void testAnoValido () {
        
        Data data = new Data (2021, 02, 17);
        
        int expected = 2021;
        
        int result3 = data.getAno();
        
        assertEquals (expected, result3);
    }
    
    //Tests a invalid day.
    @Test(expected = IllegalArgumentException.class)
    public void diaInvalido(){
        new Data(2021, 02, 40);
    }
    
    //Tests a invalid month.
    @Test(expected = IllegalArgumentException.class)
    public void mesInvalido(){
        new Data(2021, 17, 17);
    }
}
