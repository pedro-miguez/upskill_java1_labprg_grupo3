/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Grupo 3
 */
public class RegistarColaboradorUI extends Application {
    
    @FXML
    private Button btn_RegistarColaborador;
    
    @FXML
    private TextField nomeColaborador;

    @FXML
    private TextField funcaoColaborador;

    @FXML
    private TextField contactoColaborador;

    @FXML
    private TextField emailColaborador;

    
    @FXML
    void Registar_Colaborador(ActionEvent event) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
