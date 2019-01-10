package controller;

import ajudantes.NotificadorAlertas;
import app.Main;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormPrincipalController implements Initializable {

    @FXML
    private BorderPane painelPrincipal;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuArquivo;

    @FXML
    private MenuItem itemMenuSair;

    @FXML
    private Menu menuGerenciar;

    @FXML
    private Menu menuCadastrar;

    @FXML
    private MenuItem itemCadastrarUsuario;

    @FXML
    private MenuItem itemCadastrarCliente;

    @FXML
    private Menu menuAjuda;


    private AnchorPane anchorPane;

    Service service;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarElementos();
        configurarMenuBar();
    }


    private void configurarElementos() {

    }

    private void configurarMenuBar() {
        itemMenuSair.setOnAction(actionEvent -> {
            fechar();
        });

        itemCadastrarUsuario.setOnAction(actionEvent->{
            try {
                iniciarTela("/view/cadUsuario.fxml");
            } catch (Exception e) {
                NotificadorAlertas.mostrarMsgErro(e,"Erro ao tentar carregar tela de cadastro de usuário.");
            }
        });
        itemCadastrarCliente.setOnAction(actionEvent->{
            try {
                iniciarTela("/view/cadCliente.fxml");
            } catch (Exception e) {
                NotificadorAlertas.mostrarMsgErro(e,"Erro ao tentar carregar tela de cadastro de cliente.");
            }
        });

    }

    private void iniciarTela(String caminhoFXML) throws IOException {
        painelPrincipal.setCenter(null);
        anchorPane = new AnchorPane();
        anchorPane = (AnchorPane)FXMLLoader.load(getClass().getResource(caminhoFXML));
        painelPrincipal.setCenter(anchorPane);
    }

    private void fechar() {
        Main.getStage().close();
    }


}
