package ui;

import application.PlataformaController;
import domain.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistence.RepositorioOrganizacao;
import persistence.RepositorioUtilizador;

import java.io.IOException;

public class MainApp extends Application {

    public static final String TITULO_APLICACAO = "T4J - Tasks For Joe";

    public static ScreenController screenController; //private + get
    private PlataformaController plataformaController;

    @Override
    public void start(Stage stage) throws Exception {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaInicialScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            //scene.getStylesheets().add("/styles/Styles.css");
            scene.getStylesheets().addAll(this.getClass().getResource("/styles/Styles.css").toExternalForm());


            //adicionar as diferentes janelas
            screenController = new ScreenController(scene);

            screenController.addScreen("RegistarOrganizacao",
                    FXMLLoader.load(getClass().getResource("/fxml/RegistarOrganizacaoScene.fxml")));
            screenController.addScreen("Login",
                    FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml")));
            screenController.addScreen("JanelaInicial",
                    FXMLLoader.load(getClass().getResource("/fxml/JanelaInicialScene.fxml")));
            screenController.addScreen("AreaAdministrativo",
                    FXMLLoader.load(getClass().getResource("/fxml/AreaAdministrativoScene.fxml")));
            screenController.addScreen("AreaColaborador",
                    FXMLLoader.load(getClass().getResource("/fxml/AreaColaboradorOrganizacaoScene.fxml")));
            screenController.addScreen("AreaGestor",
                    FXMLLoader.load(getClass().getResource("/fxml/AreaGestorOrganizacaoScene.fxml")));


            stage.setTitle(TITULO_APLICACAO);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setResizable(false);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                            "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        if (!Plataforma.guardarDados()) {
                            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO, "Guardar Dados",
                                    "Problema ao guardar dados!").show();
                        }
                    }
                }
            });

            stage.show();

            try {
                Plataforma.carregarDados();

            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Desserialização",
                        "Não foi encontrado ficheiro com dados.").show();
            }

            plataformaController = new PlataformaController();

            plataformaController.resetUserAPI();

        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO,
                    "Problemas no arranque da aplicação.", ex.getMessage()).show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ScreenController getScreenController() {
        return screenController;
    }

    private static void createData() {
        Organizacao org = new Organizacao("org", new NIF(123123123), new Website("www.org.com"), new Telefone(999999999),
                new Email("org@org.com"), new EnderecoPostal("Rua da Povoa 23", "Porto", "4200-432"));
        Colaborador gestor = new Colaborador("gestorGrupo3", new Telefone(999999999), new Email("colab@org.com"), org, Funcao.GESTOR);

        Plataforma.getInstance().getRepoOrg().addOrganizacao(org);
        Plataforma.getInstance().getRepoOrg().addGestor(gestor, org);

        //Plataforma.getInstance().getRepoOrg().registarGestorComoUtilizador(gestor);
    }
}

