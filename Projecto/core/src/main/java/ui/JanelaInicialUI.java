package ui;

import domain.Plataforma;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JanelaInicialUI {

    public Button login;
    public Button registar;

    public void GoToLogin(ActionEvent actionEvent) {
        MainApp.screenController.activate("Login");
    }

    public void RegistarOrganizacao(ActionEvent actionEvent) {
        MainApp.screenController.activate("RegistarOrganizacao");
    }

}
