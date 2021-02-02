package ui;

import application.RegistarOrganizacaoController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;
import persistence.RepositorioUtilizador;


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
        try {
            RegistarOrganizacaoController controller = new RegistarOrganizacaoController();

            boolean added = controller.registarOrganizacao(
                    txtNomeOrg.getText().trim(),
                    Integer.parseInt(txtNIFOrg.getText()),
                    txtWebsiteOrg.getText().trim(),
                    Integer.parseInt(txtTelefoneOrg.getText()),
                    txtEmailOrg.getText().trim(),
                    txtMoradaOrg.getText().trim(),
                    txtLocalidadeOrg.getText().trim(),
                    txtCodigoPostalOrg.getText().trim(),
                    txtNomeGestor.getText().trim(),
                    Integer.parseInt(txtTelefoneOrg.getText()), //MUDAR PARA TELEFONE GESTOR
                    txtEmailGestor.getText().trim()
                    );

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registo de Organização",
                    added ? "Organização registada com sucesso"
                            : "Não foi possível registar a organização").show();

            voltarJanelaInicial();

        } catch (IllegalArgumentException iae) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        }
    }

    public void limparAction(ActionEvent actionEvent) {
        txtNomeOrg.clear();
        txtNIFOrg.clear();
        txtWebsiteOrg.clear();
        txtTelefoneOrg.clear();
        txtEmailOrg.clear();
        txtMoradaOrg.clear();
        txtLocalidadeOrg.clear();
        txtCodigoPostalOrg.clear();
        txtNomeGestor.clear();
        //txtTelefoneOrg.getText()); //MUDAR PARA TELEFONE GESTOR
        txtEmailGestor.clear();
    }

    public void voltarAction(ActionEvent actionEvent) {
        voltarJanelaInicial();
    }

    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }
}


