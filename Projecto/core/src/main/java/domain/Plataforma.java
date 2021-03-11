package domain;

import application.UsersAPI;
import network.ConnectionHandler;
import persistence.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

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
    private UsersAPI uapi;
    private ConnectionHandler connectionHandler;

    private Plataforma() throws SQLException {
        agp = new AlgoritmoGeradorPasswords();
        uapi = new UsersAPI();
        connectionHandler = new ConnectionHandler();
    }

    /**
     * Gets instance Plataforma.
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

    /**
     * Gets users api.
     *
     * @return the users api
     */
    public UsersAPI getUsersAPI() {
        return uapi;
    }


    /**
     * Method that allows to reset user api.
     */
    public void resetUserAPI() {
        this.uapi = new UsersAPI();
    }

    /**
     * Gets a connection handler.
     * 
     * @return connectionHandler
     */
    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }
}
