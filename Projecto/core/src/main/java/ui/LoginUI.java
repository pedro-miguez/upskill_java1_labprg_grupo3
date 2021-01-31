package ui;

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
    }

    public void VoltarMenu(ActionEvent actionEvent) {
        MainApp.screenController.activate("JanelaInicial");
    }
}
