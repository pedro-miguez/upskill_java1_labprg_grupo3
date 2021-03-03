/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;


import java.io.Serializable;

/**
 *
 * @author Anibal
 */
public class Role extends User implements Serializable {
    
    //private int numeroFuncionario;
    private String cargo;

    public Role() {
        //super(cargo);
    }

    
    public Role(String cargo) {
        
        this.cargo = cargo;
    }

    public Role(Role role) {
        
        
        this.cargo = role.getRole();
    }

    

    public String getRole() {
        return cargo;
    }

    
    public void setRole(String cargo) {
        this.cargo = cargo;
    }

    void remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
