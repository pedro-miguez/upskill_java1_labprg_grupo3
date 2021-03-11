/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.AuthenticationController;
import application.DefinirTarefaController;
import application.PlataformaController;
import domain.CategoriaTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AreaColaboradorUI implements Initializable {


    public Button btnCriarTarefaSelect;
    public Button btnLogout;
    public TextField txtNomeTarefa;
    public ComboBox<CategoriaTarefa> comboCategoriaTarefa;
    public TextField txtDuracaoEstimadaTarefa;
    public TextArea txtDescricaoTecnicaTarefa;
    public TextField txtCustoEstimadoTarefa;
    public TextField txtCodigoUnicoTarefa;
    public TextArea txtDescricaoInformalTarefa;
    public Button btnLimparDadosTarefa;


    private DefinirTarefaController criarTarefaController;
    private PlataformaController plataformaController;
    private AuthenticationController authenticationController;
    @FXML
    private Label l1;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Label l2;
    @FXML
    private Button submeterTarefa;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private Label l7;
    @FXML
    private Label l8;
    @FXML
    private Label l9;
    @FXML
    private Label l3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarTarefaController = new DefinirTarefaController();
        plataformaController = new PlataformaController();
        authenticationController = new AuthenticationController();
        comboCategoriaTarefa.getItems().setAll(plataformaController.getCategoriasTarefa());
    }

    //método para submeter a criação de tarefa
    @FXML
    void submeterTarefaAction(ActionEvent event) {
        try {
            boolean criou = criarTarefaController.definirTarefa(
                    txtCodigoUnicoTarefa.getText().trim(),
                    txtNomeTarefa.getText().trim(),
                    txtDescricaoInformalTarefa.getText().trim(),
                    txtDescricaoTecnicaTarefa.getText().trim(),
                    Integer.parseInt(txtDuracaoEstimadaTarefa.getText().trim()),
                    Float.parseFloat(txtCustoEstimadoTarefa.getText().trim()),
                    comboCategoriaTarefa.getValue(),
                    authenticationController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar nova tarefa.",
                    criou ? "Tarefa criada com sucesso! \n\n" +
                            plataformaController.getTarefaToStringCompletoByCodigoUnico(txtCodigoUnicoTarefa.getText().trim())
                            : "Não foi possível criar a tarefa.").show();

            if (criou) {
                limparDados();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        }

    }

    @FXML
    public void criarTarefaSelectAction(ActionEvent actionEvent) {
        comboCategoriaTarefa.getItems().setAll(plataformaController.getCategoriasTarefa());
    }

    //logout
    @FXML
    public void logoutAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            limparDados();
            authenticationController.logout();
            plataformaController.resetUserAPI();
            voltarJanelaInicial();
        }
    }

    //limpa todos os campos
    @FXML
    public void btnLimparDadosTarefaAction(ActionEvent actionEvent) {
        limparDados();
    }
    public void limparDados() {
        txtCodigoUnicoTarefa.clear();
        txtNomeTarefa.clear();
        txtDescricaoInformalTarefa.clear();
        txtDescricaoTecnicaTarefa.clear();
        txtDuracaoEstimadaTarefa.clear();
        txtCustoEstimadoTarefa.clear();
        comboCategoriaTarefa.setValue(null);
    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }

    @FXML
    private void handleColorPickerAction(ActionEvent event) {
        
        l1.setTextFill(colorPicker.getValue());
        
        l2.setTextFill(colorPicker.getValue());
        
        l3.setTextFill(colorPicker.getValue());
        
        l4.setTextFill(colorPicker.getValue());
        
        l5.setTextFill(colorPicker.getValue());
        
        l6.setTextFill(colorPicker.getValue());
        
        l7.setTextFill(colorPicker.getValue());
        
        l8.setTextFill(colorPicker.getValue());
        
        l9.setTextFill(colorPicker.getValue());
        
    }

}
