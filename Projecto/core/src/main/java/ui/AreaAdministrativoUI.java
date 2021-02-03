package ui;

import application.DefinirAreaAtividadeController;
import application.DefinirCompetenciaTecnicaController;
import domain.AreaAtividade;
import domain.CompetenciaTecnica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AreaAdministrativoUI {

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
    public ComboBox<?> comboBoxGrauProficienciaCategoriaTarefa;
    public ListView<CompetenciaTecnica> listViewCompTecnicasPorSelecionarCategoriaTarefa;
    public ListView<CompetenciaTecnica> listViewCompTecnicasSelecionadasCategoriaTarefa;

    public Button btnConfirmarAreaAtividadeCategoriaTarefa;

    public Button btnCompOpcionalCategoriaTarefa;
    public Button btnCompObrigatoriaCategoriaTarefa;

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

    public void ConfirmarCategoriaTarefaAction(ActionEvent actionEvent) {
    }

    public void criarAreaAtividadeSelectAction(ActionEvent actionEvent) {
    }

    public void criarCategoriaTarefaSelectAction(ActionEvent actionEvent) {
    }

    public void criarCompetenciaTecnicaSelectAction(ActionEvent actionEvent) {
    }

    public void logoutAction(ActionEvent actionEvent) {
    }

    public void ConfirmarAreaAtividadeCategoriaTarefaAction(ActionEvent actionEvent) {
    }
}
