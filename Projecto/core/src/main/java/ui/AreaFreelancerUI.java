/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.*;
import domain.Anuncio;
import domain.Candidatura;
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

    public BorderPane homePaneAreaFreelancer;
    public ListView<Candidatura> listViewCandidaturasAbertas;
    public Button btnAtualizarCandidatura1;
    public Button btnRemoverCandidatura;
    public Button btnVoltarHome;
    public Button btnConsultarCandidatura;
    public BorderPane consultarCandidaturaPane;
    public BorderPane atualizarCandidaturaPane;
    public Button btnConfirmarAtualizarCandidatura;
    public Button btnLimparDadosAtualizarCandidatura;
    public ListView<Anuncio> listViewAnuncioAtualizarCandidaturaFreelancer;
    public TextField txtValorPretendidoAtualizarCandidatura;
    public TextField txtDuracaoDiasAtualizarCandidatura;
    public TextArea txtApresentacaoAtualizarCandidatura;
    public TextArea txtMotivacaoAtualizarCandidatura;
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
    private AtualizarCandidaturaController atualizarCandidaturaController;
    private RemoverCandidaturaController removerCandidaturaController;

    public void initialize(URL location, ResourceBundle resources) {

        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        efetuarCandidaturaController = new EfetuarCandidaturaController();
        atualizarCandidaturaController = new AtualizarCandidaturaController();
        removerCandidaturaController = new RemoverCandidaturaController();

    }

    @FXML
    void confirmarCandidaturaAction(ActionEvent event) throws SQLException {

        try {

            boolean efetuou = efetuarCandidaturaController.efetuarCandidatura(listViewAnunciosMatchedFreelancer.getSelectionModel().getSelectedItem(),
                    authenticationController.getEmail(),
                     Double.parseDouble(txtValorPretendido.getText()), Integer.parseInt(txtDuracaoDias.getText()),
                    txtApresentacao.getText(), txtMotivacao.getText());


            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Efetuar nova candidatura.",
                    efetuou ? "Candidatura efetuada com sucesso! \n\n" +
                            serviceController.getCandidaturatoStringCompletoByAnuncioFreelancer(
                                    listViewAnunciosMatchedFreelancer.getSelectionModel().getSelectedItem(),
                                    authenticationController.getEmail())
                            : "Não foi possível efetuar a candidatura.").showAndWait();

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
        txtApresentacaoAtualizarCandidatura.clear();
        txtValorPretendidoAtualizarCandidatura.clear();
        txtDuracaoDiasAtualizarCandidatura.clear();
        txtMotivacaoAtualizarCandidatura.clear();
        listViewAnuncioAtualizarCandidaturaFreelancer.getItems().clear();

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
        //desligar
        efetuarCandidaturaPane.setDisable(true);
        efetuarCandidaturaPane.setVisible(false);
        consultarCandidaturaPane.setDisable(true);
        consultarCandidaturaPane.setVisible(false);
        atualizarCandidaturaPane.setDisable(true);
        atualizarCandidaturaPane.setVisible(false);
        //ligar
        homePaneAreaFreelancer.setDisable(false);
        homePaneAreaFreelancer.setVisible(true);
    }

    @FXML
    void efetuarCandidaturaAction(ActionEvent event) throws SQLException {
        //desligar
        homePaneAreaFreelancer.setDisable(true);
        homePaneAreaFreelancer.setVisible(false);
        consultarCandidaturaPane.setDisable(true);
        consultarCandidaturaPane.setVisible(false);
        atualizarCandidaturaPane.setDisable(true);
        atualizarCandidaturaPane.setVisible(false);
        //ligar
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


    public void btnAtualizarCandidaturaAction(ActionEvent actionEvent) {
        //ligar
        atualizarCandidaturaPane.setDisable(false);
        atualizarCandidaturaPane.setVisible(true);

        //desligar
        consultarCandidaturaPane.setDisable(true);
        consultarCandidaturaPane.setVisible(false);
        efetuarCandidaturaPane.setDisable(true);
        efetuarCandidaturaPane.setVisible(false);
        homePaneAreaFreelancer.setDisable(true);
        homePaneAreaFreelancer.setVisible(false);

        listViewAnuncioAtualizarCandidaturaFreelancer.getItems().setAll(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getAnuncio());
        txtValorPretendidoAtualizarCandidatura.setText(String.valueOf(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getValorPretendido()));
        txtDuracaoDiasAtualizarCandidatura.setText(String.valueOf(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getNrDias()));
        txtApresentacaoAtualizarCandidatura.setText(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getTxtApresentacao());
        txtMotivacaoAtualizarCandidatura.setText(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getTxtMotivacao());
    }

    public void btnRemoverCandidaturaAction(ActionEvent actionEvent) throws SQLException {

        if (listViewCandidaturasAbertas.getSelectionModel().getSelectedItem() != null) {
            Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Remover Candidatura",
                    "Deseja mesmo remover essa candidatura?", "Confirme a ação.");
            if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                actionEvent.consume();
            } else {
                boolean removeu = removerCandidaturaController.removerCandidatura(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getAnuncio(),
                        authenticationController.getEmail(), listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getValorPretendido(),
                        listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getNrDias(), listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getTxtApresentacao(),
                        listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getTxtMotivacao());

                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Remover candidatura.",
                        removeu ? "Candidatura removida com sucesso! \n"
                                : "Não foi possível remover a candidatura.").show();

                if (removeu) {
                    try {
                        listViewCandidaturasAbertas.getItems().remove(listViewCandidaturasAbertas.getSelectionModel().getSelectedItem());
                        listViewCandidaturasAbertas.getItems().setAll(atualizarCandidaturaController.getCandidaturasAbertasFreelancer(
                                authenticationController.getEmail()));
                    } catch (Exception e) {
                        AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                                "Erro ao preencher a lista de candidaturas.",
                                e.getMessage()).show();
                    }
                }
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Remover candidatura.",
                    "Não selecionou nenhuma candidatura para remover!").showAndWait();
        }
    }

    public void btnVoltarHomeAction(ActionEvent actionEvent) {
        //ligar
        homePaneAreaFreelancer.setDisable(false);
        homePaneAreaFreelancer.setVisible(true);
        //desligar
        consultarCandidaturaPane.setDisable(true);
        consultarCandidaturaPane.setVisible(false);
        atualizarCandidaturaPane.setDisable(true);
        atualizarCandidaturaPane.setVisible(false);
        efetuarCandidaturaPane.setDisable(true);
        efetuarCandidaturaPane.setVisible(false);


    }

    public void consultarCandidaturaAction(ActionEvent actionEvent) {
        //ligar
        consultarCandidaturaPane.setDisable(false);
        consultarCandidaturaPane.setVisible(true);
        //desligar
        atualizarCandidaturaPane.setDisable(true);
        atualizarCandidaturaPane.setVisible(false);
        efetuarCandidaturaPane.setDisable(true);
        efetuarCandidaturaPane.setVisible(false);
        homePaneAreaFreelancer.setDisable(true);
        homePaneAreaFreelancer.setVisible(false);

        try {
            listViewCandidaturasAbertas.getItems().setAll(atualizarCandidaturaController.getCandidaturasAbertasFreelancer(
                    authenticationController.getEmail()));
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro ao preencher a lista de candidaturas.",
                    e.getMessage()).show();
            e.printStackTrace();
        }

    }

    public void confirmarAtualizarCandidaturaAction(ActionEvent actionEvent) {
        try {

            boolean atualizou = atualizarCandidaturaController.atualizarCandidatura(listViewAnuncioAtualizarCandidaturaFreelancer.getItems().get(0),
                    authenticationController.getEmail(), Double.parseDouble(txtValorPretendidoAtualizarCandidatura.getText()),
                    Integer.parseInt(txtDuracaoDiasAtualizarCandidatura.getText()), txtApresentacaoAtualizarCandidatura.getText(),
                    txtMotivacaoAtualizarCandidatura.getText());


            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Atualizar candidatura.",
                    atualizou ? "Candidatura atualizada com sucesso! \n\n" +
                            serviceController.getCandidaturatoStringCompletoByAnuncioFreelancer(
                                    listViewCandidaturasAbertas.getSelectionModel().getSelectedItem().getAnuncio(),
                                    authenticationController.getEmail())
                            : "Não foi possível atualizar a candidatura.").showAndWait();


            if (atualizou) {
                limparDados();
                try {
                    listViewCandidaturasAbertas.getItems().setAll(atualizarCandidaturaController.getCandidaturasAbertasFreelancer(
                            authenticationController.getEmail()));
                } catch (Exception e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Erro ao preencher a lista de candidaturas.",
                            e.getMessage()).show();
                }

                //ligar
                consultarCandidaturaPane.setDisable(false);
                consultarCandidaturaPane.setVisible(true);
                //desligar
                atualizarCandidaturaPane.setDisable(true);
                atualizarCandidaturaPane.setVisible(false);
                efetuarCandidaturaPane.setDisable(true);
                efetuarCandidaturaPane.setVisible(false);
                homePaneAreaFreelancer.setDisable(true);
                homePaneAreaFreelancer.setVisible(false);


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

    public void btnLimparDadosAtualizarCandidaturaAction(ActionEvent actionEvent) {

        txtApresentacaoAtualizarCandidatura.clear();
        txtValorPretendidoAtualizarCandidatura.clear();
        txtDuracaoDiasAtualizarCandidatura.clear();
        txtMotivacaoAtualizarCandidatura.clear();
        txtValorPretendidoAtualizarCandidatura.requestFocus();
    }

    public void btnVoltarAtualizarCandidaturaAction(ActionEvent actionEvent) {
        //ligar
        consultarCandidaturaPane.setDisable(false);
        consultarCandidaturaPane.setVisible(true);
        //desligar
        atualizarCandidaturaPane.setDisable(true);
        atualizarCandidaturaPane.setVisible(false);
        efetuarCandidaturaPane.setDisable(true);
        efetuarCandidaturaPane.setVisible(false);
        homePaneAreaFreelancer.setDisable(true);
        homePaneAreaFreelancer.setVisible(false);

        try {
            listViewCandidaturasAbertas.getItems().setAll(atualizarCandidaturaController.getCandidaturasAbertasFreelancer(
                    authenticationController.getEmail()));
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro ao preencher a lista de candidaturas.",
                    e.getMessage()).show();
            e.printStackTrace();
        }
    }
}


