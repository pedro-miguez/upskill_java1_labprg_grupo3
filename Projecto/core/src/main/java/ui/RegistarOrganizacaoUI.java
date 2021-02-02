package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistarOrganizacaoUI extends Application {

    @FXML
    private TextField Localidade_Org;

    @FXML
    private TextField website;

    @FXML
    private TextField FuncaoGestor;

    @FXML
    private Button limparDados;

    @FXML
    private TextField NIF_Org;

    @FXML
    private Button Send_Info;

    @FXML
    private TextField Tlf_Org;

    @FXML
    private TextField NomeGestor;

    @FXML
    private TextField ContactoGestor;

    @FXML
    private TextField Nome_Org;

    @FXML
    private TextField Email_Org;

    @FXML
    private Button voltar;

    @FXML
    private TextField CP_Org;

    @FXML
    private TextField Morada_Org;

    @FXML
    private TextField EmailGestor;

    @FXML
    void Sending_Info_Org(ActionEvent event) {
        /*try {
            AplicacaoController appController = janelaPrincipalUI.getAplicacaoController();

            boolean adicionou = appController.adicionarContactoTelefonico(
                    txtNome.getText().trim(),
                    Integer.parseInt(txtTelefone.getText()));
            if (adicionou) {
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
                    iae.getMessage()).show();
        }*/
    }

    @FXML
    void Clear_Info(ActionEvent event) {
        this.CP_Org.clear();
        this.Localidade_Org.clear();
        this.Email_Org.clear();
        this.EmailGestor.clear();
        this.ContactoGestor.clear();
        this.Morada_Org.clear();
        this.FuncaoGestor.clear();
        this.NIF_Org.clear();
        this.Nome_Org.clear();
        this.NomeGestor.clear();
        this.Tlf_Org.clear();
        this.website.clear();
        this.NomeGestor.requestFocus();
    }

    @FXML
    void GotoMain(ActionEvent event) {
        MainApp.screenController.activate("JanelaInicial");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}


