/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.AuthenticationController;
import application.RegistarColaboradorController;
import domain.CategoriaTarefa;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Grupo 3
 */
public class AreaGestorUI extends Application {

    //Registar Colaborador elements
    public Button btnRegistarColaborador;
    public Button btnLimparRegistarColaborador;

    public TextField txtNomeColaborador;
    public TextField txtContactoColaborador;
    public TextField txtEmailColaborador;


    //Criar Tarefa Elements
    public Button btnRegistarTarefa;
    public Button btnLimparTarefa;

    public TextField txtCodigoUnicoTarefa;
    public TextField txtCustoTarefa;
    public TextArea txtDescInfTarefa;
    public TextArea txtDescTecnicaTarefa;
    public TextField txtDuracaoTarefa;
    public ComboBox<CategoriaTarefa> comboCategoria;
    public TextField txtNomeTarefa;


    //General Elements
    public Button btnSelecionarRegistarColaborador;
    public Button btnSelecionarCriarTarefa;
    public Button btnLogout;

    public BorderPane criarTarefaPane;
    public BorderPane registarColaboradorPane;

    private RegistarColaboradorController registarColaboradorController;
    private AuthenticationController authController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        registarColaboradorController = new RegistarColaboradorController();
        authController = new AuthenticationController();
        comboCategoria.getItems().setAll()
    }

    @FXML
    void registarColaboradorAction(ActionEvent event) {
        try {
            boolean registou = registarColaboradorController.Colaborador(txtNomeColaborador.getText().trim(),
                    txtContactoColaborador.getText().trim(),
                    txtEmailColaborador.getText().trim(),
                    authController.getEmail()
                    );
            
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar novo colaborador.",
                    registou ? "Colaborador registado com sucesso." : "Não foi possível registar o colaborador.").show();
            
        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }


    public void criarTarefaActionTarefa(ActionEvent actionEvent) {
    }

    public void limparActionTarefa(ActionEvent actionEvent) {
    }

    public void btnSelecionarRegistarColaboradorAction(ActionEvent actionEvent) {
        registarColaboradorPane.setVisible(true);
        registarColaboradorPane.setDisable(false);
    }

    public void btnSelecionarCriarTarefaAction(ActionEvent actionEvent) {
    }

    public void btnLogoutAction(ActionEvent actionEvent) {
    }

    public void limparRegistarColaboradorAction(ActionEvent actionEvent) {
        txtNomeColaborador.clear();
        txtContactoColaborador.clear();
        txtEmailColaborador.clear();
    }


}
