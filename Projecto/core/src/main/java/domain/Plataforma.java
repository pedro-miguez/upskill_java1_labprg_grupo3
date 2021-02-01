package domain;

import api.UsersAPI;

public class Plataforma {
    private static Plataforma plataforma;
    private AlgoritmoGeradorPasswords agp;
    private UsersAPI uapi;

    private Plataforma() {
        //Geração de passwords
        agp = new AlgoritmoGeradorPasswords();

        //UsersAPI
        uapi = new UsersAPI();
    }

    public static Plataforma getInstance() {
        if (Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    public AlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return agp;
    }

    public UsersAPI getUsersAPI() {
        return uapi;
    }

    
    //responde a metodos
    
}
