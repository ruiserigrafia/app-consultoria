package model;

import dao.CidadeDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Cidade {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private Estado estado;
    private List<Cidade> cidades = new ArrayList<>();
    private CidadeDao cidadeDao;

    public Cidade() throws Exception {
        cidadeDao = new CidadeDao();
    }

    public Cidade(int id, String nome, Estado estado) {
        setId(id);
        setNome(nome);
        setEstado(estado);
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

    public void setPropriedade(IntegerProperty id) {
        this.id = id;
    }

    public String getNome(){
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public void cadastrarCidade() throws Exception {
        this.setId(cidadeDao.inserirCidade(this));
    }

    public void atualizarCidade() throws Exception {
        cidadeDao.alterarCidade(this);
    }

    public void excluirCidade() throws Exception{
        cidadeDao.deletarCidade(this.getId());
    }

    public Object pesquisarIdCidade() throws Exception {
        return cidadeDao.pesquisarPorId(this.getId());
    }

    public Object pesquisarNomeCidade() throws Exception {
        return cidadeDao.pesquisarPorNome(this.getNome());
    }

    public List<Cidade> pesquisarCidades() throws Exception {
        return cidadeDao.pesquisarTodos();
    }

    public int obterTotalCidades() throws Exception {
        return cidadeDao.contarQuantidadeCidade();
    }

    public List<String> listaPorEstado() {
        List<String> listaCidades = new ArrayList<>();
        for (Cidade c :  cidades) {
            if(c.getEstado().getId() == this.estado.getId()) {
                listaCidades.add(c.getNome());
            }
        }
        return listaCidades;
    }

}
