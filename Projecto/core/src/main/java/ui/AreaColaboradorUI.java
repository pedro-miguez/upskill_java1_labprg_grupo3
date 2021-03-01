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
import java.util.ArrayList;
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
    public ListView<Anuncio> listViewAnunciosSeriarAnuncio;
    public BorderPane seriacaoManualPane;
    public ListView<Candidatura> listViewCandidaturasPorSelecionarSeriacaoManual;
    public ListView<Colaborador> listViewColaboradoresPorSelecionarSeriacaoManual;
    public ListView<Candidatura> listViewCandidaturasSelecionadasSeriacaoManual;
    public ListView<Colaborador> listViewColaboradoresSelecionadosSeriacaoManual;
    public Button adicionarColaboradorSeriacaoManualAction;
    public Button removerUltimoColaboradorSeriacaoManualAction;
    public BorderPane seriacaoAutomaticaPane;
    public ListView<Candidatura> listViewCandidaturasSeriarAnuncioSeriacaoAutomatica;
    public BorderPane homePane;
    public Button btnRemoverUltimaCandidatura;
    public Button btnRemoverUltimoColaborador;


    private DefinirTarefaController criarTarefaController;
    private PublicarTarefaController publicarTarefaController;
    private ServiceController serviceController;
    private AuthenticationController authenticationController;
    private SeriarCandidaturaController seriarCandidaturaController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        criarTarefaController = new DefinirTarefaController();
        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        publicarTarefaController = new PublicarTarefaController();
        seriarCandidaturaController = new SeriarCandidaturaController();



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
        seriacaoManualPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
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
        btnDataFimPub.getEditor().clear();
        btnDataFimCand.getEditor().clear();
        btnDataFimSeriacao.getEditor().clear();
        btnDataInicioCand.getEditor().clear();
        btnDataInicioSeriacao.getEditor().clear();
        btnTipoRegimento.setValue(null);
    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }

    public void publicarTarefaAction(ActionEvent actionEvent) {

        try {

            boolean publicou = publicarTarefaController.publicarTarefa(
                    listViewTarefasMatchedPublicarTarefa.getSelectionModel().getSelectedItem(),
                    btnTipoRegimento.getValue(),
                    Data.dataAtual(),
                    btnDataFimPub.getValue(),
                    btnDataInicioCand.getValue(),
                    btnDataFimCand.getValue(),
                    btnDataInicioSeriacao.getValue(),
                    btnDataFimSeriacao.getValue(),
                    authenticationController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar novo Anúncio.",
                    publicou ? "Anúncio criado com sucesso! \n\n" +
                            serviceController.getAnunciotoStringCompletoByTarefa(listViewTarefasMatchedPublicarTarefa.
                                    getSelectionModel().getSelectedItem())
                            : "Não foi possível criar o anúncio.").show();

            if (publicou) {
                limparDados();
                try {
                    listViewTarefasMatchedPublicarTarefa.getItems().setAll(serviceController.getTarefasOrganizacao(authenticationController.getEmail()));
                } catch (Exception e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Problema preencher lista de tarefas.",
                            e.getMessage()).show();
                }
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    "Datas inválidas ou campos em falta");
        }
    }

    public void btnLimparDadosPublicarTarefaAction(ActionEvent actionEvent) {
        limparDados();
    }

    public void btnPublicarTarefaSelectAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        seriacaoManualPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
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
        btnRemoverUltimaCandidatura.setDisable(true);
        btnRemoverUltimoColaborador.setDisable(true);
        listViewCandidaturasSelecionadasSeriacaoManual.getItems().clear();
        listViewColaboradoresSelecionadosSeriacaoManual.getItems().clear();
        try {
            if (seriarCandidaturaController.isSeriacaoAutomatica(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem())) {
                //ligar
                seriacaoAutomaticaPane.setDisable(false);
                seriacaoAutomaticaPane.setVisible(true);
                seriacaoManualPane.setDisable(true);
                seriacaoManualPane.setVisible(false);
                listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().setAll(seriarCandidaturaController.
                        candidaturasSeriadasPorValor(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem()));
            } else {
                //ligar
                seriacaoManualPane.setDisable(false);
                seriacaoManualPane.setVisible(true);
                seriacaoAutomaticaPane.setDisable(true);
                seriacaoAutomaticaPane.setVisible(false);
                listViewCandidaturasPorSelecionarSeriacaoManual.getItems().setAll(seriarCandidaturaController.
                        getAllCandidaturasPorSelecionar(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem()));
                listViewColaboradoresPorSelecionarSeriacaoManual.getItems().setAll(seriarCandidaturaController.
                        getAllColaboradoresOrganizacao(authenticationController.getEmail()));
            }

        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Problema ao iniciar seriação.",
                    e.getMessage()).show();
        }
    }

    public void finalizarSeriacaoManualAction(ActionEvent actionEvent) {
        try {
            boolean criou = seriarCandidaturaController.criarProcessoSeriacao(
                    listViewCandidaturasSelecionadasSeriacaoManual.getItems(),
                    listViewColaboradoresSelecionadosSeriacaoManual.getItems(),
                    authenticationController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Processo de seriação.",
                    criou ? "Processo de seriação realizado com sucesso!" :
                            "Não foi possível realizar o processo de seriação").show();

            if (criou) {
                listViewCandidaturasSelecionadasSeriacaoManual.getItems().clear();
                listViewCandidaturasPorSelecionarSeriacaoManual.getItems().clear();
                listViewColaboradoresPorSelecionarSeriacaoManual.getItems().clear();
                listViewColaboradoresSelecionadosSeriacaoManual.getItems().clear();
                seriacaoManualPane.setDisable(true);
                seriacaoManualPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
                btnRemoverUltimoColaborador.setDisable(true);
                btnRemoverUltimaCandidatura.setDisable(true);
                try {
                    listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.getAllAnunciosSeriacao(authenticationController.getEmail()));
                } catch (SQLException e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Problema preencher lista de tarefas.",
                            e.getMessage()).show();
                }

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
                btnRemoverUltimaCandidatura.setDisable(true);
                btnRemoverUltimoColaborador.setDisable(true);
            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Problema ao voltar ao menu anterior.",
                        e.getMessage()).show();
            }
        }
    }

    public void classificarCandidaturaSeriacaoManualAction(ActionEvent actionEvent) {
        if (listViewCandidaturasPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem() != null) {
            if(!listViewCandidaturasSelecionadasSeriacaoManual.getItems().contains(
                    listViewCandidaturasPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem())) {
                listViewCandidaturasSelecionadasSeriacaoManual.getItems().add(
                        listViewCandidaturasPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem());
                if (btnRemoverUltimaCandidatura.isDisable()) {
                    btnRemoverUltimaCandidatura.setDisable(false);
                }
                listViewCandidaturasPorSelecionarSeriacaoManual.getItems().remove(
                        listViewCandidaturasPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem());
            }

        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar candidatura",
                    "É obrigatório escolher uma candidatura para adicionar!").show();
        }
    }

    public void removerUltimaCandidaturaSeriacaoManualAction(ActionEvent actionEvent) {
        listViewCandidaturasPorSelecionarSeriacaoManual.getItems().add(
                listViewCandidaturasSelecionadasSeriacaoManual.getItems().get(listViewCandidaturasSelecionadasSeriacaoManual.getItems().size() - 1));

        listViewCandidaturasSelecionadasSeriacaoManual.getItems().remove(listViewCandidaturasSelecionadasSeriacaoManual.getItems().size() - 1);

        if (listViewCandidaturasSelecionadasSeriacaoManual.getItems().size() == 0) {
            btnRemoverUltimaCandidatura.setDisable(true);
        }
    }

    public void adicionarColaboradorSeriacaoManualAction(ActionEvent actionEvent) {
        if (listViewColaboradoresPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem() != null) {
            if(!listViewColaboradoresSelecionadosSeriacaoManual.getItems().contains(
                    listViewColaboradoresPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem())) {

                listViewColaboradoresSelecionadosSeriacaoManual.getItems().add(
                        listViewColaboradoresPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem());
                if (btnRemoverUltimoColaborador.isDisable()) {
                    btnRemoverUltimoColaborador.setDisable(false);
                }
                listViewColaboradoresPorSelecionarSeriacaoManual.getItems().remove(
                        listViewColaboradoresPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem());
            }

        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar candidatura",
                    "É obrigatório escolher uma candidatura para adicionar!").show();
        }
    }

    public void removerUltimoColaboradorSeriacaoManualAction(ActionEvent actionEvent) {
        listViewColaboradoresPorSelecionarSeriacaoManual.getItems().add(
                listViewColaboradoresSelecionadosSeriacaoManual.getItems().get(listViewColaboradoresSelecionadosSeriacaoManual.getItems().size() - 1));
        listViewColaboradoresSelecionadosSeriacaoManual.getItems().remove(listViewColaboradoresSelecionadosSeriacaoManual.getItems().size() - 1);

        if (listViewColaboradoresSelecionadosSeriacaoManual.getItems().size() == 0) {
            btnRemoverUltimoColaborador.setDisable(true);
        }
    }

    public void confirmarSeriacaoAutomaticaAction(ActionEvent actionEvent) {
        try {

            boolean seriou = seriarCandidaturaController.criarProcessoSeriacao(listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems(),
                    new ArrayList<Colaborador>(),
                    authenticationController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Seriação de Candidaturas.",
                    seriou ? "Seriação Automática realizada com sucesso! \n\n"
                            : "Não foi possível seriar automáticamente as candidaturas.").show();
            if (seriou) {
                listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().clear();
                seriacaoAutomaticaPane.setDisable(true);
                seriacaoAutomaticaPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
                try {
                    listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.getAllAnunciosSeriacao(authenticationController.getEmail()));
                } catch (SQLException e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Problema preencher lista de tarefas.",
                            e.getMessage()).show();
                }
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    "Datas inválidas ou campos em falta");
        }

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
        seriacaoManualPane.setDisable(true);
        seriacaoManualPane.setVisible(false);

        //ligar
        IniciarSeriacaoPane.setDisable(false);
        IniciarSeriacaoPane.setVisible(true);
        try {
            listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.getAllAnunciosSeriacao(authenticationController.getEmail()));
        } catch (SQLException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Problema preencher lista de tarefas.",
                    e.getMessage()).show();
        }
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
        seriacaoManualPane.setDisable(true);
        seriacaoManualPane.setVisible(false);

        //ligar
        homePane.setVisible(true);
        homePane.setDisable(false);


    }


}
