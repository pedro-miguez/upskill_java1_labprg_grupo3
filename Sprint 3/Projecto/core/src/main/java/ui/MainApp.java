package ui;

import application.ServiceController;
import domain.Plataforma;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import utils.SendMail;

import java.io.IOException;
import java.sql.SQLException;

public class MainApp extends Application {

    public static final String TITULO_APLICACAO = "T4J - Tasks For Joe";

    public static ScreenController screenController; //private + get
    private ServiceController serviceController;

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
            screenController.addScreen("AreaFreelancer",
                    FXMLLoader.load(getClass().getResource("/fxml/AreaFreelancerScene.fxml")));

            Plataforma plataforma = Plataforma.getInstance();

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
                        try {
                            Plataforma.getInstance().getConnectionHandler().closeConnection();
                        } catch (SQLException throwables) {
                            AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                                    "Confirmação da ação.", throwables.getMessage());
                            throwables.printStackTrace();
                        }
                    }
                }
            });

            stage.show();


            serviceController = new ServiceController();

            serviceController.resetUserAPI();

        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO,
                    "Problemas no arranque da aplicação.", ex.getMessage()).show();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);

        //SendMail.sendMail("victor88_6@msn.com");
    }

    public ScreenController getScreenController() {
        return screenController;
    }

}

