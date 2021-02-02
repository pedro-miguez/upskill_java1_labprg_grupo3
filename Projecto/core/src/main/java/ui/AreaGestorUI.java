/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Grupo 3
 */
public class AreaGestorUI {
    @FXML
    private Button btn_RegistarColaborador;

    @FXML
    private TextField contactoColaborador;

    @FXML
    private TextField nomeColaborador;

    @FXML
    private TextField emailColaborador;

    @FXML
    private TextField funcaoColaborador;

     @FXML
    private GridPane regColaboradorPane;
    
    @FXML
    void registarColaboradorAction(ActionEvent event) {
        try {
            boolean registou = 
            
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar novo colaborador.",
                    registou ? "Colaborador registado com sucesso." : "Não foi possível registar o colaborador.").show();
            
        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Introduza os dados novamente!").show();
        }
    }

    @FXML
    void limparAction(ActionEvent event) {
        nomeColaborador.clear();
        funcaoColaborador.clear();
        contactoColaborador.clear();
        emailColaborador.clear();
    }

    @FXML
    void sairAction(ActionEvent event) {
        MainApp.screenController.activate("JanelaInicial");
    }
     
    @FXML
    void Registar_Colaborador(ActionEvent event) {
        regColaboradorPane.setVisible(true);
        regColaboradorPane.setDisable(false);
    }

}
