/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"cargo"})
@JacksonXmlRootElement(localName = "role")

/**
 *
 * @author Anibal
 */
public class RoleDTO extends UserDTO {
    
    @JacksonXmlProperty(localName = "cargo")
    private String cargo;

    public RoleDTO() {
        //super();
    }

    
    public String getRole() {
        return cargo;
    }

    public void setRole(String cargo) {
        this.cargo = cargo;
    }
    
}
