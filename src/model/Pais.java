package model;

import dao.PaisDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Pais {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private StringProperty isp = new SimpleStringProperty();
    private IntegerProperty ddi = new SimpleIntegerProperty();
    private StringProperty nacionalidade = new SimpleStringProperty();
    private List<Pais> paises = new ArrayList<>();
    private PaisDao paisDao;

    public Pais() throws Exception{
        paisDao = new PaisDao();
    }

    public Pais(int id) {
        this.setId(id);
    }

    public Pais(int id, String nome, String isp, int ddi, String nacionalidade) {
        setId(id);
        setNome(nome);
        setISP(isp);
        setDDI(ddi);
        setNacionalidade(nacionalidade);
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

    public void setPropriedadeNome(StringProperty nome) {
        this.nome = nome;
    }

    public String getISP() {
        return isp.get();
    }

    public void setISP(String isp) {
        this.isp.set(isp);
    }

    public StringProperty getPropriedadeISP() {
        return isp;
    }

    public void getPropiedadeISP(StringProperty isp) {
        this.isp = isp;
    }

    public int getDDI() {
        return ddi.get();
    }

    public void setDDI(int ddi) {
        this.ddi.set(ddi);
    }

    public IntegerProperty getPropriedadeDDI() {
        return ddi;
    }

    public void setPropriedadeDDI(IntegerProperty ddi) {
        this.ddi = ddi;
    }

    public String getNacionalidade() {
        return nacionalidade.get();
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade.set(nacionalidade);
    }

    public StringProperty getPropriedadeNacionalidade() {
        return nacionalidade;
    }

    public void setPropriedadeNacionalidade(StringProperty nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public List<Pais> carregarPaises() throws Exception{
        return paisDao.pesquisarTodos();
    }

    public void adicionarPais() throws Exception {
        paisDao.inserirPais(new Pais(getId(), getNome(), getISP(), getDDI(), getNacionalidade()));
    }

    public void atualizarPais() throws Exception {
        paisDao.alterarPais(new Pais(getId(), getNome(), getISP(), getDDI(), getNacionalidade()));
    }

    public void excluirPais() throws Exception {
        paisDao.deletarDados(getId());
    }

    public void addPaises() throws Exception {

    }

    public void idPorNaconalidade(String nacionalidade) {
        this.setNacionalidade(nacionalidade);
        for(Pais p : paises) {
            if(p.getNacionalidade() == getNacionalidade()) {
                setId(p.getId());
            }
        }
    }

    public List<String> listaNomesPaises() {
        List<String> nomesPaises = new ArrayList<>();
        nomesPaises.clear();
        for(Pais pais : paises ) {
            nomesPaises.add(pais.getNome());
        }
        return nomesPaises;
    }

    public List<String> listaNacionalidades() {
        List<String> listaNacionalidades = new ArrayList<>();
        for(Pais pais : paises ) {
            if(pais.getNacionalidade() != null) {
                listaNacionalidades.add(pais.getNacionalidade());
            }
        }
        return listaNacionalidades;
    }

    public int mostrarTotal() throws Exception {
        return paisDao.contarPaises();
    }




}
