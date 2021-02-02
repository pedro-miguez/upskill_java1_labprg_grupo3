package ui;

import domain.Plataforma;
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

import java.io.IOException;

public class MainApp extends Application {

    public static final String TITULO_APLICACAO = "T4J - Tasks For Joe";

    public static ScreenController screenController;

    @Override
    public void start(Stage stage) throws Exception {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaInicialScene.fxml"));
            Parent root = loader.load();

            //criar plataforma
            Plataforma.carregarDados();
            Plataforma plataforma = Plataforma.getInstance();


            Scene scene = new Scene(root);
            //scene.getStylesheets().add("/styles/Styles.css");



            //adicionar as diferentes janelas
            screenController = new ScreenController(scene);
            screenController.addScreen("RegistarOrganizacao",
                    FXMLLoader.load(getClass().getResource("/fxml/RegistarOrganizacaoScene.fxml")));
            screenController.addScreen("Login",
                    FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml")));
            screenController.addScreen("JanelaInicial",
                    FXMLLoader.load(getClass().getResource("/fxml/JanelaInicialScene.fxml")));
            //screenController.addScreen("AreaAdministrativo",
              //      FXMLLoader.load(getClass().getResource("/fxml/AreaAdministrativoScene.fxml")));


            stage.setTitle(TITULO_APLICACAO);
            stage.setScene(scene);

            stage.sizeToScene();

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
}

