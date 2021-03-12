/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package domain;

package com.company.model;


import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Current class represents the tool for creating new random passwords so that 
 * users can have access to the Platform and enjoy all of its functions.
 *
 * @author nunocastro
 */
public class AlgoritmoGeradorPasswords implements Serializable {
    
    public String geraPassword(){
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // each iteration of loop choose a character randomly from the given ASCII range
        // and append it to StringBuilder instance
 
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    };
    
}
