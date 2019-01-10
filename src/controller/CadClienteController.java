package controller;

import ajudantes.NotificadorAlertas;
import ajudantes.MascaraCampos;
import ajudantes.ValidaCampos;
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
    private Text textStatus;

    @FXML
    ProgressBar progressBar;

    private ToggleGroup grupoSexo;
    private Pais pais;
    private Estado estado;
    private Cidade cidade;
    private Etnia etnia;
    private Religiao religiao;
    private Servidor servidor;

    private Service service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            pais = new Pais();
            estado = new Estado();
            cidade = new Cidade();
            etnia = new Etnia();
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
                        int total = pais.mostrarTotal();
                        updateMessage("Pesquisando registros de países...");
                        Thread.sleep(1000);
                        for (Pais p : pais.carregarPaises()) {
                            pais.getPaises().add(p);
                            updateProgress(p.getId(), total);
                            updateMessage("Carregando registros: " + p.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = estado.mostrarTotal();
                        updateMessage("Pesquisando registros de estados...");
                        Thread.sleep(1000);
                        for (Estado e : estado.carregarEstados()) {
                            estado.getEstados().add(e);
                            updateProgress(e.getId(), total);
                            updateMessage("Carregando registros: " + e.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = cidade.mostrarTotal();
                        updateMessage("Pesquisando registros de cidades...");
                        Thread.sleep(1000);
                        for (Cidade c : cidade.carregarCidades()) {
                            cidade.getCidades().add(c);
                            updateProgress(c.getId(), total);
                            updateMessage("Carregando registros: " + c.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = etnia.mostrarTotal();
                        updateMessage("Pesquisando registros de etnias...");
                        Thread.sleep(1000);
                        for (Etnia e : etnia.carregarEtnias()) {
                            etnia.getEtnias().add(e);
                            updateProgress(e.getId(), total);
                            updateMessage("Carregando registros: " + e.getId() + " de " + total);
                        }

                        updateProgress(0,0);
                        total = religiao.mostrarTotal();
                        updateMessage("Pesquisando registros de religião...");
                        Thread.sleep(1000);
                        for (Religiao r : religiao.carregarReligioes()) {
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

            comboNacionalidade.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
                pais.getPaises().sort((o1, o2) -> {
                    if(o2.getNacionalidade() == observableValue.getValue()){
                        pais.setId(o2.getId());                    }
                    return 0;
                });
            });

        });

        comboNaturalidade.setOnAction(actionEvent -> {
            estado.setId(comboNaturalidade.getSelectionModel().getSelectedIndex());
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


    }

    private void listarNomesPaises() {
        try {

            service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            textStatus.setVisible(true);
                            progressBar.setVisible(true);
                            anchorPane.setDisable(true);
                            updateMessage("Carregando lista de países...");

                            ObservableList<String> lista = FXCollections.observableArrayList();
                            lista.add(null);
                            Thread.sleep(1000);
                            for(int i = 0; i < pais.listaNomesPaises().size(); i++ ) {
                                lista.add(pais.listaNomesPaises().get(i));
                                updateProgress(i, pais.listaNomesPaises().size());
                                updateMessage(" " + pais.listaNomesPaises().get(i));
                                Thread.sleep(2);

                            }
                            comboPais.setItems(lista);
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            textStatus.textProperty().bind(service.messageProperty());

            service.start();

            service.stateProperty().addListener((observableValue1, o, t11) -> {
                //service.cancel();
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
                comboPais.requestFocus();
                comboPais.getItems().sort((o1, o2) -> {
                    if(o2.contains("Brasil")) {
                        comboPais.getSelectionModel().select(o2);
                    }
                    return 0;
                });
            });

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nomes de países");
        }
    }

    private void listarNomesEstados(){

        try {

            if( pais.getId() > 0) {

                estado.setPais(pais);

                service = new Service() {
                    @Override
                    protected Task createTask() {
                        return new Task() {
                            @Override
                            protected Object call() throws Exception {
                                textStatus.setVisible(true);
                                progressBar.setVisible(true);
                                anchorPane.setDisable(true);
                                updateMessage("Carregando lista de estados...");

                                ObservableList<String> lista = FXCollections.observableArrayList();
                                lista.add(null);
                                Thread.sleep(1000);
                                for(int i = 0; i < estado.listaNomeEstadoPorPais().size(); i++ ) {
                                    lista.add(estado.listaNomeEstadoPorPais().get(i));
                                    updateProgress(i, estado.listaNomeEstadoPorPais().size());
                                    updateMessage(" " + estado.listaNomeEstadoPorPais().get(i));
                                    Thread.sleep(2);

                                }
                                comboEstado.setItems(lista);
                                return null;
                            }
                        };
                    }
                };

                progressBar.progressProperty().bind(service.progressProperty());
                textStatus.textProperty().bind(service.messageProperty());

                service.start();

                service.stateProperty().addListener((observableValue1, o, t11) -> {
                    //service.cancel();
                    textStatus.setVisible(false);
                    progressBar.setVisible(false);
                    anchorPane.setDisable(false);
                    comboEstado.requestFocus();
                    comboEstado.getItems().sort((o1, o2) -> {
                        if (o2.contains("Pará")) {
                            comboEstado.getSelectionModel().select(o2);
                        } else {
                            comboEstado.setPromptText("Selecione uma cidade");
                        }
                        return 0;
                    });
                });

            }

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nomes de estados");
        }

    }

    private void listarNomesCidades() {

        try {

            if (estado.getId() > 0) {
                cidade.setEstado(estado);

                service = new Service() {
                    @Override
                    protected Task createTask() {
                        return new Task() {
                            @Override
                            protected Object call() throws Exception {
                                textStatus.setVisible(true);
                                progressBar.setVisible(true);
                                anchorPane.setDisable(true);
                                updateMessage("Carregando lista de cidades...");

                                ObservableList<String> lista = FXCollections.observableArrayList();
                                lista.add(null);
                                Thread.sleep(1000);
                                for(int i = 0; i < cidade.listaPorEstado().size(); i++ ) {
                                    lista.add(cidade.listaPorEstado().get(i));
                                    updateProgress(i, cidade.listaPorEstado().size());
                                    updateMessage(" " + cidade.listaPorEstado().get(i));
                                    Thread.sleep(2);

                                }
                                comboCidade.setItems(lista);
                                return null;
                            }
                        };
                    }
                };

                progressBar.progressProperty().bind(service.progressProperty());
                textStatus.textProperty().bind(service.messageProperty());

                service.start();

                service.stateProperty().addListener((observableValue1, o, t11) -> {
                    //service.cancel();
                    textStatus.setVisible(false);
                    progressBar.setVisible(false);
                    anchorPane.setDisable(false);
                    comboCidade.requestFocus();
                    comboCidade.getItems().sort((o1, o2) -> {
                        if (o2.contains("Belém")) {
                            comboCidade.getSelectionModel().select(o2);
                        } else {
                            comboCidade.setPromptText("Selecione uma cidade");
                        }
                        return 0;
                    });
                });

            }

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nomes de estados");
        }

    }

    private void listarNacionalidades() {

        try {

            service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            textStatus.setVisible(true);
                            progressBar.setVisible(true);
                            anchorPane.setDisable(true);
                            updateMessage("Carregando lista de nacionalidades...");

                            ObservableList<String> lista = FXCollections.observableArrayList();
                            lista.add(null);
                            Thread.sleep(1000);
                            for(int i = 0; i < pais.listaNacionalidades().size(); i++ ) {
                                lista.add(pais.listaNacionalidades().get(i));
                                updateProgress(i, pais.listaNacionalidades().size());
                                updateMessage(" " + pais.listaNacionalidades().get(i));
                                Thread.sleep(2);

                            }
                            comboNacionalidade.setItems(lista);
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            textStatus.textProperty().bind(service.messageProperty());

            service.start();

            service.stateProperty().addListener((observableValue1, o, t11) -> {
                //service.cancel();
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
                comboNacionalidade.requestFocus();
                comboNacionalidade.getItems().sort((o1, o2) -> {
                    if (o2.contains("Brasileira")) {
                        comboNacionalidade.getSelectionModel().select(o2);
                    } else {
                        comboCidade.setPromptText("Selecione uma nacionalidade");
                    }
                    return 0;
                });
            });

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de nacionalidades");
        }
    }

    private void listarNaturalidades() {

        try {

            estado.setPais(pais);

            service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            textStatus.setVisible(true);
                            progressBar.setVisible(true);
                            anchorPane.setDisable(true);
                            updateMessage("Carregando lista de nacionalidades...");

                            ObservableList<String> lista = FXCollections.observableArrayList();
                            lista.add(null);
                            Thread.sleep(1000);
                            for(int i = 0; i < estado.listaNaturalidade().size(); i++ ) {
                                lista.add(estado.listaNaturalidade().get(i));
                                updateProgress(i, estado.listaNaturalidade().size());
                                updateMessage(" " + estado.listaNaturalidade().get(i));
                                Thread.sleep(2);
                            }
                            comboNaturalidade.setItems(lista);
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            textStatus.textProperty().bind(service.messageProperty());

            service.start();

            service.stateProperty().addListener((observableValue1, o, t11) -> {
                //service.cancel();
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
                comboNaturalidade.requestFocus();
                comboNaturalidade.getItems().sort((o1, o2) -> {
                    if(o2.contains("Paraense")) {
                        comboNaturalidade.getSelectionModel().select(o2);
                    } else {
                        comboNaturalidade.setPromptText("Selecione uma naturalidade");
                    }

                    return 0;
                });

            });

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de naturalidades");
        }

    }

    private void listarEtnias() {
        try {

            service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            textStatus.setVisible(true);
                            progressBar.setVisible(true);
                            anchorPane.setDisable(true);
                            updateMessage("Carregando lista de nacionalidades...");

                            ObservableList<String> lista = FXCollections.observableArrayList();
                            lista.add(null);
                            Thread.sleep(1000);
                            for(int i = 0; i < etnia.listaEtnias().size(); i++ ) {
                                lista.add(etnia.listaEtnias().get(i));
                                updateProgress(i, etnia.listaEtnias().size());
                                updateMessage(" " + etnia.listaEtnias().get(i));
                                Thread.sleep(2);

                            }
                            comboEtnia.setItems(lista);
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            textStatus.textProperty().bind(service.messageProperty());

            service.start();

            service.stateProperty().addListener((observableValue1, o, t11) -> {
                //service.cancel();
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
                comboEtnia.requestFocus();
            });

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de etnias");
        }
    }

    private void listarReligioes() {
        try {

            service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            textStatus.setVisible(true);
                            progressBar.setVisible(true);
                            anchorPane.setDisable(true);
                            updateMessage("Carregando lista de nacionalidades...");

                            ObservableList<String> lista = FXCollections.observableArrayList();
                            lista.add(null);
                            Thread.sleep(1000);
                            for(int i = 0; i < religiao.listaReligioes().size(); i++ ) {
                                lista.add(religiao.listaReligioes().get(i));
                                updateProgress(i, religiao.listaReligioes().size());
                                updateMessage(" " + religiao.listaReligioes().get(i));
                                Thread.sleep(2);

                            }
                            comboReligao.setItems(lista);
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            textStatus.textProperty().bind(service.messageProperty());

            service.start();

            service.stateProperty().addListener((observableValue1, o, t11) -> {
                //service.cancel();
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
                comboReligao.requestFocus();
            });

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de religiões");
        }
    }


    private void listarServidoresEmail() {
        try {
            service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            textStatus.setVisible(true);
                            progressBar.setVisible(true);
                            anchorPane.setDisable(true);
                            updateMessage("Carregando lista de nacionalidades...");

                            ObservableList<String> lista = FXCollections.observableArrayList();
                            lista.add(null);
                            Thread.sleep(1000);
                            for(int i = 0; i < servidor.listaNomeServidor().size(); i++ ) {
                                lista.add(servidor.listaNomeServidor().get(i));
                                updateProgress(i, servidor.listaNomeServidor().size());
                                updateMessage(" " + servidor.listaNomeServidor().get(i));
                                Thread.sleep(2);

                            }
                            comboServidor.setItems(lista);
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            textStatus.textProperty().bind(service.messageProperty());

            service.start();

            service.stateProperty().addListener((observableValue1, o, t11) -> {
                //service.cancel();
                textStatus.setVisible(false);
                progressBar.setVisible(false);
                anchorPane.setDisable(false);
                comboServidor.requestFocus();
            });

        } catch (Exception ex) {
            NotificadorAlertas.mostrarMsgErro(ex, "Erro ao tentar mostrar lista de servidor de email");
        }
    }

    private void mascarearCampos() {
        MascaraCampos.textoField(textFieldNome);
        MascaraCampos.dataField(pickerNascimento);
        MascaraCampos.telFixoField(textFieldFixo);
        MascaraCampos.telMovelField(textFieldCelular);
        MascaraCampos.textoField(textFieldEndereco);
        MascaraCampos.palavraField(textFieldEmail);
    }

    private void validarCampos() throws Exception {
        ValidaCampos.validarData(pickerNascimento);
    }



}
