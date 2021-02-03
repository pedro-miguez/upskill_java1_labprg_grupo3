package ui;

import application.AuthenticationController;
import application.DefinirTarefaController;
import application.PlataformaController;
import application.RegistarColaboradorController;
import domain.CategoriaTarefa;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AreaGestorUI implements Initializable {

    //Registar Colaborador elements
    public Button btnRegistarColaborador;
    public Button btnLimparRegistarColaborador;

    public TextField txtNomeColaborador;
    public TextField txtContactoColaborador;
    public TextField txtEmailColaborador;


    //Criar Tarefa Elements
    public Button btnRegistarTarefa;
    public Button btnLimparTarefa;

    public TextField txtCodigoUnicoTarefa;
    public TextField txtCustoTarefa;
    public TextArea txtDescInfTarefa;
    public TextArea txtDescTecnicaTarefa;
    public TextField txtDuracaoTarefa;
    public ComboBox<CategoriaTarefa> comboCategoria;
    public TextField txtNomeTarefa;


    //General Elements
    public Button btnSelecionarRegistarColaborador;
    public Button btnSelecionarCriarTarefa;
    public Button btnLogout;

    public BorderPane criarTarefaPane;
    public BorderPane registarColaboradorPane;

    private RegistarColaboradorController registarColaboradorController;
    private AuthenticationController authController;
    private PlataformaController plataformaController;
    private DefinirTarefaController tarefaController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registarColaboradorController = new RegistarColaboradorController();
        authController = new AuthenticationController();
        plataformaController = new PlataformaController();
        comboCategoria.getItems().setAll(plataformaController.getCategoriasTarefa());
        tarefaController = new DefinirTarefaController();
    }

    //Registar Colaborador
    @FXML
    void registarColaboradorAction(ActionEvent event) {
        try {
            boolean registou = registarColaboradorController.Colaborador(txtNomeColaborador.getText().trim(),
                    txtContactoColaborador.getText().trim(),
                    txtEmailColaborador.getText().trim(),
                    authController.getEmail()
                    );
            
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar novo colaborador.",
                    registou ? "Colaborador registado com sucesso." : "Não foi possível registar o colaborador.").show();
            
        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }

    //Criar Tarefa
    public void criarTarefaActionTarefa(ActionEvent actionEvent) {
        try {
            boolean criou = tarefaController.definirTarefa(
                    txtCodigoUnicoTarefa.getText().trim(),
                    txtNomeTarefa.getText().trim(),
                    txtDescInfTarefa.getText().trim(),
                    txtDescTecnicaTarefa.getText().trim(),
                    Integer.parseInt(txtDuracaoTarefa.getText().trim()),
                    Float.parseFloat(txtCustoTarefa.getText().trim()),
                    comboCategoria.getValue(),
                    authController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION,
                    MainApp.TITULO_APLICACAO, "Criar nova tarefa.",
                    criou ? "Tarefa criada com sucesso."
                            : "Não foi possível criar a tarefa.").show();

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        }
    }

    //limpar campos do registo colaborador
    public void limparRegistarColaboradorAction(ActionEvent actionEvent) {
        txtNomeColaborador.clear();
        txtContactoColaborador.clear();
        txtEmailColaborador.clear();
    }

    //Limpar campos do registo tarefa
    public void limparActionTarefa(ActionEvent actionEvent) {
        txtCodigoUnicoTarefa.clear();
        txtCustoTarefa.clear();
        txtDescInfTarefa.clear();
        txtDescTecnicaTarefa.clear();
        txtDuracaoTarefa.clear();
        txtNomeTarefa.clear();
    }

    //selecionar menu registo colaborador
    public void btnSelecionarRegistarColaboradorAction(ActionEvent actionEvent) {
        registarColaboradorPane.setVisible(true);
        registarColaboradorPane.setDisable(false);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
    }

    //selecionar menu criação de tarefa
    public void btnSelecionarCriarTarefaAction(ActionEvent actionEvent) {
        registarColaboradorPane.setVisible(false);
        registarColaboradorPane.setDisable(true);
        criarTarefaPane.setVisible(true);
        criarTarefaPane.setDisable(false);
    }

    //fazer logout
    public void btnLogoutAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Irá voltar à pagina inicial após confirmação.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            limparTodosOsCampos();
            authController.logout();
            voltarJanelaInicial();
        }
    }

    //limpar todos os campos
    public void limparTodosOsCampos() {
        txtNomeColaborador.clear();
        txtContactoColaborador.clear();
        txtEmailColaborador.clear();

        txtCodigoUnicoTarefa.clear();
        txtCustoTarefa.clear();
        txtDescInfTarefa.clear();
        txtDescTecnicaTarefa.clear();
        txtDuracaoTarefa.clear();
        txtNomeTarefa.clear();
    }

    //volta à janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }



}
