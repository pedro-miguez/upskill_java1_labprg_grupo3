package application;

import domain.Plataforma;

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
}
