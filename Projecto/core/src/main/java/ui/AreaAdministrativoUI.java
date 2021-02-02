package ui;

import application.DefinirAreaAtividadeController;
import domain.AreaAtividade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AreaAdministrativoUI {

    @FXML
    private Button confirmarCompetenciaTecnicaBtn;

    @FXML
    private TextField txtDescBreveAreaAtividade;

    @FXML
    private Button limparCategoriaTarefaBtn;

    @FXML
    private BorderPane criarAreaAtividadePane;

    @FXML
    private ComboBox<?> comboBoxAreaAtividadeCategoriaTarefa;

    @FXML
    private Button confirmarCategoriaTarefaBtn;

    @FXML
    private TextArea txtDescDetalhadaCompetenciaTecnica;

    @FXML
    private ListView<?> listViewCompTecnicasSelecionadasCategoriaTarefa;

    @FXML
    private ListView<?> listViewCompTecnicasPorSelecionarCategoriaTarefa;

    @FXML
    private Button criarCompetenciaTecnicaBtn;

    @FXML
    private Button confirmarAreaAtividadeBtn;

    @FXML
    private Button voltarCompetenciaTecnicaBtn;

    @FXML
    private BorderPane criarCategoriaTarefaPane;

    @FXML
    private TextField txtDescricaoCategoriaTarefa;

    @FXML
    private TextField txtDescBreveCompetenciaTecnica;

    @FXML
    private Button criarCategoriaTarefaBtn;

    @FXML
    private Button limparAreaAtividadeBtn;

    @FXML
    private ComboBox<AreaAtividade> comboBoxAreaAtividadeCompetenciaTecnica;

    @FXML
    private TextField txtCodigoUnicoCompetenciaTecnica;

    @FXML
    private Button limparCompetenciaTecnicaBtn;

    @FXML
    private TextArea txtDescDetalhadaAreaAtividade;

    @FXML
    private TextField txtCodUnicoAreaAtividade;

    @FXML
    private BorderPane criarCompetenciaTecnicaPane;

    @FXML
    private Button voltarCategoriaTarefaBtn;

    @FXML
    private Button btnCompObrigatoriaCategoriaTarefa;

    @FXML
    private ComboBox<?> comboBoxGrauProficienciaCategoriaTarefa;

    @FXML
    private Button voltarAreaAtividadeBtn;

    @FXML
    private Button criarAreaAtividadeBtn;

    private DefinirAreaAtividadeController areaAtividadeController;

    //AREA ATIVIDADE
    @FXML
    void voltarAreaAtividadeAction(ActionEvent event) {

        MainApp.screenController.activate("JanelaInicial");

    }

    @FXML
    void limparAreaAtividadeAction(ActionEvent event) {

        txtCodUnicoAreaAtividade.clear();
        txtDescBreveAreaAtividade.clear();
        txtDescDetalhadaAreaAtividade.clear();
        txtCodUnicoAreaAtividade.requestFocus();

    }

    @FXML
    void confirmarAreaAtividadeAction(ActionEvent event) {

        try {

            boolean adicionou = areaAtividadeController.definirAreaAtividade(txtCodUnicoAreaAtividade.getText().trim(),
                    txtDescBreveAreaAtividade.getText().trim(), txtDescDetalhadaAreaAtividade.getText().trim());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar novo contacto.",
                    adicionou ? "Contacto adicionado com sucesso."
                            : "Não foi possível adicionar o contacto.").show();

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Introduza os dados novamente!").show();
        }

    }

    @FXML
    void criarAreaAtividadeAction(ActionEvent event) {

        criarAreaAtividadePane.isVisible();
        criarAreaAtividadePane.isDisable();

    }

    //CATEGORIA TAREFA
    @FXML
    void voltarCategoriaTarefaAction(ActionEvent event) {

        MainApp.screenController.activate("JanelaInicial");

    }

    @FXML
    void limparCategoriaTarefaAction(ActionEvent event) {

    }

    @FXML
    void confirmarCategoriaTarefaBtn(ActionEvent event) {

    }

    @FXML
    void criarCategoriaTarefaAction(ActionEvent event) {

        criarCategoriaTarefaPane.isVisible();

    }

    //COMPETENCIA TECNICA
    @FXML
    void voltarCompetenciaTecnicaAction(ActionEvent event) {

        MainApp.screenController.activate("JanelaInicial");

    }

    @FXML
    void limparCompetenciaTecnicaAction(ActionEvent event) {

    }

    @FXML
    void confirmarCompetenciaTecnicaAction(ActionEvent event) {

    }

    @FXML
    void criarCompetenciaTecnicaAction(ActionEvent event) {

        criarCompetenciaTecnicaPane.isVisible();

    }

}
