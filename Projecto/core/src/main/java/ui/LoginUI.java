package ui;

import domain.Plataforma;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.applet.Main;

public class LoginUI {
    public TextField username;
    public PasswordField password;
    public Button entrar;
    public Button voltar;

    public void InserirUsername(ActionEvent actionEvent) {
    }

    public void inserirPassword(ActionEvent actionEvent) {
    }

    public void GoPlataforma(ActionEvent actionEvent) {
        boolean login = Plataforma.getInstance().getUsersAPI().login(username.getText(), password.getText());
        if (login) {
            System.out.println("login com sucesso");
        }

    }

    public void VoltarMenu(ActionEvent actionEvent) {
        MainApp.screenController.activate("JanelaInicial");
    }
}
