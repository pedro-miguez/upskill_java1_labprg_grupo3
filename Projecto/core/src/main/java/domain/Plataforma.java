package domain;

import application.UsersAPI;
import persistence.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Current class enables to create a new Platform object which hosts some of the critical functions available in the app. For this
 * reason it is implemented as a Singleton Class. This design pattern only allows to instantiate one object of this kind, meaning
 * that if a new one is attempted to be built, the program will return the existing one. Some of the critical functions that the class
 * hosts are a method to save or load data via files, or establish/reset the connection to the users API.
 */
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

    /**
     * Gets instance Plataforma.
     *
     * @return the instance Plataforma that already exists or creates a new one in case none exists.
     */
    public static Plataforma getInstance() {
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
     * Method that enables to save data.
     *
     * @return the boolean
     */
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

    /**
     * Method that enables to load data.
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void carregarDados() throws IOException, ClassNotFoundException {
        Plataforma.plataforma = new Plataforma();
        Path file = Paths.get(PLATAFORMA_FILE);
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(file.toString()));
        Plataforma.plataforma = (Plataforma) o.readObject();
        o.close();
    }


    /**
     * Gets collaborator repository
     *
     * @return the collaborator repository
     */
    public RepositorioColaborador getRepoColab() {
        return repoColab;
    }

    /**
     * Gets organization repository
     *
     * @return the organization repository
     */
    public RepositorioOrganizacao getRepoOrg() {
        return repoOrg;
    }

    /**
     * Gets technical competence repository
     *
     * @return the technical competence repository
     */
    public RepositorioCompetenciaTecnica getRepoCompTec() {
        return repoCompTec;
    }

    /**
     * Gets activity area repository
     *
     * @return the activity area repository
     */
    public RepositorioAreaAtividade getRepoAreaAtiv() {
        return repoAreaAtiv;
    }

    /**
     * Gets user repository.
     *
     * @return the user repository.
     */
    public RepositorioUtilizador getRepoUser() {
        return repoUser;
    }

    /**
     * Gets task repository
     *
     * @return the task repository
     */
    public RepositorioTarefa getRepoTarefa() { return repoTarefa; }

    /**
     * Gets task category repository
     *
     * @return the task category repository
     */
    public RepositorioCategoriaTarefa getRepoCategoriaTarefa() {
        return repoCategoriaTarefa;
    }

    /**
     * Method that allows to reset user api.
     */
    public void resetUserAPI() {
        this.uapi = new UsersAPI();
    }

}
