/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.AuthenticationController;
//import application.DefinirTarefaController;
import application.PlataformaController;
import domain.CategoriaTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;


/**
 *
 * @author Grupo 3
 */
public class AreaFreelancerUI implements Initializable {
    
    public Button btnCandidatar;
    
    public Button btnSair;
    
    public ListView<?> showAnuncios;
    
    //@FXML
    //private Button btnCandidatar;

    //@FXML
    //private ListView<?> showAnuncios;

    //@FXML
    //private Button btnSair;
    
    
    private PlataformaController plataformaController;
    private AuthenticationController authenticationController;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //candidatarAnuncioController ?? (...)
        
        plataformaController = new PlataformaController();
        authenticationController = new AuthenticationController();
        
        /*try {
            //(...)
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
    

    @FXML
    void candidatarTarefaAction(ActionEvent event) {

    }

    @FXML
    void sairAction(ActionEvent actionEvent) {
        
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            //limparDados();
            authenticationController.logout();
            plataformaController.resetUserAPI();
            voltarJanelaInicial();
        }

    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }
    
}
