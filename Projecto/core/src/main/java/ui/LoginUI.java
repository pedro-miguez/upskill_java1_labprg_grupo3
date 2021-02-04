package ui;

import application.AuthenticationController;
import application.PlataformaController;
import domain.Plataforma;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sun.applet.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {

    public Button btnLogin;
    public Button btnVoltar;
    public TextField txtUsername;
    public PasswordField txtPassoword;

    private AuthenticationController authController;
    private PlataformaController plataformaController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authController = new AuthenticationController();
        plataformaController = new PlataformaController();
    }


    //efectuar o login, renovando a user API
    public void loginAction(ActionEvent actionEvent) {
        login();
    }

    //voltar à janela inicial
    public void voltarAction(ActionEvent actionEvent) {
        MainApp.screenController.activate("JanelaInicial");
    }


    //efectuar o login quando se carrega no Enter
    public void entrarButtonActivate(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    public void login() {
        boolean login = authController.login(txtUsername.getText(), txtPassoword.getText());
        if (login) {
            //plataformaController.resetUserAPI();
            txtPassoword.clear();
            txtUsername.clear();
            switch(authController.getRole()) {
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
}
