package controller;

import ajudantes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.*;

import java.net.URL;
import java.util.*;

public class CadClienteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneDivUm;

    @FXML
    private Label labelNome;

    @FXML
    private TextField textFieldNome;

    @FXML
    private RadioButton radioMasculno;

    @FXML
    private RadioButton radioFeminino;

    @FXML
    private Label labelNascimento;

    @FXML
    private DatePicker pickerNascimento;

    @FXML
    private Label labelEstadoCivil;

    @FXML
    private ComboBox<String> comboEstadoCivil;

    @FXML
    private Label labelFixo;

    @FXML
    private TextField textFieldFixo;

    @FXML
    private Label labelCelular;

    @FXML
    private TextField textFieldCelular;

    @FXML
    private RadioButton radioZap;

    @FXML
    private Pane paneDivDois;

    @FXML
    private Label labelEndereco;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private Label labelPais;

    @FXML
    private ComboBox<String> comboPais;

    @FXML
    private Label labelEstado;

    @FXML
    private ComboBox<String> comboEstado;

    @FXML
    private Label labelCidade;

    @FXML
    private ComboBox<String> comboCidade;

    @FXML
    private Pane paneDivTres;

    @FXML
    private Label labelNaconalidade;

    @FXML
    private ComboBox<String> comboNacionalidade;

    @FXML
    private Label labelNaturakidade;

    @FXML
    private ComboBox<String> comboNaturalidade;

    @FXML
    private Label labelEtnia;

    @FXML
    private ComboBox<String> comboEtnia;

    @FXML
    private Label labelReligiao;

    @FXML
    private ComboBox<String> comboReligao;

    @FXML
    private Label labelEmail;

    @FXML
    private Text textArroba;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private ComboBox<String> comboServidor;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Text textStatus;

    @FXML
    ProgressBar progressBar;

    private ToggleGroup grupoSexo;
    private Cliente cliente;
    private Pais pais;
    private Estado estado;
    private Cidade cidade;
    private Etnia etnia;
    private Naturalidade naturalidade;
    private Nacionalidade nacionalidade;
    private Religiao religiao;
    private Servidor servidor;

    private Service service;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            cliente = new Cliente();
            pais = new Pais();
            estado = new Estado();
            cidade = new Cidade();
            etnia = new Etnia();
            naturalidade = new Naturalidade();
            nacionalidade = new Nacionalidade();
            religiao = new Religiao();
            servidor = new Servidor();
            textStatus.setVisible(false);
            carregarDados();
            mascarearCampos();
            inicializarElementos();
            carregarEventos();
        } catch (Exception e) {
            NotificadorAlertas.mostrarMsgErro(e,"Erro ao tentar carregar componentes da tela de cadastro de cliente.");
        }
    }

    private void carregarDados() {
        carregarDadosBD();
        carregarDadosLocalmente();
    }

    private void carregarDadosLocalmente() {
        ObservableList<String> listaEstadoCivil =
                FXCollections.
                        observableArrayList(
                                "Solteiro(a)",
                                "Casado(a)",
                                "Divorciado(a)",
                                "Viuvo(a)"
                        );
        comboEstadoCivil.setItems(listaEstadoCivil);
    }

    private void carregarDadosBD() {
        service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        anchorPane.setDisable(true);
                        textStatus.setVisible(true);
                        progressBar.setVisible(true);

                        updateMessage("Consultando e carregando do banco de dados...");
                        Thread.sleep(1000);

                        updateProgress(0,0);
                        int total = pais.obterTotalPaises();
                        updateMessage("Pesquisando registros de países...");
                        Thread.sleep(1000);
                        for (Pais p : pais.pesquisarPaises()) {
                            pais.getPaises().add(p);
                            updateProgress(p.getId(), total);
                            updateMessage("Carregando registros: " + p.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = estado.obterTotalEstados();
                        updateMessage("Pesquisando registros de estados...");
                        Thread.sleep(1000);
                        for (Estado e : estado.pesquisarEstados()) {
                            estado.getEstados().add(e);
                            updateProgress(e.getId(), total);
                            updateMessage("Carregando registros: " + e.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = cidade.obterTotalCidades();
                        updateMessage("Pesquisando registros de cidades...");
                        Thread.sleep(1000);
                        for (Cidade c : cidade.pesquisarCidades()) {
                            cidade.getCidades().add(c);
                            updateProgress(c.getId(), total);
                            updateMessage("Carregando registros: " + c.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = nacionalidade.obterTotalNacionalidades();
                        updateMessage("Pesquisando registros de nacionalidades...");
                        Thread.sleep(1000);

                        for (Nacionalidade nc : nacionalidade.pesquisarNacionalidades()) {
                            nacionalidade.getNacionalidades().add(nc);
                            updateProgress(nc.getId(), total);
                            updateMessage("Carregando registros: " + nc.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = naturalidade.obterTotalNaturalidades();
                        updateMessage("Pesquisando registros de naturalidades...");
                        Thread.sleep(1000);
                        for (Naturalidade nt : naturalidade.pesquisarNaturalidades()) {
                            naturalidade.getNaturalidades().add(nt);
                            updateProgress(nt.getId(), total);
                            updateMessage("Carregando registros: " + nt.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = etnia.mostrarTotal();
                        updateMessage("Pesquisando registros de etnias...");
                        Thread.sleep(1000);
                        for (Etnia e : etnia.pesquisarEtnias()) {
                            etnia.getEtnias().add(e);
                            updateProgress(e.getId(), total);
                            updateMessage("Carregando registros: " + e.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = religiao.mostrarTotal();
                        updateMessage("Pesquisando registros de religião...");
                        Thread.sleep(1000);
                        for (Religiao r : religiao.pesquisarPorReligioes()) {
                            religiao.getReligioes().add(r);
                            updateProgress(r.getId(), total);
                            updateMessage("Carregando registros: " + r.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = servidor.mostrarTotal();
                        updateMessage("Pesquisando registros de servidores...");
                        Thread.sleep(1000);
                        for (Servidor srv : servidor.carregarServidores()) {
                            servidor.getServidores().add(srv);
                            updateProgress(srv.getId(), total);
                            updateMessage("Carregando registros: " + srv.getId() + " de " + total);
                        }

                        updateMessage("DADOS CARREGADOS COM SUCESSO!");
                        Thread.sleep(1000);

                        return null;
                    }
                };
            }
        };

        textStatus.textProperty().bind(service.messageProperty());
        progressBar.progressProperty().bind(service.progressProperty());
        service.start();

        service.stateProperty().addListener((observableValue, o, t1) -> {

            if(observableValue.getValue().equals(Worker.State.SUCCEEDED)) {
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
            }
        });
    }

    private void inicializarElementos()  {

        labelNome.setLabelFor(textFieldNome);
        textFieldNome.setPromptText("Digite o nome completo do cliente...");

        grupoSexo = new ToggleGroup();
        radioMasculno.setToggleGroup(grupoSexo);
        radioFeminino.setToggleGroup(grupoSexo);
        grupoSexo.selectToggle(radioMasculno);

        labelNascimento.setLabelFor(pickerNascimento);
        pickerNascimento.setPromptText("00/00/0000");

        labelEstadoCivil.setLabelFor(comboEstadoCivil);
        comboEstadoCivil.setPromptText("Selecione...");

        labelFixo.setLabelFor(textFieldFixo);
        textFieldFixo.setPromptText("(00) 0000-0000");

        labelCelular.setLabelFor(textFieldCelular);
        textFieldCelular.setPromptText("(00) 0 0000-0000");

        labelEndereco.setLabelFor(textFieldEndereco);
        textFieldEndereco.setPromptText("Digite o endereço do cliente...");

        int linhasCombos = 7;
        labelPais.setLabelFor(comboPais);
        comboPais.setVisibleRowCount(linhasCombos);
        comboPais.setPromptText("Selecione um país...");

        labelEstado.setLabelFor(comboEstado);
        comboEstado.setVisibleRowCount(linhasCombos);
        comboEstado.setPromptText("Selecione um estado...");

        labelCidade.setLabelFor(comboCidade);
        comboCidade.setVisibleRowCount(linhasCombos);
        comboCidade.setPromptText("Selecione uma cidade...");

        linhasCombos = 4;

        labelNaconalidade.setLabelFor(comboNacionalidade);
        comboNacionalidade.setVisibleRowCount(linhasCombos);
        comboNacionalidade.setPromptText("Selecione uma nacionalidade...");

        labelNaturakidade.setLabelFor(comboNaturalidade);
        comboNaturalidade.setVisibleRowCount(linhasCombos);
        comboNaturalidade.setPromptText("Selecione uma naturalidade...");

        labelEtnia.setLabelFor(comboEtnia);
        comboEtnia.setVisibleRowCount(linhasCombos);
        comboEtnia.setPromptText("Selecione uma etnia..");

        linhasCombos = 3;
        labelReligiao.setLabelFor(comboReligao);
        comboReligao.setVisibleRowCount(linhasCombos);
        comboReligao.setPromptText("Selecione uma religião...");

        labelEmail.setLabelFor(textFieldEmail);
        textFieldEmail.setPromptText("Digite o email do cliente...");
        comboServidor.setVisibleRowCount(linhasCombos);
        comboServidor.setPromptText("servidor de Email...");

        textStatus.setVisible(false);

        progressBar.setVisible(false);

    }

    private void carregarEventos() {
        elementosFocados();
        acaoElementos();
    }

    private void elementosFocados()  {
        pickerNascimento.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                pickerNascimento.show();
            }
        });
        comboPais.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                comboEstado.getItems().clear();
                comboEstado.setPromptText("Selecione um estado...");
                comboCidade.setPromptText("Selecione uma cidade...");
                comboCidade.getItems().clear();
                if( comboPais.getItems().size() == 0 ) {
                    listarNomesPaises();
                };
            } else {
                comboPais.setEditable(false);
            }
        });
        comboEstado.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                comboCidade.getItems().clear();
                comboCidade.setPromptText("Selecione uma cidade...");
                if(comboEstado.getItems().size()==0) {
                    listarNomesEstados();
                }
            } else {
                comboEstado.setEditable(false);
            }
        });
        comboCidade.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                if(comboCidade.getItems().size()==0) {
                    listarNomesCidades();
                }
            } else {
                comboCidade.setEditable(false);
            }
        });
        comboNacionalidade.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                if(comboNacionalidade.getItems().size() == 0) {
                    listarNacionalidades();
                }
            } else {
                comboNacionalidade.setEditable(false);
            }
        });
        comboNaturalidade.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                if(comboNaturalidade.getItems().size() == 0) {
                    listarNaturalidades();
                }
            } else {
                comboNaturalidade.setEditable(false);
            }
        });
        comboEtnia.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                if(comboEtnia.getItems().size() == 0) {
                    listarEtnias();
                }
            } else {
                comboEtnia.setEditable(false);
            }
        });
        comboReligao.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                if(comboReligao.getItems().size() == 0) {
                    listarReligioes();
                }
            } else {
                comboReligao.setEditable(false);
            }
        });
        comboServidor.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(observableValue.getValue()) {
                if(comboServidor.getItems().size() == 0) {
                    listarServidoresEmail();
                }
            } else {
                comboServidor.setEditable(false);
            }
        });
    }

    private void acaoElementos() {

        comboPais.setOnAction(actionEvent -> {
            pais.setId(comboPais.getSelectionModel().getSelectedIndex());
        });


        comboEstado.setOnAction(actionEvent -> {
            estado.setId(comboEstado.getSelectionModel().getSelectedIndex());
        });

        comboCidade.setOnAction(actionEvent -> {
            cidade.setId(comboCidade.getSelectionModel().getSelectedIndex());
        });

        comboNacionalidade.setOnAction(actionEvent -> {
            for(Nacionalidade nc: nacionalidade.getNacionalidades()) {
                if(nc.getNacionalidade() == comboNacionalidade.getSelectionModel().getSelectedItem()) {
                    nacionalidade.setId(nc.getId());
                }
            }
        });

        comboNaturalidade.setOnAction(actionEvent -> {
            naturalidade.setId(comboNaturalidade.getSelectionModel().getSelectedIndex());

        });

        comboEtnia.setOnAction(actionEvent -> {
            etnia.setId(comboEtnia.getSelectionModel().getSelectedIndex());
        });

        comboReligao.setOnAction(actionEvent -> {
            religiao.setId(comboReligao.getSelectionModel().getSelectedIndex());
        });

        comboServidor.setOnAction(actionEvent -> {
            servidor.setId(comboReligao.getSelectionModel().getSelectedIndex());
        });

        buttonCadastrar.setOnAction(actionEvent -> {
            try {
                //validarCampos();
                persisirDados();
                String titulo;
                titulo = "Nome: " + cliente.getNome() + "\r\n" +
                        "Nascimento: " + cliente.getNascimento() + "\r\n" +
                        "SexoEnum: " + cliente.getSexo() + "\r\n" +
                        "Estado Civil: " + cliente.getEstadoCivil();
                NotificadorAlertas.mostrarMsgSucesso(titulo, "ok");
            } catch (Exception e) {
                NotificadorAlertas.mostrarMsgErro(e, "Dados informados incorretamente");
            }
        });
    }

    private void listarNomesPaises() {
        try {
            popularComboBox(pais.listaNomesPaises(), "Brasil", comboPais);
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nomes de países");
        }
    }


    private void listarNomesEstados(){
        try {
            if( pais.getId() > 0) {
                estado.setPais(pais);
                popularComboBox(estado.listaNomeEstadoPorPais(),"Pará", comboEstado);
            }
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nomes de estados");
        }
    }

    private void listarNomesCidades() {
        try {
            if (estado.getId() > 0) {
                cidade.setEstado(estado);
                popularComboBox(cidade.listaPorEstado(), "Belém", comboCidade);
            }
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nomes de estados");
        }
    }

    private void listarNacionalidades() {
        try {
            popularComboBox(nacionalidade.listaNomesNacionalidades(), "Brasileira", comboNacionalidade);
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nacionalidades");
        }    }

    private void listarNaturalidades() {
        try {
            if(nacionalidade.getId() > 0) {
                naturalidade.setNacionalidade(nacionalidade);
                popularComboBox(naturalidade.listaPorNacionalidade(), "Paraense", comboNaturalidade);
            }
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de naturalidades");
        } finally {

        }
    }

    private void listarEtnias() {
        try {
            popularComboBox(etnia.listaEtnias(), comboEtnia);
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de etnias");
        }
    }

    private void listarReligioes() {
        try {
            popularComboBox(religiao.listaReligioes(), comboReligao);
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de religiões");
        }
    }

    private void listarServidoresEmail() {
        try {
            popularComboBox(servidor.listaNomeServidor(), comboServidor);
        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de servidor de email");
        }
    }


    private void popularComboBox(List<String> elementos, String selecionar, ComboBox<String> comboBox) {
        executarPopularComboBox(elementos, comboBox);
        service.stateProperty().addListener((observableValue1, o, t11) -> {
            //service.cancel();
            textStatus.setVisible(false);
            progressBar.setVisible(false);
            anchorPane.setDisable(false);

            comboBox.requestFocus();

            comboBox.getItems().sorted((o1, o2) -> {

                if(o2.contains(selecionar)) {
                    comboBox.getSelectionModel().select(o2);
                } else {
                    comboBox.setPromptText("Selecione...");
                }
                return 0;
            });

        });
    }

    private void popularComboBox(List<String> elementos, ComboBox<String> comboBox) {
        executarPopularComboBox(elementos, comboBox);
        service.stateProperty().addListener((observableValue1, o, t11) -> {
            //service.cancel();
            textStatus.setVisible(false);
            progressBar.setVisible(false);
            anchorPane.setDisable(false);
            comboBox.requestFocus();
        });
    }

    private void executarPopularComboBox(List<String> elementos, ComboBox<String> comboBox) {
        service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        textStatus.setVisible(true);
                        progressBar.setVisible(true);
                        anchorPane.setDisable(true);
                        updateMessage("Carregando lista...");

                        ObservableList<String> lista = FXCollections.observableArrayList();
                        lista.add(null);
                        Thread.sleep(1000);
                        for(int i = 0; i < elementos.size(); i++ ) {
                            lista.add(elementos.get(i));
                            updateProgress(i, elementos.size());
                            updateMessage(elementos.get(i));
                            Thread.sleep(2);

                        }
                        comboBox.setItems(lista);
                        return null;
                    }
                };
            }
        };

        progressBar.progressProperty().bind(service.progressProperty());
        textStatus.textProperty().bind(service.messageProperty());

        service.start();

    }

    private void mascarearCampos() {
        MascaraCampos.textoField(textFieldNome);
        MascaraCampos.dataField(pickerNascimento);
        MascaraCampos.telFixoField(textFieldFixo);
        MascaraCampos.telMovelField(textFieldCelular);
        MascaraCampos.textoField(textFieldEndereco);
        MascaraCampos.emailField(textFieldEmail);
    }

    private void validarCampos() {
        try {
            ValidaCampos.validarData(pickerNascimento);
        } catch (Exception e) {
            NotificadorAlertas.mostrarMsgErro(e,"Erro tentar validar campos!");
        }
    }

    private void persisirDados() throws Exception {

        cliente.setNome(textFieldNome.getText().trim());

        cliente.setNascimento(pickerNascimento.getValue());

        if(radioMasculno.isSelected()) {
            cliente.setSexo(SexoEnum.MASCULINO);
        } else if (radioFeminino.isSelected()){
            cliente.setSexo(SexoEnum.FEMININO);
        }

        if(!comboEstadoCivil.getSelectionModel().isEmpty()){

            switch (comboEstadoCivil.getSelectionModel().getSelectedItem()) {
                case "Solteiro(a)" :
                    cliente.setEstadoCivil(EstadoCivilEnum.SOLTEIRO);
                    break;
                case "Casado(a)":
                    cliente.setEstadoCivil(EstadoCivilEnum.CASADO);
                    break;
                case "Divorciado(a)":
                    cliente.setEstadoCivil(EstadoCivilEnum.DIVORCIADO);
                    break;
                case "Viuvo(a)":
                    cliente.setEstadoCivil(EstadoCivilEnum.VIUVO);
                    break;
                    default:
                        throw new Exception("Estado civil informado desconhecido!");
            }

        } else {
            throw new Exception("Informe um estado civil");
        }
    }

}
