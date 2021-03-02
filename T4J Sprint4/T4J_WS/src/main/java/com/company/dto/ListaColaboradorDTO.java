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

@JacksonXmlRootElement(localName = "colaboradores")

/**
 *
 * @author Grupo 3
 */
public class ListaColaboradorDTO {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "colaborador")
    
    private ArrayList<ColaboradorDTO> colaboradores;

    public ListaColaboradorDTO() {
    }

    public ArrayList<ColaboradorDTO> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(ArrayList<ColaboradorDTO> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
