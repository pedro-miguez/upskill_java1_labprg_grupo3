package com.company.model;

//import application.UsersAPI;

//import com.company.controller.UsersAPI;

//import network.ConnectionHandler;
//import persistence.*;

import com.company.exception.ElementoNaoExistenteException;

import com.company.exception.NomeDuplicadoException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Current class enables to create a new Platform object which hosts some of the 
 * critical functions available in the app. For this reason it is implemented as 
 * a Singleton Class. This design pattern only allows to instantiate one object 
 * of this kind, meaning that if a new one is attempted to be built, the program 
 * will return the existing one. Some of the critical functions that the class
 * hosts are a method to save or load data via files, or establish/reset the 
 * connection to the users API.
 */
public class Plataforma implements Serializable {

    static final String PLATAFORMA_FILE = "dados_plataforma.dat";

    private static Plataforma plataforma;

    private AlgoritmoGeradorPasswords agp;
    //private UsersAPI uapi;
    //private ConnectionHandler connectionHandler;
    
    
    private String nome;
    private ArrayList<Freelancer> freelancers;
    
    private ArrayList<Organizacao> organizacoes;

    public Plataforma(String nome) {
        this.nome = nome;
        this.freelancers = new ArrayList<Freelancer>();
        
        this.organizacoes = new ArrayList<Organizacao>();
    }

    public ArrayList<Freelancer> getAllFreelancers() {
        Freelancer freelancer;
        ArrayList<Freelancer> lista = new ArrayList<>();
        for (int i = 0; i < this.freelancers.size(); i++) {
            freelancer = this.freelancers.get(i);
            if (!(freelancer instanceof Plataforma)) {
                Freelancer copiaFreelancer = new Freelancer(freelancer);
                lista.add(copiaFreelancer);
            } else {
                //
            }
        }
        return lista;
    }
    
    public ArrayList<Organizacao> getAllOrganizacoess() {
        
        Organizacao organizacao;
        
        ArrayList<Organizacao> lista = new ArrayList<>();
        
        for (int i = 0; i < this.organizacoes.size(); i++) {
            organizacao = this.organizacoes.get(i);
            
            if (!(organizacao instanceof Colaborador)) {
                Organizacao copiaOrganizacao = new Organizacao(organizacao);
                lista.add(copiaOrganizacao);
            } else {
                Colaborador colaboradorColaborador = new Colaborador((Colaborador) organizacao);
                lista.add(colaboradorColaborador);
            }
        }
        
        return lista;
    }
    

    /*private Plataforma() throws SQLException {
        agp = new AlgoritmoGeradorPasswords();
        uapi = new UsersAPI();
        connectionHandler = new ConnectionHandler();
    }*/

    /**
     * Gets instance Plataforma.
     *
     *
     * @return the instance Plataforma that already exists or creates a new one in case none exists.
     */
    public static Plataforma getInstance() throws SQLException {
        if (Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    /**
     * Gets password generator algorithm.
     *
     * @return the password generator algorithm.
     */
    public AlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return agp;
    }

    ////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<Organizacao> getOrganizacoes() {
        Organizacao organizacao;
        ArrayList<Organizacao> lista = new ArrayList<>();
        for (int i = 0; i < this.organizacoes.size(); i++) {
            
            organizacao = this.organizacoes.get(i);
            if (!(organizacao instanceof Gestor)) {
                Organizacao copia = new Organizacao(organizacao);
                lista.add(copia);
            }
        }
        return lista;
    }

    public Organizacao getOrganizacao(String nome) {
        return getOrganizacaoByNome(nome);
    }

    public void addOrganizacao(Organizacao organizacao) throws NomeDuplicadoException {
        Organizacao o = getOrganizacaoByNome(organizacao.getNome());
        if (o == null) {
            this.organizacoes.add(organizacao);
            
        } else {
            throw new NomeDuplicadoException(o.getNome() + ": Nome já existe");
        }
    }

    public void updateOrganizacao(String nome, Organizacao organizacao) throws ElementoNaoExistenteException {
        Organizacao organizacao = null;
        
        boolean updated = false;
        
        for (int i = 0; i < this.organizacoes.size() && !updated; i++) {
            organizacao = this.organizacoes.get(i);
            if (organizacao.getNome() == nome) {
                organizacao = o;
                updated = true;
            }
        }
        if (updated == false) {
            throw new ElementoNaoExistenteException(nome + ": Não existe essa organizacao");
        }
    }

    public void removeOrganizacao(String nome) throws ElementoNaoExistenteException {
        Organizacao organizacao = null;
        for (int i = 0; i < this.organizacoes.size(); i++) {
            organizacao = this.organizacoes.get(i);
            
            if (organizacao.getNome() == nome) {
                if (!(organizacao instanceof Plataforma)) {
                    this.organizacoes.remove(i);
                    
                    return;
                } else {
                    throw new ElementoNaoExistenteException(nome + ": Não é uma organizacao");
                }
            }
        }
        throw new ElementoNaoExistenteException(nome + ": Não existe essa organizacao");
    }

    ////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<Colaborador> getColaboradores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Colaborador getColaborador(int nr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeColaborador(int nr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addColaborador(Colaborador colaborador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateColaborador(int nr, Colaborador colaborador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<Freelancer> getAllFreelancers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Freelancer getFreelancer(long nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addFreelancer(Freelancer freelancer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateFreelancer(long nif, Freelancer freelancer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeFreelancer(long nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * Gets users api.
     *
     * @return the users api
     */
    /*public UsersAPI getUsersAPI() {
        return uapi;
    }


    /**
     * Method that allows to reset user api.
     */
    /*public void resetUserAPI() {
        this.uapi = new UsersAPI();
    }

    /**
     * Gets a connection handler.
     * @return connectionHandler
     */
    /*public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }*/
    
}
