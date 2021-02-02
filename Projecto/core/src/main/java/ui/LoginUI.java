package ui;

import application.AuthenticationController;
import domain.Plataforma;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.applet.Main;

public class LoginUI {
    public TextField username;
    public PasswordField password;
    public Button entrar;
    public Button voltar;

    private AuthenticationController loginControl = new AuthenticationController();


    public void GoPlataforma(ActionEvent actionEvent) {
        boolean login = Plataforma.getInstance().getUsersAPI().login(username.getText(), password.getText());
        if (login) {
            switch(loginControl.getRole()) {
                case "gestor":
                    MainApp.screenController.activate("AreaGestor");
                    break;
                case "administrativo":
                    MainApp.screenController.activate("AreaAdministrativo");
                    break;
                case "colaborador":
                    MainApp.screenController.activate("AreaColaborador");
                    break;
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Username ou password inválidos").show();
        }

    }

    public void VoltarMenu(ActionEvent actionEvent) {
        MainApp.screenController.activate("JanelaInicial");
    }
}
