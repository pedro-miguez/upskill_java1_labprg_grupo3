/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.AuthenticationController;
import application.EfetuarCandidaturaController;
import application.ServiceController;
import domain.Anuncio;
import domain.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import persistence.RepositorioFreelancer;

public class AreaFreelancerUI implements Initializable {

    public BorderPane homeBorderPane;
    public BorderPane homePaneAreaFreelancer;
    @FXML
    private Button btnConfirmarCandidatura;
    @FXML
    private TextArea txtMotivacao;
    @FXML
    private ListView<Anuncio> listViewAnunciosMatchedFreelancer;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnEfetuarCandidatura;
    @FXML
    private BorderPane efetuarCandidaturaPane;
    @FXML
    private TextField txtDuracaoDias;
    @FXML
    private TextField txtValorPretendido;
    @FXML
    private TextArea txtApresentacao;
    @FXML
    private Button btnLimparDadosEfetuarCandidatura;

    private ServiceController serviceController;
    private AuthenticationController authenticationController;
    private EfetuarCandidaturaController efetuarCandidaturaController;

    public void initialize(URL location, ResourceBundle resources) {

        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        efetuarCandidaturaController = new EfetuarCandidaturaController();

    }

    @FXML
    void confirmarCandidaturaAction(ActionEvent event) throws SQLException {

        try{

            boolean efetuou = efetuarCandidaturaController.efetuarCandidatura(listViewAnunciosMatchedFreelancer.getSelectionModel().getSelectedItem(),
                    authenticationController.getEmail(),
                    LocalDate.now(), Double.parseDouble(txtValorPretendido.getText()), Integer.parseInt(txtDuracaoDias.getText()),
                    txtApresentacao.getText(), txtMotivacao.getText());


            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Efetuar nova candidatura.",
                    efetuou ? "Candidatura efetuada com sucesso! \n\n" +
                            serviceController.getCandidaturatoStringCompletoByAnuncioFreelancer(
                                    listViewAnunciosMatchedFreelancer.getSelectionModel().getSelectedItem(),
                                    authenticationController.getEmail())
                            : "Não foi possível efetuar a candidatura.").show();

            if (efetuou) {
                limparDados();
                try {
                    listViewAnunciosMatchedFreelancer.getItems().setAll(efetuarCandidaturaController.getAnunciosMatched(
                            authenticationController.getEmail()));
                } catch (Exception e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Erro ao preencher a lista de anúncios.",
                            e.getMessage()).show();
                }
                txtValorPretendido.requestFocus();
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

    @FXML
    void btnLimparDadosEfetuarCandidaturaAction(ActionEvent event) {
        limparDados();
    }

    public void limparDados() {
        txtValorPretendido.clear();
        txtDuracaoDias.clear();
        txtApresentacao.clear();
        txtMotivacao.clear();
    }



    @FXML
    void logoutAction(ActionEvent event) {

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            //limparDados();
            try {
                authenticationController.logout();
                serviceController.resetUserAPI();
                voltarJanelaInicial();
                //desliga
                efetuarCandidaturaPane.setDisable(true);
                efetuarCandidaturaPane.setVisible(false);
                //liga
                homePaneAreaFreelancer.setDisable(false);
                homePaneAreaFreelancer.setVisible(true);
            } catch (SQLException e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Erro de SQL.",
                        e.getMessage()).show();
            }

        }

    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }


    public void goHomeSelectAction(ActionEvent actionEvent) {
        //desliga
        efetuarCandidaturaPane.setDisable(true);
        efetuarCandidaturaPane.setVisible(false);
        //liga
        homePaneAreaFreelancer.setDisable(false);
        homePaneAreaFreelancer.setVisible(true);
    }

    @FXML
    void efetuarCandidaturaAction(ActionEvent event) throws SQLException {
        homePaneAreaFreelancer.setDisable(true);
        homePaneAreaFreelancer.setVisible(false);
        efetuarCandidaturaPane.setDisable(false);
        efetuarCandidaturaPane.setVisible(true);

        try {
            listViewAnunciosMatchedFreelancer.getItems().setAll(efetuarCandidaturaController.getAnunciosMatched(
                    authenticationController.getEmail()));
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro ao preencher a lista de anúncios.",
                    e.getMessage()).show();
        }


    }


}


