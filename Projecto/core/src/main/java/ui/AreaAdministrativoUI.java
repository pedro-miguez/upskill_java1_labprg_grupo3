package ui;

import application.DefinirAreaAtividadeController;
import application.DefinirCompetenciaTecnicaController;
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

    private DefinirCompetenciaTecnicaController competenciaTecnicaController;

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

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar nova área de atividade.",
                    adicionou ? "Área de atividade adicionada com sucesso."
                            : "Não foi possível adicionar a área de atividade.").show();

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Introduza os dados novamente!").show();
        }

    }

    @FXML
    void criarAreaAtividadeAction(ActionEvent event) {

        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        criarAreaAtividadePane.setVisible(true);
        criarAreaAtividadePane.setDisable(false);

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

        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);
        criarCompetenciaTecnicaPane.setVisible(false);
        criarCompetenciaTecnicaPane.setDisable(true);
        criarCategoriaTarefaPane.setVisible(true);
        criarCategoriaTarefaPane.setDisable(false);

    }

    //COMPETENCIA TECNICA
    @FXML
    void voltarCompetenciaTecnicaAction(ActionEvent event) {

        MainApp.screenController.activate("JanelaInicial");

    }

    @FXML
    void limparCompetenciaTecnicaAction(ActionEvent event) {

        txtCodigoUnicoCompetenciaTecnica.clear();
        comboBoxAreaAtividadeCompetenciaTecnica.getSelectionModel().clearSelection();
        /*comboBoxAreaAtividadeCompetenciaTecnica.setValue(null);*/
        txtDescBreveCompetenciaTecnica.clear();
        txtDescDetalhadaCompetenciaTecnica.clear();
        txtCodUnicoAreaAtividade.requestFocus();

    }

    @FXML
    void confirmarCompetenciaTecnicaAction(ActionEvent event) {

        try {

            boolean adicionou = competenciaTecnicaController.definirCompetenciaTecnica(txtCodigoUnicoCompetenciaTecnica.getText().trim(),
                    comboBoxAreaAtividadeCompetenciaTecnica.getValue().getCodigoUnico().toString(), txtDescBreveCompetenciaTecnica.getText().trim(),
                txtDescDetalhadaCompetenciaTecnica.getText().trim());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Adicionar nova competencia tecnica.",
                    adicionou ? "Competencia tecnica adicionada com sucesso."
                            : "Não foi possível adicionar a competencia tecnica.").show();

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Introduza os dados novamente!").show();
        }

    }

    @FXML
    void criarCompetenciaTecnicaAction(ActionEvent event) {

        criarAreaAtividadePane.setVisible(false);
        criarAreaAtividadePane.setDisable(true);
        criarCategoriaTarefaPane.setVisible(false);
        criarCategoriaTarefaPane.setDisable(true);
        criarCompetenciaTecnicaPane.setVisible(true);
        criarCompetenciaTecnicaPane.setDisable(false);


    }

}
