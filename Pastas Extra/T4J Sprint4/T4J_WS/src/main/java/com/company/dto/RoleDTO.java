/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"role"})
@JacksonXmlRootElement(localName = "roles")

/**
 *
 * @author Grupo 3
 */
public class RoleDTO {
    @JacksonXmlProperty(localName = "roles")
    private String role;
    

    public RoleDTO() {
        super();
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /*
	enum RoleDTO {
		GESTOR,
		COLABORADOR,
		ADMNISTRATIVO,
		FREELANCER
	}
     */
}
