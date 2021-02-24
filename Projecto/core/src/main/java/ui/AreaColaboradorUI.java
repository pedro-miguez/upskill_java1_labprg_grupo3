/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import application.*;
import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import persistence.RepositorioAnuncio;

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
    public BorderPane IniciarSeriacaoPane;
    public ListView listViewAnunciosSeriarAnuncio;
    public BorderPane seriacaoManualPane;
    public ListView listViewCandidaturasPorSelecionarSeriacaoManual;
    public ListView listViewColaboradoresPorSelecionarSeriacaoManual;
    public ListView listViewCandidaturasSelecionadasSeriacaoManual;
    public ListView listViewColaboradoresSelecionadosSeriacaoManual;
    public Button adicionarColaboradorSeriacaoManualAction;
    public Button removerUltimoColaboradorSeriacaoManualAction;
    public BorderPane seriacaoAutomaticaPane;
    public ListView listViewCandidaturasSeriarAnuncioSeriacaoAutomatica;
    public BorderPane homePane;


    private DefinirTarefaController criarTarefaController;
    private PublicarTarefaController publicarTarefaController;
    private ServiceController serviceController;
    private AuthenticationController authenticationController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarTarefaController = new DefinirTarefaController();
        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        publicarTarefaController = new PublicarTarefaController();

        try {
            comboCategoriaTarefa.getItems().setAll(serviceController.getCategoriasTarefa());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //popular combo boxes do painel Publicar Tarefa
        try {
            btnTipoRegimento.getItems().setAll(serviceController.getTiposRegimento());
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
        homePane.setVisible(false);
        homePane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        IniciarSeriacaoPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);

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
            try {
                limparDados();
                authenticationController.logout();
                serviceController.resetUserAPI();
                voltarJanelaInicial();
            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Problema ao fazer logout.",
                        e.getMessage()).show();
            }

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

        try {
            boolean publicou = publicarTarefaController.publicarTarefa(listViewTarefasMatchedPublicarTarefa.getSelectionModel().getSelectedItem(),
                    btnTipoRegimento.getValue(), Data.dataAtual(), btnDataFimPub.getValue(), btnDataInicioCand.getValue(),
                    btnDataFimCand.getValue(), btnDataInicioSeriacao.getValue(), btnDataFimSeriacao.getValue());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar novo Anúncio.",
                    publicou ? "Anúncio criado com sucesso! \n\n" +
                            serviceController.getAnunciotoStringCompletoByTarefa(listViewTarefasMatchedPublicarTarefa.getSelectionModel().getSelectedItem())
                            : "Não foi possível criar o anúncio.").show();

            if (publicou) {
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

    public void btnLimparDadosPublicarTarefaAction(ActionEvent actionEvent) {
    }

    public void btnPublicarTarefaSelectAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        IniciarSeriacaoPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);

        //ligar
        publicarTarefaPane.setVisible(true);
        publicarTarefaPane.setDisable(false);


        //popular elementos
        try {
            listViewTarefasMatchedPublicarTarefa.getItems().setAll(serviceController.getTarefasOrganizacao(authenticationController.getEmail()));
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Problema preencher lista de tarefas.",
                    e.getMessage()).show();
        }

    }

    public void iniciarSeriacaoAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        IniciarSeriacaoPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);
//        try {
//        if(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem().getTipoRegimento().getDesignacao().equalsIgnoreCase("automático")){

        //ligar
        seriacaoAutomaticaPane.setDisable(false);
        seriacaoAutomaticaPane.setVisible(true);
//        }else
        //ligar
        //seriacaoManualPane.setDisable(false);
        //seriacaoManualPane.setVisible(true);
//        } catch (Exception e) {
//            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
//                    "Problema ao seriar anúncios.",
//                    e.getMessage()).show();
//        }
    }

    public void finalizarSeriacaoManualAction(ActionEvent actionEvent) {
    }

    public void voltarSeriacaoManualAction(ActionEvent actionEvent) {

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar ao menu de seriação.", "Deseja mesmo fazer voltar?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            try {
            listViewCandidaturasSelecionadasSeriacaoManual.getItems().clear();
            listViewCandidaturasPorSelecionarSeriacaoManual.getItems().clear();
            listViewColaboradoresPorSelecionarSeriacaoManual.getItems().clear();
            listViewColaboradoresSelecionadosSeriacaoManual.getItems().clear();
            seriacaoManualPane.setDisable(true);
            seriacaoManualPane.setVisible(false);
            IniciarSeriacaoPane.setVisible(true);
            IniciarSeriacaoPane.setDisable(false);
            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Problema ao voltar ao menu anterior.",
                        e.getMessage()).show();
            }
        }
    }

    public void classificarCandidaturaSeriacaoManualAction(ActionEvent actionEvent) {
    }

    public void removerUltimaCandidaturaSeriacaoManualAction(ActionEvent actionEvent) {
    }

    public void confirmarSeriacaoAutomaticaAction(ActionEvent actionEvent) {
    }

    public void voltarSeriacaoAutomaticaAction(ActionEvent actionEvent) {

        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar ao menu de seriação.", "Deseja mesmo fazer voltar?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            try {
                listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().clear();
                seriacaoAutomaticaPane.setDisable(true);
                seriacaoAutomaticaPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Problema ao voltar ao menu anterior.",
                        e.getMessage()).show();
            }
        }

    }

    public void btnSeriarAnuncioSelectAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);

        //ligar
        IniciarSeriacaoPane.setDisable(false);
        IniciarSeriacaoPane.setVisible(true);
        listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.);
    }

    public void goHomeSelectAction(ActionEvent actionEvent) {

        IniciarSeriacaoPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);

        //ligar
        homePane.setVisible(true);
        homePane.setDisable(false);


    }
}
