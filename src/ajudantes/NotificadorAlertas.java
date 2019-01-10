package ajudantes;


import javafx.scene.control.Alert;

import java.io.IOException;

public class NotificadorAlertas {

    private static Alert alerta;

    public static void mostrarMsgErro(Exception e, String msg) {
        alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Alerta de Erro");
        alerta.setHeaderText(e.getMessage());
        alerta.setContentText(msg);
        alerta.showAndWait();
    }



}
