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

/**
 *
 * @author Grupo 3
 */
public class AreaColaboradorUI {

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

    
    private DefinirTarefaController criarTarefaController;
    
    @FXML
    void submeterTarefaAction(ActionEvent event) {
        try {
            boolean criou = criarTarefaController.Tarefa(txtDesignacao.getText().trim(), 
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
        }

    }

    @FXML
    void limparDadosAction(ActionEvent event) {

    }

    @FXML
    void sairAction(ActionEvent event) {

    }

    @FXML
    void criarTarefaAction(ActionEvent event) {

    }
}
