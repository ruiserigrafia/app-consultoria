package controller;

import ajudantes.NotificadorAlertas;
import ajudantes.MascaraCampos;
import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Usuario;


import java.net.URL;
import java.util.ResourceBundle;

public class FormLoginController implements Initializable {

    /**
     * Injeção de elementos FXML com classes correspondente
     */

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Text textMsgLogin;

    @FXML
    private Text textRodape;

    @FXML
    private PasswordField fieldSenha;

    @FXML
    private Button buttonEntrar;

    @FXML
    private Pane paneRodape;

    @FXML
    private Text textCabecalho;

    @FXML
    private Pane paneCentral;

    @FXML
    private Pane paneCabecalho;

    @FXML
    private Text textMsgSenha;

    @FXML
    private Button buttonSair;

    @FXML
    private TextField fieldlogin;

    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            iniciarComponents();
            configurarComponents();
            configurarEventos();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            NotificadorAlertas.mostrarMsgErro(e,"Erro na inicialização, tente novamnete.\r\n" +
                    "Se o erro persistir contate o desenvolvedor do sistema.");
        }
    }

    private void iniciarComponents() throws Exception {
        usuario = new Usuario();
    }

    private void configurarComponents() {
        textCabecalho.setText("Sistema de Avaliação Física e Nutricional");

        imageLogo.setImage(new Image("/img/logo_login.png"));

        Tooltip tipNome = new Tooltip();
        tipNome.setText("Login usuario");
        fieldlogin.setTooltip(tipNome);
        fieldlogin.setPromptText("Insira seu login");
        MascaraCampos.loginField(fieldlogin);
        textMsgLogin.setVisible(false);

        Tooltip tipSenha = new Tooltip();
        tipSenha.setText("Senha usuario");
        fieldlogin.setTooltip(tipSenha);
        fieldSenha.setPromptText("Insira sua senha");
        fieldSenha.setEditable(false);
        textMsgSenha.setVisible(false);

        buttonEntrar.setText("Entrar");

        buttonSair.setText("Sair");

        textRodape.setText("Developer: ruiserigrafia@gmail.com");
    }

    private void configurarEventos() {
        fieldlogin.focusedProperty().addListener(l->{
            textMsgSenha.setVisible(false);
            fieldSenha.clear();
            if(fieldlogin.getText().isEmpty()) {
                textMsgLogin.setText("Digite seu login de usuário.");
                textMsgLogin.setVisible(true);
            } else {
                fieldSenha.setEditable(true);
            }
        });

        fieldSenha.focusedProperty().addListener(l->{

            if(fieldlogin.getText().isEmpty()) {
                textMsgLogin.setText("Login não pode ser nulo!");
                fieldSenha.setEditable(false);
                fieldlogin.setFocusTraversable(true);
            } else {
                textMsgLogin.setVisible(false);
                fieldlogin.setEditable(true);
            }

            if(fieldSenha.getText().isEmpty()) {
                textMsgSenha.setText("Digite sua senha de usuário.");
                if(!fieldlogin.getText().isEmpty()) {
                    textMsgSenha.setVisible(true);
                }
            }
        });

        buttonEntrar.focusedProperty().addListener(l->{
            textMsgLogin.setVisible(false);
            textMsgSenha.setVisible(false);
            if(fieldlogin.getText().isEmpty() && fieldSenha.getText().isEmpty()) {
                textRodape.setText("Os campos de login e senha não podem ser nulos!");
                textRodape.setStyle("-fx-fill: yellow");
            } else if (fieldlogin.getText().isEmpty()) {
                textRodape.setText("O campo de login não pode ser nulo!");
                textRodape.setStyle("-fx-fill: yellow");
            } else if (fieldSenha.getText().isEmpty()) {
                textRodape.setText("O campo de senha não pode ser nulo!");
                textRodape.setStyle("-fx-fill: yellow");
            }
        });

        buttonEntrar.setOnAction(event->{
            logar();
        });

        buttonSair.setOnAction(event->{
            sair();
        });

        // Eventos de teclado
        buttonEntrar.setOnKeyPressed(key->{
            if(key.getCode() == KeyCode.ENTER) {
                logar();
            }

        });

        buttonSair.setOnKeyPressed(key->{
            if(key.getCode() == KeyCode.ENTER) {
                sair();
            }
        });
    }

    public void sair(){
        Main.getStage().close();
    }

    public void logar() {
        String msgError = null;
        try {

            if(fieldlogin.getText().isEmpty()) {
                msgError = "Digite seu login. Se o erro persistir informe o administrador do sistema.";
                throw new Exception("O login de usuário não foi informado");
            } else if (fieldSenha.getText().isEmpty()) {
                msgError = "Digite sua senha. Se o erro persistir informe o administrador do sistema.";
                throw new Exception("A senha do usuário não foi informada");
            }

            usuario.setLogin(fieldlogin.getText());
            usuario.setSenha(fieldSenha.getText());


            if(usuario.validarLogin()) {
                if(usuario.validarSenha()) {
                    Main.getStage().getScene().getWindow().hide();
                    BorderPane root = new BorderPane();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/formPrincipal.fxml"));
                    root = (BorderPane)loader.load();
                    loader.setController(new FormPrincipalController());
                    Main.getStage().getScene().setRoot(root);
                    Main.getStage().showAndWait();
                }else {
                    msgError = "A SENHA informada não é válida. Informe sua senha e tente novamente!";
                    throw new Exception("Senha inválida!");

                }
            } else {
                msgError = "O LOGIN informada não é válida. Informe sua senha e tente novamente!";
                throw new Exception("Login inválido!");
            }

        } catch (Exception e) {
            Main.getStage().setOpacity(0);
            NotificadorAlertas.mostrarMsgErro(e,msgError);
            Main.getStage().setOpacity(1);
        }

    }

}
