package ui;

import application.*;
import domain.*;
import exceptions.FetchingProblemException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AreaAdministrativoUI implements Initializable {

    //Criar Area de Atividade
    public BorderPane criarAreaAtividadePane;

    public Button btnConfirmarAreaAtividade;
    public Button btnLimparAreaAtividade;

    public TextField txtCodUnicoAreaAtividade;
    public TextField txtDescBreveAreaAtividade;
    public TextArea txtDescDetalhadaAreaAtividade;


    //Criar Categoria de Tarefa
    public BorderPane criarCategoriaTarefaPane;
    public TextField txtDescricaoCategoriaTarefa;

    public ComboBox<AreaAtividade> comboBoxAreaAtividadeCategoriaTarefa;
    public ComboBox<GrauProficiencia> comboBoxGrauProficienciaCategoriaTarefa;
    public ListView<CompetenciaTecnica> listViewCompTecnicasPorSelecionarCategoriaTarefa;
    public ListView<CaracterizacaoCompTec> listViewCompTecnicasSelecionadasCategoriaTarefa;

    public Button btnConfirmarAreaAtividadeCategoriaTarefa;

    public Button btnCompOpcionalCategoriaTarefa;
    public Button btnCompObrigatoriaCategoriaTarefa;
    public Button btnRemoverUltimaCompTecCategoriaTarefa;

    public Button btnConfirmarCategoriaTarefa;
    public Button btnLimparCategoriaTarefa;


    //Criar Competência Técnica
    public BorderPane criarCompetenciaTecnicaPane;

    public ComboBox<AreaAtividade> comboBoxAreaAtividadeCompetenciaTecnica;

    public Button btnConfirmarCompetenciaTecnica;
    public Button btnLimparCompetenciaTecnica;

    public TextArea txtDescDetalhadaCompetenciaTecnica;
    public TextField txtDescBreveCompetenciaTecnica;
    public TextField txtCodigoUnicoCompetenciaTecnica;


    public ListView<GrauProficiencia> listViewGrauProficienciaCompetenciaTecnica;
    public TextField txtNivelGrauProficienciaCriarCompetenciaTecnica;
    public TextField txtDesignacaoGrauProficienciaCriarCompetenciaTecnica;

    //Criar Freelancer

    public BorderPane criarFreelancerPane;
    public TextField txtNomeFreelancer;
    public TextField txtTelefoneFreelancer;
    public TextField txtEmailFreelancer;
    public TextField txtNIFFreelancer;

    public ComboBox<GrauProficiencia> comboBoxGrauProficienciaFreelancer;
    public ListView<CompetenciaTecnica> listViewCompTecnicasPorSelecionarFreelancer;
    public ListView<ReconhecimentoCT> listViewCompTecnicasSelecionadasFreelancer;
    public ListView<HabilitacaoAcademica> listViewHabilitacaoAcademicaRegistoFreelancer;

    public TextField txtGrauFreelancer;
    public TextField txtDesignacaoCurso;
    public TextField txtNomeInstituicao;
    public TextField txtMediaCurso;
    public Button btnAdicionarHabilitacaoAcademica;
    public Button btnRemoverUltimaHabilitacaoAcademicaAdicionada;

    public Button btnCompetenciaReconhecidaFreelancer;
    public Button btnRemoverUltimaCompTecFreelancer;

    public Button btnConfirmarCriacaoFreelancer;
    public Button btnLimparFreelancer;

    //Elementos gerais
    public Button btnCriarAreaAtividadeSelect;
    public Button btnCriarCategoriaTarefaSelect;
    public Button btnCriarCompetenciaTecnicaSelect;
    public Button btnCriarFreelancerSelect;

    public Button btnLogout;
    public Button btnRemoverGrauProficienciaCompetenciaTecnica;
    public BorderPane homePane;


    private DefinirAreaAtividadeController areaAtividadeController;
    private DefinirCompetenciaTecnicaController competenciaTecnicaController;
    private DefinirCategoriaTarefaController categoriaTarefaController;
    private ServiceController serviceController;
    private AuthenticationController authenticationController;
    private RegistarFreelancerController registarFreelancerController;

    static int grauCounter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //inicializar controllers
        areaAtividadeController = new DefinirAreaAtividadeController();
        competenciaTecnicaController = new DefinirCompetenciaTecnicaController();
        categoriaTarefaController = new DefinirCategoriaTarefaController();
        serviceController = new ServiceController();
        authenticationController = new AuthenticationController();
        registarFreelancerController = new RegistarFreelancerController();




    }


    // ## METODOS PARA CRIAÇAO DE OBJECTOS ######

    //criar área de atividade
    public void confirmarAreaAtividadeAction(ActionEvent event) {
        try {
            boolean adicionou = areaAtividadeController.definirAreaAtividade(txtCodUnicoAreaAtividade.getText().trim(),
                    txtDescBreveAreaAtividade.getText().trim(), txtDescDetalhadaAreaAtividade.getText().trim());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar nova área de atividade.",
                    adicionou ? "Area de atividade criada com sucesso! \n\n" +
                            serviceController.getAreaAtividadeToStringCompletoByCodigoUnico(txtCodUnicoAreaAtividade.getText().trim())
                            : "Não foi possível registar a área de atividade.").show();

            if (adicionou) {
                limparDadosAreaAtividade();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }

    }

    //criar categoria de tarefa
    public void confirmarCategoriaTarefaAction(ActionEvent actionEvent) {
        try {
            boolean adicionou = categoriaTarefaController.definirCategoriaTarefa(comboBoxAreaAtividadeCategoriaTarefa.getValue(),
                    txtDescricaoCategoriaTarefa.getText().trim(), listViewCompTecnicasSelecionadasCategoriaTarefa.getItems());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar nova categoria de tarefa.",
                    adicionou ? "Categoria de Tarefa criada com sucesso! \n\n" +
                            serviceController.getCategoriaTarefaToStringCompletoByNome(txtDescricaoCategoriaTarefa.getText().trim(),
                                    comboBoxAreaAtividadeCategoriaTarefa.getValue())
                            : "Não foi possível registar a categoria de tarefa.").show();

            if (adicionou) {
                limparDadosCategoriaTarefa();
                listViewGrauProficienciaCompetenciaTecnica.getItems().clear();
                try {
                    listViewCompTecnicasPorSelecionarCategoriaTarefa.getItems().setAll(serviceController.getCompetenciasTecnicasByAreaAtividade(comboBoxAreaAtividadeCategoriaTarefa.getValue()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                btnRemoverUltimaCompTecCategoriaTarefa.setDisable(true);
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }
    }

    //criar competencia tecnica
    public void confirmarCompetenciaTecnicaAction(ActionEvent event) {
        try {
            boolean adicionou = competenciaTecnicaController.definirCompetenciaTecnica(txtCodigoUnicoCompetenciaTecnica.getText().trim(),
                    comboBoxAreaAtividadeCompetenciaTecnica.getValue(), txtDescBreveCompetenciaTecnica.getText().trim(),
                    txtDescDetalhadaCompetenciaTecnica.getText().trim(), listViewGrauProficienciaCompetenciaTecnica.getItems());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar nova competência técnica.",
                    adicionou ? "Competência Técnica criada com sucesso! \n\n" +
                            serviceController.getCompetenciaTecnicaToStringCompletoByCodigoUnico(txtCodigoUnicoCompetenciaTecnica.getText().trim(), comboBoxAreaAtividadeCompetenciaTecnica.getValue() )
                            : "Não foi possível registar a Competência Técnica.").show();

            if (adicionou) {
                limparDadosCompetenciaTecnica();
                listViewGrauProficienciaCompetenciaTecnica.getItems().clear();
                grauCounter = 0;
                btnRemoverGrauProficienciaCompetenciaTecnica.setDisable(true);
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }

    }

    //criar freelancer
    public void confirmarCriacaoFreelancerAction(ActionEvent actionEvent) {
        try {
            boolean adicionou = registarFreelancerController.registarFreelancer(txtNomeFreelancer.getText().trim(), Integer.parseInt(txtTelefoneFreelancer.getText()),
                    txtEmailFreelancer.getText().trim(), Integer.parseInt(txtNIFFreelancer.getText()), listViewCompTecnicasSelecionadasFreelancer.getItems(),
                    listViewHabilitacaoAcademicaRegistoFreelancer.getItems());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar novo Freelancer.",
                    adicionou ? "Freelancer criado com sucesso! \n\n" +
                            serviceController.getFreelancerToStringCompletoByEmail(txtEmailFreelancer.getText().trim())
                            : "Não foi possível registar o freelancer.").show();

            if (adicionou) {
                limparDadosFreelancer();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }
    }

    // ##########################################






    // ## METODOS PARA MUDAR O PAINEL VISIVEL ###

    //mudar para o painel Criar Area de Atividade
    public void criarAreaAtividadeSelectAction(ActionEvent actionEvent) {
        //desligar
        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        criarFreelancerPane.setVisible(false);
        criarFreelancerPane.setDisable(true);
        homePane.setDisable(true);
        homePane.setVisible(false);

        //ligar
        criarAreaAtividadePane.setVisible(true);
        criarAreaAtividadePane.setDisable(false);
        txtCodUnicoAreaAtividade.requestFocus();

        //atualizar

    }

    //mudar para o painel Criar Categoria de Tarefa
    public void criarCategoriaTarefaSelectAction(ActionEvent actionEvent) throws SQLException {
        //desligar
        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);
        criarFreelancerPane.setVisible(false);
        criarFreelancerPane.setDisable(true);
        homePane.setDisable(true);
        homePane.setVisible(false);

        //ligar
        criarCategoriaTarefaPane.setVisible(true);
        criarCategoriaTarefaPane.setDisable(false);
        txtDescricaoCategoriaTarefa.requestFocus();


        //popular elementos
        comboBoxAreaAtividadeCategoriaTarefa.getItems().setAll(serviceController.getAreasAtividade());
    }

    //mudar para o painel Criar Competencia Tecnica
    public void criarCompetenciaTecnicaSelectAction(ActionEvent actionEvent) throws SQLException {
        //desligar
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);
        criarFreelancerPane.setVisible(false);
        criarFreelancerPane.setDisable(true);
        homePane.setDisable(true);
        homePane.setVisible(false);

        //ligar
        criarCompetenciaTecnicaPane.setVisible(true);
        criarCompetenciaTecnicaPane.setDisable(false);
        txtCodigoUnicoCompetenciaTecnica.requestFocus();


        //popular combo box do painel Criar Competencia Tecnica
        try {
            comboBoxAreaAtividadeCompetenciaTecnica.getItems().setAll(serviceController.getAreasAtividade());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //mudar para o painel Criar Freelancer
    public void criarFreelancerSelectAction(ActionEvent actionEvent) throws SQLException {
        //desligar
        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        homePane.setDisable(true);
        homePane.setVisible(false);

        //ligar
        criarFreelancerPane.setVisible(true);
        criarFreelancerPane.setDisable(false);
        txtNomeFreelancer.requestFocus();

        //popular elementos
        listViewCompTecnicasPorSelecionarFreelancer.getItems().setAll(serviceController.getAllCompetenciasTecnicas());
    }
    //mudar para o painel home
    public void goHomeSelectAction(ActionEvent actionEvent) {
        //desliga
        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        criarFreelancerPane.setVisible(false);
        criarFreelancerPane.setDisable(true);
        //liga
        homePane.setDisable(false);
        homePane.setVisible(true);
    }

    // ##########################################




    // ## METODOS UTILITARIOS ###################

    //confirmar área de atividade selecionada para popular o ListView de competencias tecnicas
    public void comboBoxAreaAtividadeCategoriaTarefaSelectAction(ActionEvent actionEvent) {
        try {
            listViewCompTecnicasPorSelecionarCategoriaTarefa.getItems().setAll(serviceController.getCompetenciasTecnicasByAreaAtividade(comboBoxAreaAtividadeCategoriaTarefa.getValue()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().clear();
    }

    //adicionar a competência técnica selecionada com o grau de proficiencia e obrigatoriedade verdadeira
    public void compObrigatoriaCategoriaTarefaAction(ActionEvent actionEvent) {
        if (comboBoxGrauProficienciaCategoriaTarefa.getValue() != null &&
                listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem() != null) {
            CaracterizacaoCompTec ct = new CaracterizacaoCompTec(
                    listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem(),
                    true,
                    comboBoxGrauProficienciaCategoriaTarefa.getValue());

            if (competenciaTecnicaAindaNaoFoiAdicionada(listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem())) {
                listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().add(ct);
                btnRemoverUltimaCompTecCategoriaTarefa.setDisable(false);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar nova competencia tecnica.",
                        "Não é possível adicionar a mesma competência técnica mais do que uma vez.").show();
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar nova competencia tecnica.",
                    "É obrigatório escolher um grau de proficiência").show();
        }

    }



    //adicionar a competência técnica selecionada com o grau de proficiencia e obrigatoriedade falso
    public void compOpcionalCategoriaTarefaAction(ActionEvent actionEvent) {
        if (comboBoxGrauProficienciaCategoriaTarefa.getValue() != null &&
                listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem() != null) {
            CaracterizacaoCompTec ct = new CaracterizacaoCompTec(
                    listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem(),
                    false,
                    comboBoxGrauProficienciaCategoriaTarefa.getValue());

            if (competenciaTecnicaAindaNaoFoiAdicionada(listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem())) {
                listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().add(ct);
                btnRemoverUltimaCompTecCategoriaTarefa.setDisable(false);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar nova competencia tecnica.",
                        "Não é possível adicionar a mesma competência técnica mais do que uma vez.").show();
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Adicionar nova competencia tecnica.",
                    "É obrigatório escolher um grau de proficiência").show();
        }



    }

    public boolean competenciaTecnicaAindaNaoFoiAdicionada(CompetenciaTecnica competenciaTecnica) {
        for(CaracterizacaoCompTec cct : listViewCompTecnicasSelecionadasCategoriaTarefa.getItems()) {
            if (cct.getCompetenciaTecnica().equals(competenciaTecnica)) {
                return false;
            }
        }
        return true;
    }

    public boolean grauProficienciaAindaNaoFoiAdicionado(GrauProficiencia grauProficiencia) {
        for(GrauProficiencia gp : listViewGrauProficienciaCompetenciaTecnica.getItems()) {
            if (gp.equals(grauProficiencia)) {
                return false;
            }
        }
        return true;
    }

    //remover a última competência técnica da lista
    public void removerUltimaCompTecCategoriaTarefaAction(ActionEvent actionEvent) {
        listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().remove(listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().size() - 1);

        if (listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().size() == 0) {
            btnRemoverUltimaCompTecCategoriaTarefa.setDisable(true);
        }
    }


    //###########################################

    //adicionar a competência técnica selecionada com o grau de proficiencia e obrigatoriedade verdadeira no Freelancer
    public void competenciaReconhecidaFreelancerAction(ActionEvent actionEvent) {
        if (comboBoxGrauProficienciaFreelancer.getValue() != null &&
                listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem() != null) {
            ReconhecimentoCT ct = new ReconhecimentoCT(
                    listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem(),
                    comboBoxGrauProficienciaFreelancer.getValue(),
                    Data.dataAtual());

            if (competenciaTecnicaAindaNaoFoiAdicionadaFreelancer(listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem())) {
                listViewCompTecnicasSelecionadasFreelancer.getItems().add(ct);
                btnRemoverUltimaCompTecFreelancer.setDisable(false);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar nova competencia tecnica.",
                        "Não é possível adicionar a mesma competência técnica mais do que uma vez.").show();
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar nova competencia tecnica.",
                    "É obrigatório escolher um grau de proficiência").show();
        }

    }



    public boolean competenciaTecnicaAindaNaoFoiAdicionadaFreelancer(CompetenciaTecnica competenciaTecnica) {
        for(ReconhecimentoCT rct : listViewCompTecnicasSelecionadasFreelancer.getItems()) {
            if (rct.getCompetenciaTecnica().equals(competenciaTecnica)) {
                return false;
            }
        }
        return true;
    }

    //remover a última competência técnica da lista
    public void removerUltimaCompTecFreelancerAction(ActionEvent actionEvent) {
        listViewCompTecnicasSelecionadasFreelancer.getItems().remove(listViewCompTecnicasSelecionadasFreelancer.getItems().size() - 1);

        if (listViewCompTecnicasSelecionadasFreelancer.getItems().size() == 0) {
            btnRemoverUltimaCompTecFreelancer.setDisable(true);
        }
    }


    // ##########################################


    // ## METODOS PARA LIMPAR DADOS #############

    //limpar dados criar area de atividade
    public void limparAreaAtividadeAction(ActionEvent event) {
        limparDadosAreaAtividade();
    }
    public void limparDadosAreaAtividade() {
        txtCodUnicoAreaAtividade.clear();
        txtDescBreveAreaAtividade.clear();
        txtDescDetalhadaAreaAtividade.clear();
        txtCodUnicoAreaAtividade.requestFocus();
    }

    //limpar dados criar categoria de tarefa
    public void limparCategoriaTarefaAction(ActionEvent event) {
        btnRemoverUltimaCompTecCategoriaTarefa.setDisable(true);
        limparDadosCategoriaTarefa();
    }
    public void limparDadosCategoriaTarefa() {
        txtDescricaoCategoriaTarefa.clear();
        listViewCompTecnicasPorSelecionarCategoriaTarefa.getItems().setAll(new ArrayList<>());
        listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().setAll(new ArrayList<>());
    }

    //limpar dados criar freelancer
    public void limparFreelancerAction(ActionEvent event) {
        btnRemoverUltimaCompTecFreelancer.setDisable(true);
        limparDadosFreelancer();
    }
    public void limparDadosFreelancer() {

        txtNomeFreelancer.clear();
        txtTelefoneFreelancer.clear();;
        txtEmailFreelancer.clear();
        txtNIFFreelancer.clear();

        txtMediaCurso.clear();
        txtDesignacaoCurso.clear();
        txtGrauFreelancer.clear();
        txtNomeInstituicao.clear();

        listViewCompTecnicasSelecionadasFreelancer.getItems().setAll(new ArrayList<>());
        listViewHabilitacaoAcademicaRegistoFreelancer.getItems().setAll(new ArrayList<>());
    }

    //limpar dados criar competencia tecnica
    public void limparCompetenciaTecnicaAction(ActionEvent event) {
        limparDadosCompetenciaTecnica();

    }
    public void limparDadosCompetenciaTecnica() {
        txtCodigoUnicoCompetenciaTecnica.clear();
        comboBoxAreaAtividadeCompetenciaTecnica.getSelectionModel().clearSelection();
        txtDescBreveCompetenciaTecnica.clear();
        txtDescDetalhadaCompetenciaTecnica.clear();
        txtCodUnicoAreaAtividade.requestFocus();
    }

    //limpar todos os dados

    public void limparTodosOsDados(){
        limparDadosAreaAtividade();
        limparDadosCategoriaTarefa();
        limparDadosCompetenciaTecnica();
        limparDadosFreelancer();
    }

    // ##########################################



    public void logoutAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            try {
                limparTodosOsDados();
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

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }


    public void btnAdicionarGrauProficienciaCompetenciaTecnica(ActionEvent actionEvent) {
        if (txtDesignacaoGrauProficienciaCriarCompetenciaTecnica.getText() != null) {
            GrauProficiencia gp = new GrauProficiencia(
                    ++grauCounter,
                    txtDesignacaoGrauProficienciaCriarCompetenciaTecnica.getText().trim()
                    );

            if (grauProficienciaAindaNaoFoiAdicionado(gp)){
                listViewGrauProficienciaCompetenciaTecnica.getItems().add(gp);
                txtDesignacaoGrauProficienciaCriarCompetenciaTecnica.clear();
                btnRemoverGrauProficienciaCompetenciaTecnica.setDisable(false);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar novo grau.",
                        "Não é possível adicionar o mesmo grau de proficiência mais do que uma vez.").show();
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar novo grau.",
                    "É obrigatório designar um nível e uma designação para cada grau a adicionar.").show();
        }
    }

    public void btnRemoverGrauProficienciaCompetenciaTecnica(ActionEvent actionEvent) {
        listViewGrauProficienciaCompetenciaTecnica.getItems().remove(listViewGrauProficienciaCompetenciaTecnica.getItems().size()-1);
        grauCounter += -1;
        if (listViewGrauProficienciaCompetenciaTecnica.getItems().size() == 0) {
            btnRemoverGrauProficienciaCompetenciaTecnica.setDisable(true);
        }
    }

    public void popularComboBoxGrauProficienciaCategoriaTarefa(Event actionEvent) {
        if (listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem() != null) {
            comboBoxGrauProficienciaCategoriaTarefa.getItems().setAll(serviceController.getGrausProficiencia(listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem()));
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Graus de proficiência",
                    "Precisa selecionar uma competência técnica primeiro.").show();
        }
    }

    public void popularComboBoxGrauProficienciaFreelancer(Event actionEvent) {
        if (listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem() != null) {
            comboBoxGrauProficienciaFreelancer.getItems().setAll(serviceController.getGrausProficiencia(listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem()));
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Graus de proficiência",
                    "Precisa selecionar uma competência técnica primeiro.").show();
        }
    }


    public void adicionarhabilitacaoAcademicaFreelancerAction(ActionEvent actionEvent) {
        if (!txtNomeInstituicao.getText().isEmpty() &&
                !txtGrauFreelancer.getText().isEmpty() && !txtDesignacaoCurso.getText().isEmpty() && !txtMediaCurso.getText().isEmpty()) {

            try {
                HabilitacaoAcademica ha = new HabilitacaoAcademica(txtGrauFreelancer.getText(), txtDesignacaoCurso.getText(),
                        txtNomeInstituicao.getText(), Double.parseDouble(txtMediaCurso.getText())
                );

                if(!listViewHabilitacaoAcademicaRegistoFreelancer.getItems().contains(ha)) {
                    listViewHabilitacaoAcademicaRegistoFreelancer.getItems().add(ha);
                    btnRemoverUltimaHabilitacaoAcademicaAdicionada.setDisable(false);
                } else {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar habilitação académica.",
                            "Não é possível adicionar a mesma habilitação académica mais do que uma vez.").show();
                }

            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar habilitação.",
                        "A média do curso tem de ser um valor numérico").show();
            }



        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar habilitação.",
                    "É obrigatório preencher todos os campos").show();
        }

    }

    public void removerUltimaHabilitacaoAcademicaAdicionadaFreelancerAction(ActionEvent actionEvent) {
        listViewHabilitacaoAcademicaRegistoFreelancer.getItems().remove(listViewHabilitacaoAcademicaRegistoFreelancer.getSelectionModel().getSelectedItem());
        if (listViewHabilitacaoAcademicaRegistoFreelancer.getItems().size() == 0) {
            btnRemoverUltimaHabilitacaoAcademicaAdicionada.setDisable(true);
        }
    }


    public void comboBoxAreaAtividadeCriarCategoriaTarefaOnShowing(Event event) {


        //popular combo boxes do painel Criar Categoria de Tarefa
        try {
            comboBoxAreaAtividadeCategoriaTarefa.getItems().setAll(serviceController.getAreasAtividade());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void listViewCompTecRegistarFreelancerOnMouseClickedAction(MouseEvent mouseEvent) {
        if (listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem() != null) {
            comboBoxGrauProficienciaFreelancer.getItems().setAll(serviceController.getGrausProficiencia(listViewCompTecnicasPorSelecionarFreelancer.getSelectionModel().getSelectedItem()));
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Graus de proficiência",
                    "Precisa selecionar uma competência técnica primeiro.").show();
        }
    }
}
