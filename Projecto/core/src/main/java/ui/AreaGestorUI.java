/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.RegistarColaboradorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Grupo 3
 */
public class AreaGestorUI {
    public Button btnRegistarColaborador;
    public Button btnLimpar;
    public Button btnVoltarRegColab;
    public TextField txtCodigoUnicoTarefa;
    public TextField txtCustoTarefa;
    public TextArea txtDescInfTarefa;
    public TextArea txtDescTecnicaTarefa;
    public TextField txtDuracaoTarefa;
    public ComboBox comboCategoria;
    public TextField txtNomeTarefa;
    public Button btnRegistarTarefa;
    public Button btnLimparTArefa;
    public Button btnVoltarTarefa;
    public Button btnSelecionarRegistarColaborador;
    public Button btnSelecionarCriarTarefa;
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
     
     
    private RegistarColaboradorController registarColaboradorController;
     
    
    @FXML
    void registarColaboradorAction(ActionEvent event) {
        try {
            boolean registou = registarColaboradorController.Colaborador(nomeColaborador.getText().trim(), funcaoColaborador.getText().trim(), contactoColaborador.getText().trim(), emailColaborador.getText().trim());
            
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

    public void voltarActionRegColab(ActionEvent actionEvent) {
    }

    public void criarTarefaActionTarefa(ActionEvent actionEvent) {
    }

    public void limparActionTarefa(ActionEvent actionEvent) {
    }

    public void voltarActionTarefa(ActionEvent actionEvent) {
    }

    public void btnSelecionarRegistarColaboradorAction(ActionEvent actionEvent) {
    }

    public void btnSelecionarCriarTarefaAction(ActionEvent actionEvent) {
    }
}
