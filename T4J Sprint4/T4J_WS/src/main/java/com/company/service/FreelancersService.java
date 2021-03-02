/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service;

import com.company.repo.Dados;
import com.company.dto.ListaFreelancerDTO;
import com.company.dto.Mapper;
import com.company.dto.FreelancerDTO;
import com.company.exception.ConversaoException;
import com.company.model.Plataforma;
import com.company.model.Freelancer;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */
public class FreelancersService {
    
    public static ListaFreelancerDTO getFreelancers() {
        ListaFreelancerDTO listaFreelancerDTO = null;
        Plataforma plataforma = Dados.carregarDados();
        ArrayList<Freelancer> freelancers = plataforma.getAllFreelancers();
        listaFreelancerDTO = Mapper.listFreelancer2FreelancerDTO(freelancers);
        return listaFreelancerDTO;
    }

    public static FreelancerDTO getFreelancer(long nif) {
        Plataforma plataforma = Dados.carregarDados();
        Freelancer freelancer = plataforma.getFreelancer(nif);
        if (freelancer == null) {
            return null;
        }
        FreelancerDTO freelancerDTO = Mapper.freelancer2FreelancerDTO(freelancer);
        if (freelancerDTO != null) {
            return freelancerDTO;
        } else {
            throw new ConversaoException("FreelancerDTO");
        }
    }

    public static void addFreelancer(FreelancerDTO freelancerDTO) {
        Freelancer freelancer = Mapper.freelancerDTO2Freelancer(freelancerDTO);
        if (freelancer != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.addFreelancer(freelancer);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("FreelancerDTO");
        }
    }

    public static void updateFreelancer(long nif, FreelancerDTO freelancerDTO) {
        Freelancer freelancer = Mapper.freelancerDTO2Freelancer(freelancerDTO);
        if (freelancer != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.updateFreelancer(nif, freelancer);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("FreelancerDTO");
        }
    }

    public static void removeFreelancer(long nif) {
        Plataforma plataforma = Dados.carregarDados();
        plataforma.removeFreelancer(nif);
        Dados.guardarDados(plataforma);
    }

    
}
