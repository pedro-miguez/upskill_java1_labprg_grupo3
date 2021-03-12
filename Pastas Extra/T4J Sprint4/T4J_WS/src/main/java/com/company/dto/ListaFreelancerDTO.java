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

@JacksonXmlRootElement(localName = "freelancers")

/**
 *
 * @author Grupo 3
 */
public class ListaFreelancerDTO {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "freelancer")

    private ArrayList<FreelancerDTO> freelancers;

    public ListaFreelancerDTO() {
    }

    public ArrayList<FreelancerDTO> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(ArrayList<FreelancerDTO> freelancers) {
        this.freelancers = freelancers;
    }
}
