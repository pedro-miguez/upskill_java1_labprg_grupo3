package ui;

import domain.Plataforma;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JanelaInicialUI {



    public void registarOrganizacaoAction(ActionEvent actionEvent) {
        MainApp.screenController.activate("RegistarOrganizacao");
    }

    public void loginAction(ActionEvent actionEvent) {
        MainApp.screenController.activate("Login");
    }
}
