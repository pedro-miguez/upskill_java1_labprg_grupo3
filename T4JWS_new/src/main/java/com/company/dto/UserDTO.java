/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"nif", "nome"})
@JacksonXmlRootElement(localName = "user")

/**
 *
 * @author Anibal
 */
public class UserDTO {
    
    @JacksonXmlProperty(localName = "nif")
    private long nif;
    @JacksonXmlProperty(localName = "nome")
    private String nome;
    

    public UserDTO() {
        
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
