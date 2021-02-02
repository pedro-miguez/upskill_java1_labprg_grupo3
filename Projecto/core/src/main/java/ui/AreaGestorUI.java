/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    }

    @FXML
    void limparAction(ActionEvent event) {

    }

    @FXML
    void sairAction(ActionEvent event) {

    }
     
    @FXML
    void Registar_Colaborador(ActionEvent event) {
        regColaboradorPane.setVisible(true);
        regColaboradorPane.setDisable(false);
    }

}
