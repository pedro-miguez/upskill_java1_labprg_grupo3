/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.AuthenticationController;
import application.DefinirTarefaController;
import application.ServiceController;
import domain.CategoriaTarefa;
import domain.Tarefa;
import domain.TipoRegimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AreaColaboradorUI implements Initializable {


    //Criar Tarefa
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
    public Button submeterTarefa;

    public BorderPane criarTarefaPane;


    //Publicar Tarefa
    public ListView<Tarefa> listViewTarefasMatchedPublicarTarefa;
    public DatePicker btnDataFimPub;
    public DatePicker btnDataInicioCand;
    public DatePicker btnDataFimCand;
    public DatePicker btnDataInicioSeriacao;
    public DatePicker btnDataFimSeriacao;
    public ComboBox<TipoRegimento> btnTipoRegimento;
    public Button btnLimparDadosPublicarTarefa;
    public Button btnPublicarTarefa;

    public BorderPane publicarTarefaPane;


    private DefinirTarefaController criarTarefaController;
    private ServiceController serviceController;
    private AuthenticationController authenticationController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarTarefaController = new DefinirTarefaController();
        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        try {
            comboCategoriaTarefa.getItems().setAll(serviceController.getCategoriasTarefa());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




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
                            serviceController.getTarefaToStringCompletoByCodigoUnico(txtCodigoUnicoTarefa.getText().trim(),
                                    authenticationController.getEmail())
                            : "Não foi possível criar a tarefa.").show();

            if (criou) {
                limparDados();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }

    }

    public void criarTarefaSelectAction(ActionEvent actionEvent) {
        //desligar
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);

        //ligar
        criarTarefaPane.setVisible(true);
        criarTarefaPane.setDisable(false);


        try {
            comboCategoriaTarefa.getItems().setAll(serviceController.getCategoriasTarefa());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //logout
    public void logoutAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            limparDados();
            authenticationController.logout();
            serviceController.resetUserAPI();
            voltarJanelaInicial();
        }
    }

    //limpa todos os campos
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

    public void publicarTarefaAction(ActionEvent actionEvent) {

    }

    public void btnLimparDadosPublicarTarefaAction(ActionEvent actionEvent) {
    }

    public void btnPublicarTarefaSelectAction(ActionEvent actionEvent) {
        //desligar
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);



        //ligar
        publicarTarefaPane.setVisible(true);
        publicarTarefaPane.setDisable(false);


        //popular elementos
        listViewTarefasMatchedPublicarTarefa.getItems().setAll(serviceController.getTarefasOrganizacao(authenticationController.getEmail()));
    }
}
