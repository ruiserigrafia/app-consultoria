package model;

import dao.ServidorDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private List<Servidor> servidores = new ArrayList<>();
    ServidorDao servidorDao;


    public Servidor() throws Exception {
        servidorDao = new ServidorDao();
    }

    public Servidor(int id, String nome) {
        setId(id);
        setNome(nome);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getProprieadeId() {
        return id;
    }

    public void setPropriedadeId(IntegerProperty id) {
        this.id = id;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty getPropriedadeNome() {
        return nome;
    }

    public void setProprieadeNome(StringProperty nome) {
        this.nome = nome;
    }

    public List<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }

    public List<Servidor> carregarServidores() throws Exception {
        return servidorDao.pesquisarTodos();
    }

    public int mostrarTotal() throws Exception {
        return servidorDao.contarQuantidadeServidores();
    }

    public List<String> listaNomeServidor() {
        List<String> nomesServidor = new ArrayList<>();
        for (Servidor srv : servidores) {
            nomesServidor.add(srv.getNome());
        }
        return nomesServidor;
    }
}
