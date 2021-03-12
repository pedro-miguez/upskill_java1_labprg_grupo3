/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service;

import com.company.repo.Dados;
import com.company.dto.ColaboradorDTO;
import com.company.dto.ListaColaboradorDTO;
import com.company.dto.Mapper;
import com.company.exception.ConversaoException;
import com.company.model.Plataforma;
import com.company.model.Colaborador;
import java.util.ArrayList;

/**
 *
 * @author Grupo 3
 */
public class ColaboradoresService {
    public static ListaColaboradorDTO getColaboradores() {
        Plataforma plataforma = Dados.carregarDados();
        ArrayList<Colaborador> colaboradores = plataforma.getColaboradores();
        ListaColaboradorDTO listaColaboradoraDTO = Mapper.listColaborador2ColaboradorDTO(colaboradores);
        return listaColaboradoraDTO;
    }

    public static ColaboradorDTO getColaborador(int nr) {
        Plataforma plataforma = Dados.carregarDados();
        Colaborador colaborador = plataforma.getColaborador(nr);
        if (colaborador == null) {
            return null;
        }

        ColaboradorDTO colaboradorDTO = Mapper.colaborador2ColaboradorDTO(colaborador);
        if (colaboradorDTO != null) {
            return colaboradorDTO;
        } else {
            throw new ConversaoException("ColaboradorDTO");
        }
    }

    public static void addColaborador(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = Mapper.colaboradorDTO2Colaborador(colaboradorDTO);
        if (colaborador != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.addColaborador(colaborador);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("ColaboradorDTO");
        }
    }

    public static void updateColaborador(int nr, ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = Mapper.colaboradorDTO2Colaborador(colaboradorDTO);
        if (colaborador != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.updateColaborador(nr, colaborador);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("ColaboradorDTO");
        }
    }

    public static void removeColaborador(int nr) {
        Plataforma plataforma = Dados.carregarDados();
        plataforma.removeColaborador(nr);
        Dados.guardarDados(plataforma);
    }
}
