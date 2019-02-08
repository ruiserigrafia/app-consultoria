package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class Telefone {

    private IntegerProperty numero = new SimpleIntegerProperty();
    private List<Telefone> telefones = new ArrayList<>();

    public Telefone() {

    }

    public Telefone( int numero) {
        setNumero(numero);
    }

    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public IntegerProperty getPropriedadeNumero() {
        return numero;
    }

    public void setPropriedadeNumero(IntegerProperty numero) {
        this.numero = numero;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public abstract void cadastrarTelefone() throws Exception;
    public abstract void atualizarTelefone() throws Exception;
    public abstract void apagarTelefone() throws Exception;
    public abstract Object pesquisarIdTelefone() throws Exception;
    public abstract Object pesquisarNumeroTelefone() throws Exception;
    public abstract List pesquisarTodosTelefones() throws Exception;
}
