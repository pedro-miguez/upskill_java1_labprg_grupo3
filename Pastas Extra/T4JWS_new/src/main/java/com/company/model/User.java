/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.exception.NifInvalidoException;
import com.company.exception.NomeUserInvalidoException;
import java.io.Serializable;

/**
 *
 * @author Anibal
 */
public class User implements Serializable {
    
    private long nif;
    private String nome;
    //private Data nascimento;

    public User() {
    }

    public User(long nif, String nome) {
        setNif(nif);
        setNome(nome);
        //this.nascimento = new Data(nascimento);
    }

    public User(User user) {
        setNif(user.nif);
        setNome(user.nome);
    }

    public long getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    /*public Data getNascimento() {
        Data data = new Data(nascimento);
        return data;
    }*/

    public void setNif(long nif) throws NifInvalidoException {
        if (nif >= 100000000 && nif <= 999999999) {
            this.nif = nif;
        } else {
            throw new NifInvalidoException(nif + ": NIF inv´alido");
        }
    }

    public void setNome(String nome) throws NomeUserInvalidoException {
        if (eNomeValido(nome)) {
            this.nome = nome;
        } else {
            throw new NomeUserInvalidoException(nome + ": Nome inv´alido");
        }
    }

    /*public void setNascimento(Data nascimento) {
        this.nascimento = nascimento;
    }*/

    private boolean eNomeValido(String nome) {
        if (nome == null) {
            return false;
        }
        if (nome.length() < 3) {
            return false;
        }
        for (int i = 0; i < nome.length(); i++) {
            if (nome.charAt(i) >= '0' && nome.charAt(i) <= '9')
                return false;
        }
        return true;
    }
    
}
