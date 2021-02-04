package application;

import domain.*;

public class AuthenticationController {

    public boolean login(String username, String password) {
        return Plataforma.getInstance().getUsersAPI().login(username, password);
    }

    public boolean logout() {
        return Plataforma.getInstance().getUsersAPI().logout();
    }

    public String getRole() {
        return Plataforma.getInstance().getUsersAPI().getRole();
    }

    public String getEmail() {
        return Plataforma.getInstance().getUsersAPI().getEmail();
    }

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
