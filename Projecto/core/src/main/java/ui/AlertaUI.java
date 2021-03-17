package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * Class responsible for showing alert messages.
 * 
 * @author Grupo 3
 */
public class AlertaUI {

    public static Alert criarAlerta(Alert.AlertType tipoAlerta, String titulo, 
                                    String cabecalho, String mensagem) {
        
        Alert alerta = new Alert(tipoAlerta);

        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);

        if (tipoAlerta == Alert.AlertType.CONFIRMATION) {
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Sim");
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Não");
        }

        return alerta;
    }
}
