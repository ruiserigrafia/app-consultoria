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

    public static void mostrarMsgSucesso(String titulo, String msg) {
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem de Sucesso");
        if(titulo.isEmpty()) {
            alerta.setHeaderText("Processo executado com sucesso!");
        } else {
            alerta.setHeaderText(titulo);
        }
        alerta.setContentText(msg);
        alerta.showAndWait();
    }


}
