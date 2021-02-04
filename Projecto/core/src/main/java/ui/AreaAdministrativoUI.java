package ui;

import application.*;
import domain.AreaAtividade;
import domain.CaracterizacaoCompTec;
import domain.CompetenciaTecnica;
import domain.GrauProficiencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
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


    //Elementos gerais
    public Button btnCriarAreaAtividadeSelect;
    public Button btnCriarCategoriaTarefaSelect;
    public Button btnCriarCompetenciaTecnicaSelect;

    public Button btnLogout;

    

    private DefinirAreaAtividadeController areaAtividadeController;
    private DefinirCompetenciaTecnicaController competenciaTecnicaController;
    private DefinirCategoriaTarefaController categoriaTarefaController;
    private PlataformaController plataformaController;
    private AuthenticationController authenticationController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //inicializar controllers
        areaAtividadeController = new DefinirAreaAtividadeController();
        competenciaTecnicaController = new DefinirCompetenciaTecnicaController();
        categoriaTarefaController = new DefinirCategoriaTarefaController();
        plataformaController = new PlataformaController();
        authenticationController = new AuthenticationController();

        //popular combo box do painel Criar Competencia Tecnica
        comboBoxAreaAtividadeCompetenciaTecnica.getItems().setAll(plataformaController.getAreasAtividade());

        //popular combo boxes do painel Criar Categoria de Tarefa
        comboBoxAreaAtividadeCategoriaTarefa.getItems().setAll(plataformaController.getAreasAtividade());
        comboBoxGrauProficienciaCategoriaTarefa.getItems().setAll(plataformaController.getGrausProficiencia());

    }


    // ## METODOS PARA CRIAÇAO DE OBJECTOS ######

    //criar área de atividade
    public void confirmarAreaAtividadeAction(ActionEvent event) {
        try {
            boolean adicionou = areaAtividadeController.definirAreaAtividade(txtCodUnicoAreaAtividade.getText().trim(),
                    txtDescBreveAreaAtividade.getText().trim(), txtDescDetalhadaAreaAtividade.getText().trim());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar nova área de atividade.",
                    adicionou ? "Área de atividade adicionada com sucesso."
                            : "Não foi possível adicionar a área de atividade.").show();

            if (adicionou) {
                limparTodosOsDados();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }

    }

    //criar categoria de tarefa
    public void confirmarCategoriaTarefaAction(ActionEvent actionEvent) {
        try {
            boolean adicionou = categoriaTarefaController.definirCategoriaTarefa(comboBoxAreaAtividadeCategoriaTarefa.getValue(),
                    txtDescricaoCategoriaTarefa.getText().trim(), listViewCompTecnicasSelecionadasCategoriaTarefa.getItems());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar nova categoria de tarefa.",
                    adicionou ? "Categoria de Tarefa adicionada com sucesso."
                            : "Não foi possível adicionar a categoria de tarefa.").show();

            if (adicionou) {
                limparTodosOsDados();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }

    //criar competencia tecnica
    public void confirmarCompetenciaTecnicaAction(ActionEvent event) {
        try {
            boolean adicionou = competenciaTecnicaController.definirCompetenciaTecnica(txtCodigoUnicoCompetenciaTecnica.getText().trim(),
                    comboBoxAreaAtividadeCompetenciaTecnica.getValue(), txtDescBreveCompetenciaTecnica.getText().trim(),
                    txtDescDetalhadaCompetenciaTecnica.getText().trim());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar nova competencia tecnica.",
                    adicionou ? "Competencia tecnica adicionada com sucesso."
                            : "Não foi possível adicionar a competencia tecnica.").show();

            if (adicionou) {
                limparTodosOsDados();
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
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

        //ligar
        criarAreaAtividadePane.setVisible(true);
        criarAreaAtividadePane.setDisable(false);
        txtCodUnicoAreaAtividade.requestFocus();

        //atualizar

    }

    //mudar para o painel Criar Categoria de Tarefa
    public void criarCategoriaTarefaSelectAction(ActionEvent actionEvent) {
        //desligar
        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);

        //ligar
        criarCategoriaTarefaPane.setVisible(true);
        criarCategoriaTarefaPane.setDisable(false);
        txtDescricaoCategoriaTarefa.requestFocus();

        //popular elementos
        comboBoxAreaAtividadeCategoriaTarefa.getItems().setAll(plataformaController.getAreasAtividade());
        comboBoxGrauProficienciaCategoriaTarefa.getItems().setAll(plataformaController.getGrausProficiencia());
    }

    //mudar para o painel Criar Competencia Tecnica
    public void criarCompetenciaTecnicaSelectAction(ActionEvent actionEvent) {
        //desligar
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);

        //ligar
        criarCompetenciaTecnicaPane.setVisible(true);
        criarCompetenciaTecnicaPane.setDisable(false);
        txtCodigoUnicoCompetenciaTecnica.requestFocus();

        //popular elementos
        comboBoxAreaAtividadeCompetenciaTecnica.getItems().setAll(plataformaController.getAreasAtividade());
    }

    // ##########################################




    // ## METODOS UTILITARIOS ###################

    //confirmar área de atividade selecionada para popular o ListView de competencias tecnicas
    public void comboBoxAreaAtividadeCategoriaTarefaSelectAction(ActionEvent actionEvent) {
        listViewCompTecnicasPorSelecionarCategoriaTarefa.getItems().setAll(plataformaController.getCompetenciasTecnicasByAreaAtividade(comboBoxAreaAtividadeCategoriaTarefa.getValue()));
    }

    //adicionar a competência técnica selecionada com o grau de proficiencia e obrigatoriedade verdadeira
    public void compObrigatoriaCategoriaTarefaAction(ActionEvent actionEvent) {
        if (comboBoxGrauProficienciaCategoriaTarefa.getValue() != null) {
            CaracterizacaoCompTec ct = new CaracterizacaoCompTec(
                    listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem(),
                    true,
                    comboBoxGrauProficienciaCategoriaTarefa.getValue());

            listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().add(ct);
            btnRemoverUltimaCompTecCategoriaTarefa.setDisable(false);
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Adicionar nova competencia tecnica.",
                    "É obrigatório escolher um grau de proficiência").show();
        }

    }

    //adicionar a competência técnica selecionada com o grau de proficiencia e obrigatoriedade falso
    public void compOpcionalCategoriaTarefaAction(ActionEvent actionEvent) {
        if (comboBoxGrauProficienciaCategoriaTarefa.getValue() != null) {
            CaracterizacaoCompTec ct = new CaracterizacaoCompTec(
                    listViewCompTecnicasPorSelecionarCategoriaTarefa.getSelectionModel().getSelectedItem(),
                    false,
                    comboBoxGrauProficienciaCategoriaTarefa.getValue());

            listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().add(ct);
            btnRemoverUltimaCompTecCategoriaTarefa.setDisable(false);
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Adicionar nova competencia tecnica.",
                    "É obrigatório escolher um grau de proficiência").show();
        }

    }

    //remover a última competência técnica da lista
    public void removerUltimaCompTecCategoriaTarefaAction(ActionEvent actionEvent) {
        listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().remove(listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().size() - 1);

        if (listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().size() == 0) {
            btnRemoverUltimaCompTecCategoriaTarefa.setDisable(true);
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
        limparDadosCategoriaTarefa();
    }
    public void limparDadosCategoriaTarefa() {
        txtDescricaoCategoriaTarefa.clear();
        listViewCompTecnicasPorSelecionarCategoriaTarefa.getItems().setAll(new ArrayList<>());
        listViewCompTecnicasSelecionadasCategoriaTarefa.getItems().setAll(new ArrayList<>());
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
    }

    // ##########################################



    public void logoutAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            limparTodosOsDados();
            authenticationController.logout();
            plataformaController.resetUserAPI();
            voltarJanelaInicial();
        }
    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }

     {
    }
}
