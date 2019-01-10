package model;

import com.oracle.javafx.scenebuilder.kit.editor.panel.inspector.editors.FxIdEditor;
import dao.EstadoDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Estado {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome =new SimpleStringProperty();
    private StringProperty uf = new SimpleStringProperty();
    private StringProperty naturalidade = new SimpleStringProperty();
    private Pais pais;
    private List<Estado> estados = new ArrayList<>();
    private EstadoDao estadoDao;


    public Estado() throws Exception {
        estadoDao = new EstadoDao();
    }

    public Estado(int id) {
        setId(id);
    }

    public Estado(int id, String nome, String uf, String naturalidade, Pais pais) {
        setId(id);
        setNome(nome);
        setUf(uf);
        setNaturalidade(naturalidade);
        setPais(pais);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getPropriedadeId() {
        return id;
    }

    public void setProridadeId(IntegerProperty id) {
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

    public void setPropridadeNome(StringProperty nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf.get();
    }

    public void setUf(String uf) {
        this.uf.set(uf);
    }

    public StringProperty getPropriedadeUf() {
        return uf;
    }

    public void setPropriedadeUf(StringProperty uf) {
        this.uf = uf;
    }

    public String getNaturalidade() {
        return naturalidade.get();
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade.set(naturalidade);
    }

    public StringProperty getPropriedadeNaturalidade() {
        return naturalidade;
    }

    public void setPropriedadeNaturalidade(StringProperty naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais (Pais pais)
    {
        this.pais = pais;
    }

    public void salvarEstado() throws Exception {
        estadoDao.inserirEstado(new Estado(getId(), getNome(), getUf(), getNaturalidade(), getPais()));
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public void atualizarEstado() throws Exception {
        estadoDao.alterarEstado(new Estado(getId(), getNome(), getUf(), getNaturalidade(), getPais()));

    }

    public void excluirEstado() throws Exception {
        estadoDao.deletarEstado(getId());
    }

    public List<Estado> carregarEstados() throws Exception {
        return estadoDao.pesquisarTodos();
    }

    public int mostrarTotal() throws Exception {
        return estadoDao.contarQuantidadeEstado();
    }

    public List<String> listaNomeEstadoPorPais() {
        List<String> nomesEstados = new ArrayList<>();
        for (Estado itemEstado : estados) {
            if(itemEstado.getPais().getId() == this.pais.getId() ) {
                nomesEstados.add(itemEstado.getNome());
            }
        }
        return nomesEstados;
    }

    public List<String> listaNaturalidade() {
        List<String> naturalidades = new ArrayList<>();
        for(Estado itemEstado : estados ) {
            if(itemEstado.getPais().getId() == this.pais.getId()) {
                naturalidades.add(itemEstado.getNaturalidade());
            }
        }
        return naturalidades;
    }

}
