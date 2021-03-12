/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service;

import com.company.repo.Dados;
import com.company.dto.RoleDTO;
import com.company.dto.ListaRoleDTO;
import com.company.dto.Mapper;
import com.company.exception.ConversaoException;
import com.company.model.Plataforma;
import com.company.model.Role;
import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class RolesService {
    
    public static ListaRoleDTO getRoles() {
        Plataforma plataforma = Dados.carregarDados();
        ArrayList<Role> roles = plataforma.getRoles();
        ListaRoleDTO listaRoleaDTO = Mapper.listRole2RoleDTO(roles);
        return listaRoleaDTO;
    }

    public static RoleDTO getRole(String cargo) {
        Plataforma plataforma = Dados.carregarDados();
        Role role = plataforma.getRole(cargo);
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = Mapper.role2RoleDTO(role);
        if (roleDTO != null) {
            return roleDTO;
        } else {
            throw new ConversaoException("RoleDTO");
        }
    }

    public static void addRole(RoleDTO roleDTO) {
        Role role = Mapper.roleDTO2Role(roleDTO);
        if (role != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.addRole(role);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("RoleDTO");
        }
    }

    public static void updateRole(String cargo, RoleDTO roleDTO) {
        Role role = Mapper.roleDTO2Role(roleDTO);
        if (role != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.updateRole(cargo, role);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("RoleDTO");
        }
    }

    public static void removeRole(String cargo) {
        Plataforma plataforma = Dados.carregarDados();
        plataforma.removeRole(cargo);
        Dados.guardarDados(plataforma);
    }

    
}
