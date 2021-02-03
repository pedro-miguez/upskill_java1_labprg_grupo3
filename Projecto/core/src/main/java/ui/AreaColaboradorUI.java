/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.DefinirTarefaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Grupo 3
 */

/**
 * 
 * The type AreaColaboradorUI
 */
public class AreaColaboradorUI {

    public TextField txtCodigoUnico;
    public TextField txtCustoEstimado;
    public TextField txtDuracaoEstimada;
    public TextField txtDesignacao1;
    public Button btnCriarTarefaSelect;
    @FXML
    private TextField txtCusto;

    @FXML
    private Button sair;

    @FXML
    private Button criarTarefa;

    @FXML
    private Button submeterTarefa;

    @FXML
    private TextField txtDesignacao;

    @FXML
    private TextArea txtDescTec;

    @FXML
    private TextField txtPrazo;

    @FXML
    private ComboBox<?> comboCategoria;

    @FXML
    private TextArea txtDescInf;

    @FXML
    private Button limparDados;

    
    @FXML
    private GridPane criarTarefaPane;
    
    
    private DefinirTarefaController criarTarefaController;
    
    @FXML //falta definir o parametro referencia!
    void submeterTarefaAction(ActionEvent event) {
        /*try {
            
            boolean criou = criarTarefaController.definirTarefa(referencia, txtDesignacao.getText().trim(), 
                    txtDescInf.getText().trim(), txtDescTec.getText().trim(), 
                    txtPrazo.getText().trim(), txtCusto.getText().trim(), 
                    comboCategoria.getValue());
            
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, 
                    MainApp.TITULO_APLICACAO, "Criar nova tarefa.",
                    criou ? "Tarefa criada com sucesso."
                            : "Não foi possível criar a tarefa.").show();
            
        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, 
                    "Erro nos dados.",
                    "Introduza os dados novamente!").show();
        }*/

    }

    @FXML
    void limparDadosAction(ActionEvent event) {
        //referencia.clear();
        txtDesignacao.clear();
        txtDescInf.clear();
        txtDescTec.clear();
        txtPrazo.clear();
        txtCusto.clear();
        comboCategoria.setValue(null);

    }

    @FXML
    void sairAction(ActionEvent event) {
        MainApp.screenController.activate("JanelaInicial");

    }

    @FXML
    void criarTarefaAction(ActionEvent event) {
        
        criarTarefaPane.setVisible(true);
        criarTarefaPane.setDisable(false);

    }

    public void criarTarefaSelectAction(ActionEvent actionEvent) {
    }
}
