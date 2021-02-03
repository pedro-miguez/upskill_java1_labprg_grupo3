package domain;

import application.UsersAPI;
import persistence.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Plataforma implements Serializable {

    static final String PLATAFORMA_FILE = "dados_plataforma.dat";

    private static Plataforma plataforma;

    private AlgoritmoGeradorPasswords agp;
    private UsersAPI uapi;
    private RepositorioColaborador repoColab;
    private RepositorioOrganizacao repoOrg;
    private RepositorioCompetenciaTecnica repoCompTec;
    private RepositorioAreaAtividade repoAreaAtiv;
    private RepositorioUtilizador repoUser;
    private RepositorioTarefa repoTarefa;
    private RepositorioCategoriaTarefa repoCategoriaTarefa;


    private Plataforma() {
        agp = new AlgoritmoGeradorPasswords();
        uapi = new UsersAPI();

        repoColab = RepositorioColaborador.getInstance();
        repoOrg = RepositorioOrganizacao.getInstance();
        repoCompTec = RepositorioCompetenciaTecnica.getInstance();
        repoAreaAtiv = RepositorioAreaAtividade.getInstance();
        repoUser = RepositorioUtilizador.getInstance();
        repoTarefa = RepositorioTarefa.getInstance();
        repoCategoriaTarefa = RepositorioCategoriaTarefa.getInstance();
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

    public static boolean guardarDados(){
        Path file = Paths.get(PLATAFORMA_FILE);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toString()));
            Object obj = Plataforma.getInstance();
            out.writeObject(obj);
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void carregarDados() throws IOException, ClassNotFoundException {
        Plataforma.plataforma = new Plataforma();
        Path file = Paths.get(PLATAFORMA_FILE);
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(file.toString()));
        Plataforma.plataforma = (Plataforma) o.readObject();
        o.close();
    }


    public RepositorioColaborador getRepoColab() {
        return repoColab;
    }

    public RepositorioOrganizacao getRepoOrg() {
        return repoOrg;
    }

    public RepositorioCompetenciaTecnica getRepoCompTec() {
        return repoCompTec;
    }

    public RepositorioAreaAtividade getRepoAreaAtiv() {
        return repoAreaAtiv;
    }
    public RepositorioCategoriaTarefa getRepoCategoriaTar() {
        return repoCategoriaTarefa;
    }

    public RepositorioUtilizador getRepoUser() {
        return repoUser;
    }

    public RepositorioTarefa getRepoTarefa() { return repoTarefa; }

    public RepositorioCategoriaTarefa getRepoCategoriaTarefa() {
        return repoCategoriaTarefa;
    }

}
