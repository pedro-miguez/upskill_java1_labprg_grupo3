package ui;

import application.RegistarOrganizacaoController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistarOrganizacaoUI extends Application {

    public TextField txtNomeGestor;
    public TextField txtContactoGestor;
    public TextField txtEmailGestor;
    public TextField txtFuncaoGestor;
    public TextField txtNomeOrg;
    public TextField txtNIFOrg;
    public TextField txtMoradaOrg;
    public TextField txtLocalidadeOrg;
    public TextField txtCodigoPostalOrg;
    public TextField txtTelefoneOrg;
    public TextField txtWebsiteOrg;
    public TextField txtEmailOrg;
    public Button btnVoltar;
    public Button limparDados;
    public Button registarOrganizacao;


    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void registarOrganizacaoAction(ActionEvent actionEvent) {
        /*try {
            RegistarOrganizacaoController controller = new RegistarOrganizacaoController();

            boolean added = controller.registarOrganizacao(
                    txtNomeOrg.getText().trim(),
                    Integer.parseInt(txtNIFOrg.getText()),
                    txtWebsiteOrg.getText().trim(),
                    Integer.parseInt(txtTelefoneOrg.getText()),
                    txtEmailOrg.getText().trim(),
                    );
            *//*if (adicionou) {
                janelaPrincipalUI.atualizaTextAreaListaTelefonica();
            }

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar novo contacto.",
                    adicionou ? "Contacto adicionado com sucesso."
                            : "Não foi possível adicionar o contacto.").show();

            encerrarNovoContactoUI(event);
        } catch (NumberFormatException nfe) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Introduza um valor numérico para telefone!").show();
        } catch (IllegalArgumentException iae) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();*//*
        }*/
    }

    public void limparAction(ActionEvent actionEvent) {
    }

    public void voltarAction(ActionEvent actionEvent) {
        MainApp.screenController.activate("JanelaInicial");
    }
}


