/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.ArrayList;

@JacksonXmlRootElement(localName = "roles")

/**
 *
 * @author Anibal
 */
public class ListaRoleDTO {
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "role")
    
    private ArrayList<RoleDTO> roles;

    public ListaRoleDTO() {
    }

    public ArrayList<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<RoleDTO> roles) {
        this.roles = roles;
    }
    
}
