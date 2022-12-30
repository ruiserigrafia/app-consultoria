package app;

import ajudantes.NotificadorAlertas;
import controller.FormLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Main instancia = new Main();
    private static Stage palco;
    private AnchorPane root;
    private Scene cena;
    private FXMLLoader loader;
    private FormLoginController controller;

    @Override
    public void start(Stage primaryStage) {
        try {
            initComponents();
            Main.palco = primaryStage;
            initStage();
        } catch(Exception e) {
            NotificadorAlertas.mostrarMsgErro(e,"Não foi possível inicializar o sistema. Por favor tente novamente.\r\n" +
                    "Se o erro persistir contate o desenvolvedor do sistema");
        }
    }

    public static Stage getStage() {
        return palco;
    }

    protected void initComponents() throws IOException {
        loader = new FXMLLoader();
        root = new AnchorPane();

        loader.setLocation(getClass().getResource("/view/formLogin.fxml"));
        root = (AnchorPane)loader.load();
        this.loader.setController(controller);
        cena = new Scene(root);
        cena.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    }

    protected void initStage() {

        palco = new Stage();
        palco.setScene(cena);
        palco.centerOnScreen();
        palco.setResizable(false);
        Image icon = new Image("/img/logo_login.png");
        palco.getIcons().add(icon);
        palco.setTitle("Sistema de Avaliação Física e Nutricional");
        palco.show();
    }

    public static Main getInstancia() {
        if (instancia == null)
            instancia = new Main();
        return instancia;
    }

    public static void main(String[] args) {
        getInstancia();
        launch(args);

    }

}
