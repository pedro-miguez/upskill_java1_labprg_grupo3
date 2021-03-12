/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dto;

//import com.company.model.Data;
import com.company.model.User;
import com.company.model.Role;


import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class Mapper {
    
    /*public static DataDTO data2dataDTO(Data data) throws NullPointerException {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setDia(data.getDia());
        dataDTO.setMes(data.getMes());
        dataDTO.setAno(data.getAno());
        return dataDTO;
    }

    public static Data dataDTO2data(DataDTO dataDTO) throws NullPointerException {
        Data data = null;
        data = new Data(dataDTO.getDia(), dataDTO.getMes(), dataDTO.getAno());
        return data;
    }*/

    ////////////////////////////////////////////////////////////////////////////
    
    public static UserDTO user2UserDTO(User user) throws NullPointerException {
        UserDTO userDTO = new UserDTO();
        userDTO.setNif(user.getNif());
        userDTO.setNome(user.getNome());
        //DataDTO dataDTO = data2dataDTO(pessoa.getNascimento());
        //pessoaDTO.setNascimento(dataDTO);
        return userDTO;
    }

    public static User userDTO2User(UserDTO userDTO) throws NullPointerException {
        User user = null;
        //Data data = dataDTO2data(pessoaDTO.getNascimento());
        user = new User(userDTO.getNif(), userDTO.getNome());
        return user;
    }

    
public static ListaUserDTO listUser2UserDTO(ArrayList<User> users) throws NullPointerException {
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            try {
                UserDTO userDTO = user2UserDTO(user);
                usersDTO.add(userDTO);
            } catch (NullPointerException e) {
//does nothing. Actually, nothing is added to arraylist
            }
        }
        ListaUserDTO listaUserDTO = new ListaUserDTO();
        listaUserDTO.setUsers(usersDTO);
        return listaUserDTO;
    }

/////////////////////////////////////////////////////////////////////////////////

    public static RoleDTO role2RoleDTO(Role role) throws
            NullPointerException {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRole(role.getRole());
        //roleDTO.setNome(role.getNome());
        //DataDTO dataDTO = data2dataDTO(funcionario.getNascimento());
        //funcionarioDTO.setNascimento(dataDTO);
        
        return roleDTO;
    }

    public static Role roleDTO2Role(RoleDTO roleDTO) throws
            NullPointerException {
        Role role = null;
        //Data data = dataDTO2data(funcionarioDTO.getNascimento());
        role = new Role(roleDTO.getRole());
        return role;
    }

    public static ListaRoleDTO listRole2RoleDTO(ArrayList<Role> roles)
            throws NullPointerException {
        ArrayList<RoleDTO> rolesDTO = new ArrayList<>();
        for (Role role : roles) {
            try {
                RoleDTO roleDTO = role2RoleDTO(role);
                rolesDTO.add(roleDTO);
            } catch (NullPointerException e) {
//does nothing. Actually, nothing is added to arraylist
            }
        }
        ListaRoleDTO listaRoleDTO = new ListaRoleDTO();
        listaRoleDTO.setRoles(rolesDTO);
        return listaRoleDTO;
    }
    
}
