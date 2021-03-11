package ui;

import application.PlataformaController;
import application.RegistarOrganizacaoController;
import domain.Plataforma;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.RepositorioColaborador;
import persistence.RepositorioOrganizacao;
import persistence.RepositorioUtilizador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;


public class RegistarOrganizacaoUI implements Initializable {

    public TextField txtNomeGestor;
    public TextField txtContactoGestor;
    public TextField txtEmailGestor;
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

    private PlataformaController plataformaController;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
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
    private Label l10;
    @FXML
    private Label l11;
    @FXML
    private Label l12;
    @FXML
    private Label l13;
    @FXML
    private Label l14;
    @FXML
    private ColorPicker colorPicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plataformaController = new PlataformaController();
    }


    //registar organização
    @FXML
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
                    Integer.parseInt(txtContactoGestor.getText()),
                    txtEmailGestor.getText().trim()
            );

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar nova organização.",
                    added ? "Organização criada com sucesso! \n\n" +
                            plataformaController.getOrganizacaoToStringCompletoByEmail(txtEmailOrg.getText().trim())
                            : "Não foi possível registar a organização.").show();
            if (added) {
                limparDados();
                voltarJanelaInicial();
            }

        } catch (NumberFormatException nfe) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Letras em campos de valores numéricos (NIF, Contacto Gestor ou Telefone Organização) ou campos em vazio.").show();
        } catch (IllegalArgumentException iae) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        }
    }

    //limpar todos os campos
    @FXML
    public void limparAction(ActionEvent actionEvent) {
        limparDados();
    }

    public void limparDados() {
        txtNomeOrg.clear();
        txtNIFOrg.clear();
        txtWebsiteOrg.clear();
        txtTelefoneOrg.clear();
        txtEmailOrg.clear();
        txtMoradaOrg.clear();
        txtLocalidadeOrg.clear();
        txtCodigoPostalOrg.clear();
        txtNomeGestor.clear();
        txtContactoGestor.clear();
        txtEmailGestor.clear();
    }

    //voltar ao menu inicial
    @FXML
    public void voltarAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Voltar à janela inicial",
                "Vai perder os dados inseridos!", "Deseja mesmo voltar à janela inicial?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            limparDados();
            voltarJanelaInicial();
        }
    }

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
        l10.setTextFill(colorPicker.getValue());
        l11.setTextFill(colorPicker.getValue());
        l12.setTextFill(colorPicker.getValue());
        l13.setTextFill(colorPicker.getValue());
        l14.setTextFill(colorPicker.getValue());
        
    }


}


