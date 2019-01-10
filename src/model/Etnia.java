package model;


import dao.EtniaDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Etnia {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty definicao = new SimpleStringProperty();
    private List<Etnia> etnias = new ArrayList<>();
    private EtniaDao etniaDao;

    public Etnia() throws Exception {
        etniaDao = new EtniaDao();
    }

    public Etnia(int id, String definicao) {
        this.setId(id);
        this.setDefinicao(definicao);
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


    public String getDefinicao() {
        return definicao.get();
    }

    public void setDefinicao(String definicao) {
        this.definicao.set(definicao);
    }

    public StringProperty getPropriedadeDefinicao() {
        return definicao;
    }

    public void setProprieadeDefinicao(StringProperty definicao) {
        this.definicao = definicao;
    }

    public List<Etnia> getEtnias() {
        return etnias;
    }

    public void setEtnias(List<Etnia> etnias) {
        this.etnias = etnias;
    }

    public List<Etnia> carregarEtnias() throws Exception {
        return etniaDao.pesquisarTodos();
    }

    public int mostrarTotal() throws Exception {
        return etniaDao.contarQuantidadeEtnia();
    }

    public List<String> listaEtnias() {
        List<String> listaEtnias = new ArrayList<>();

        for(Etnia itemEtnia : etnias) {
            listaEtnias.add(itemEtnia.getDefinicao());
        }
        return listaEtnias;
    }

}
