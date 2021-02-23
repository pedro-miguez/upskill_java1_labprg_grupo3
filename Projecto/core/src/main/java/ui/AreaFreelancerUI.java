/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.AuthenticationController;
//import application.DefinirTarefaController;
import application.ServiceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;


package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AreaFreelancerUI {

    @FXML
    private Button btnConfirmarCandidatura;

    @FXML
    private TextArea txtMotivacao;

    @FXML
    private ListView<?> listViewAnunciosMatchedFreelancer;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnEfetuarCandidatura;

    @FXML
    private BorderPane publicarTarefaPane;

    @FXML
    private TextField txtDuracaoDias;

    @FXML
    private TextField txtValorPretendido;

    @FXML
    private TextArea txtApresentacao;

    @FXML
    private Button btnLimparDadosEfetuarCandidatura;

    private ServiceController serviceController;
    private AuthenticationController authenticationController;

    public void initialize(URL location, ResourceBundle resources) {

        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        
        /*try {
            //(...)
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }

    @FXML
    void confirmarCandidaturaAction(ActionEvent event) {

    }

    @FXML
    void btnLimparDadosEfetuarCandidaturaAction(ActionEvent event) {

    }

    @FXML
    void efetuarCandidaturaAction(ActionEvent event) {

    }

    @FXML
    void logoutAction(ActionEvent event) {

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            //limparDados();
            authenticationController.logout();
            serviceController.resetUserAPI();
            voltarJanelaInicial();
        }

    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }

}


