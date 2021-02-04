package application;

import domain.*;

/**
 * JavaFX controller works based on MVC(Model-View-Controller) and can be achieved by FXML (EFF-ects eXtended Markup Language).
 * FXML is an XML based language used to develop the graphical user interfaces for JavaFX applications as in the HTML.
 * FXML can be used to build an entire GUI application scene or part of a GUI application scene.
 * This FXML allows developers for separate User Interface logic from the business logic.
 * If suppose User Interface in your JavaFX application, then no need to compile the application even if we have done some
 * changes to the application. If we want, we can edit the FXML in the editor and re-run the app.
 *
 * Current class is the one responsible to connect the GUI with the methods responsible for users log in according to their role.
 */
public class AuthenticationController {

    /**
     * Login boolean.
     *
     * @param username as username
     * @param password as password
     * @return the boolean
     */
    public boolean login(String username, String password) {
        return Plataforma.getInstance().getUsersAPI().login(username, password);
    }

    /**
     * Logout boolean.
     *
     * @return the boolean
     */
    public boolean logout() {
        return Plataforma.getInstance().getUsersAPI().logout();
    }

    /**
     * Gets role accordingly as this parameter will define the GUI that will be presented to the user.
     *
     * @return the role
     */
    public String getRole() {
        return Plataforma.getInstance().getUsersAPI().getRole();
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return Plataforma.getInstance().getUsersAPI().getEmail();
    }

    /**
     * Allows to register a new user as manager.
     *
     * @param colaborador as colaborator
     * @return the boolean
     */
    public boolean registarGestorComoUtilizador(Colaborador colaborador) {
        String nome = colaborador.getNome();
        String email = colaborador.getEmail().toString();

        AlgoritmoGeradorPasswords alg = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        String password = alg.geraPassword();
        System.out.println(password);

        UsersAPI uapi = Plataforma.getInstance().getUsersAPI();
        User user = new User(nome, password, new Email(email), Role.GESTOR);
        return uapi.registerUserWithRoles(nome, email, password, "gestor")
                && Plataforma.getInstance().getRepoUser().addUtilizador(user);
    }

    /**
     * Allows to register a new user not as a manager.
     *
     * @param colaborador as colaborator
     * @return the boolean
     */
    public boolean registarColaboradorComoUtilizador(Colaborador colaborador) {
        String nome = colaborador.getNome();
        String email = colaborador.getEmail().toString();

        AlgoritmoGeradorPasswords alg = Plataforma.getInstance().getAlgoritmoGeradorPwd();
        String password = alg.geraPassword();
        System.out.println(password);

        UsersAPI uapi = Plataforma.getInstance().getUsersAPI();
        User user = new User(nome, password, new Email(email), Role.COLABORADOR);
        return uapi.registerUserWithRoles(nome, email, password, "colaborador")
                && Plataforma.getInstance().getRepoUser().addUtilizador(user);
    }
}
