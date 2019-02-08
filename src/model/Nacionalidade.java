package model;

import dao.NacionalidadeDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Nacionalidade {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty pais = new SimpleStringProperty();
    private StringProperty nacionalidade = new SimpleStringProperty();
    private List<Nacionalidade> nacionalidades = new ArrayList<>();
    private NacionalidadeDao nacionalidadeDao;

    public Nacionalidade() throws Exception {
        nacionalidadeDao = new NacionalidadeDao();
    }

    public Nacionalidade(int id, String pais, String nacionalidade) {
        setId(id);
        setPais(pais);
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

    public String getPais() {
        return pais.get();
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }

    public StringProperty getPropridadePais() {
        return pais;
    }

    public void getPropridadePais(StringProperty pais) {
        this.pais = pais;
    }

    public String getNacionalidade() {
        return nacionalidade.get();
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade.set(nacionalidade);
    }

    public StringProperty getPropriedadeNacionalidadePais() {
        return nacionalidade;
    }

    public void setPropriedadeNacionalidadePais(StringProperty nacionalidadePais){
        this.nacionalidade = nacionalidadePais;
    }

    public List<Nacionalidade> getNacionalidades() {
        return nacionalidades;
    }

    public void setNacionalidades(List<Nacionalidade> nacionalidades) {
        this.nacionalidades = nacionalidades;
    }

    public void cadastrarNacionaliade() throws Exception {
        nacionalidadeDao.inserirNacionalidade(this);
    }

    public void atualizarNacionalidade() throws Exception {
        nacionalidadeDao.alterarNacionalidade(this);
    }

    public Nacionalidade pesquisarPorID() throws Exception {
        return nacionalidadeDao.pesquisarUm(this);
    }

    public List<Nacionalidade> pesquisarNacionalidades() throws Exception {
        return nacionalidadeDao.pesquisarTodos();
    }


    public List<String> listaNomesNacionalidades() {
        List<String> listaNacionalidades = new ArrayList<>();
        for(Nacionalidade n : nacionalidades) {
            listaNacionalidades.add(n.getNacionalidade());
        }
        return listaNacionalidades;
    }

    //public Naturalidade pesquisarNacio


    public int obterTotalNacionalidades() throws Exception {
        return nacionalidadeDao.contarTotalNacionalidades();
    }

}
