package model;

import dao.EstadoDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Estado {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome =new SimpleStringProperty();
    private StringProperty uf = new SimpleStringProperty();
    private Pais pais;
    private List<Estado> estados = new ArrayList<>();
    private EstadoDao estadoDao;


    public Estado() throws Exception {
        estadoDao = new EstadoDao();
    }

    public Estado(int id, String nome, String uf, Pais pais) {
        setId(id);
        setNome(nome);
        setUf(uf);
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

    public Pais getPais() {
        return pais;
    }

    public void setPais (Pais pais)
    {
        this.pais = pais;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }


    public void cadastrarEstado() throws Exception {
       this.setId(estadoDao.inserirEstado(this));
    }

    public void atualizarEstado() throws Exception {
        estadoDao.alterarEstado(this);
    }

    public void excluirEstado() throws Exception {
        estadoDao.deletarEstado(this.getId());
    }

    public Object pesquisarPorNomeEstado() throws Exception {
        return estadoDao.pesquisarPorNome(this.getNome());
    }

    public Object pesquisarPorUF() throws Exception {
        return estadoDao.pesquisarPorUF(this.getUf());
    }

    public List<Estado> pesquisarEstados() throws Exception {
        return estadoDao.pesquisarTodos();
    }

    public int obterTotalEstados() throws Exception {
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

}
