/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service;

import com.company.repo.Dados;
import com.company.dto.ListaUserDTO;
import com.company.dto.Mapper;
import com.company.dto.UserDTO;
import com.company.exception.ConversaoException;
import com.company.model.Plataforma;
import com.company.model.User;
import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class UsersService {
    
    public static ListaUserDTO getUsers() {
        ListaUserDTO listaUserDTO = null;
        Plataforma plataforma = Dados.carregarDados();
        ArrayList<User> users = plataforma.getAllUsers();
        listaUserDTO = Mapper.listUser2UserDTO(users);
        return listaUserDTO;
    }

    public static UserDTO getUser(long nif) {
        Plataforma plataforma = Dados.carregarDados();
        User user = plataforma.getUser(nif);
        if (user == null) {
            return null;
        }
        UserDTO userDTO = Mapper.user2UserDTO(user);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ConversaoException("UserDTO");
        }
    }

    public static void addUser(UserDTO userDTO) {
        User user = Mapper.userDTO2User(userDTO);
        if (user != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.addUser(user);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }

    public static void updateUser(long nif, UserDTO userDTO) {
        User user = Mapper.userDTO2User(userDTO);
        if (user != null) {
            Plataforma plataforma = Dados.carregarDados();
            plataforma.updateUser(nif, user);
            Dados.guardarDados(plataforma);
        } else {
            throw new ConversaoException("UserDTO");
        }
    }

    public static void removeUser(long nif) {
        Plataforma plataforma = Dados.carregarDados();
        plataforma.removeUser(nif);
        Dados.guardarDados(plataforma);
    }
    
}
