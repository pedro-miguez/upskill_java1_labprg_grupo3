/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.exception.ConversaoException;
import com.company.exception.ElementoNaoExistenteException;
import com.company.exception.NifDuplicadoException;
import com.company.exception.NifInvalidoException;
import com.company.exception.NomeUserInvalidoException;
import com.company.exception.CargoInvalidoException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class Plataforma implements Serializable {
    
    private String nome;
    
    private ArrayList<User> users;
    
    Role roles;
    
    
    public Plataforma(String nome) {
        this.nome = nome;
        
        this.users = new ArrayList<User>();
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<User> getAllUsers() {
        User user;
        ArrayList<User> lista = new ArrayList<>();
        for (int i = 0; i < this.users.size(); i++) {
            user = this.users.get(i);
            if (!(user instanceof Role)) {
                User copiaUser = new User(user);
                lista.add(copiaUser);
            } else {
                Role roleRole = new Role((Role) user);
                lista.add(roleRole);
            }
        }
        return lista;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<User> getUsers() {
        User user;
        ArrayList<User> lista = new ArrayList<>();
        for (int i = 0; i < this.users.size(); i++) {
            
            user = this.users.get(i);
            if (!(user instanceof Role)) {
                User copia = new User(user);
                lista.add(copia);
            }
        }
        return lista;
    }

    public void addUser(User user) throws NifDuplicadoException {
        User u = getUserByNif(user.getNif());
        if (u == null) {
            this.users.add(user);
        } else {
            throw new NifDuplicadoException(u.getNif() + ": NIF j´a existe");
        }
    }

    public User getUser(long nif) {
        return getUserByNif(nif);
    }

    public void removeUser(long nif) throws ElementoNaoExistenteException {
        User user = null;
        for (int i = 0; i < this.users.size(); i++) {
            user = this.users.get(i);
            if (user.getNif() == nif) {
                if (!(user instanceof Role)) {
                    this.roles.remove(i);
                    return;
                } else {
                    throw new ElementoNaoExistenteException(nif + ": Não é um User, é um Role!");
                }
            }
        }
        throw new ElementoNaoExistenteException(nif + ": Não existe esse User!");
    }

    public void updateUser(long nif, User u) throws ElementoNaoExistenteException {
        User user = null;
        boolean updated = false;
        for (int i = 0; i < this.users.size() && !updated; i++) {
            user = this.users.get(i);
            if (user.getNif() == nif) {
                user = u;
                updated = true;
            }
        }
        if (updated == false) {
            throw new ElementoNaoExistenteException(nif + ": Não existe esse User!");
        }
    }

    private User getUserByNif(long nif) {
        User user = null;
        for (int i = 0; i < this.users.size(); i++) {
            user = this.users.get(i);
            if (user.getNif() == nif) {
                User copia = new User(user);
                return copia;
            }
        }
        return null;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////
    
    
    public ArrayList<Role> getRoles() {
        User user;
        ArrayList<Role> lista = new ArrayList<>();
        for (int i = 0; i < this.users.size(); i++) {
            user = this.users.get(i);
            if (user instanceof Role) {
                
                Role copia = new Role((Role) user);
                lista.add(copia);
            }
        }
        return lista;
    }

    public void addRole(Role role) throws CargoInvalidoException,
            NifDuplicadoException {
        
        User u = getUserByNif(role.getNif());
        
        if (u == null) {
            Role r = getRoleByCargo(role.getRole());
            if (r == null) {
                addUser(role);
            } else {
                throw new CargoInvalidoException(r.getRole() + 
                        ": Cargo inválido!");
            }
        } else {
            throw new NifDuplicadoException(u.getNif() + ": NIF j´a existe");
        }
        
    }

    public Role getRole(String cargo) {
        Role role = getRoleByCargo(cargo);
        if (role != null) {
            Role copia = new Role(role);
            return copia;
        }
        return null;
    }

    public void removeRole(String cargo) throws ElementoNaoExistenteException {
        User user = null;
        Role role = null;
        for (int i = 0; i < this.users.size(); i++) {
            user = this.users.get(i);
            if (user instanceof Role) {
                if (user instanceof Role) {
                    role = (Role) user;
                    if (role.getRole() == cargo) {
                        this.users.remove(i);
                        return;
                    }
                }
            }
        }
        throw new ElementoNaoExistenteException(cargo + ": Não existe esse Role!");
    }

    public void updateRole(String cargo, Role r) throws ElementoNaoExistenteException {
        boolean updated = false;
        User user = null;
        Role role = null;
        for (int i = 0; i < this.users.size() && !updated; i++) {
            user = this.users.get(i);
            if (user instanceof Role) {
                role = (Role) user;
                if (role.getRole() == cargo) {
                    role = r;
                    updated = true;
                }
            }
        }
        if (updated == false) {
            throw new ElementoNaoExistenteException(cargo + ": Não existe esse Role!");
        }
    }

    private Role getRoleByCargo(String cargo) {
        User user = null;
        Role role = null;
        for (int i = 0; i < this.users.size(); i++) {
            user = this.users.get(i);
            
            if (user instanceof Role) {
                role = (Role) user;
                if (role.getRole() == cargo) {
                    return role;
                }
            }
        }
        return null;
    }
    
}
