package model;


import dao.CelularDao;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Celular extends Telefone {

    private IntegerProperty id = new SimpleIntegerProperty();
    private BooleanProperty whatsapp;
    private CelularDao celularDao;

    public Celular() throws Exception {
        celularDao = new CelularDao();
    }

    public Celular(int id, boolean whatsapp) {
        super();
        setId(id);
        setWhatsapp(whatsapp);
    }

    public Celular(int id, int numero, boolean whatsapp) {
        super(numero);
        setId(id);
        setWhatsapp(whatsapp);
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

    public void setPropriedadde(IntegerProperty id) {
        this.id = id;
    }

    public boolean isWhatsapp() {
        return whatsapp.get();
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp.set(whatsapp);
    }

    public BooleanProperty getPropriedadeWhatsapp() {
        return whatsapp;
    }

    public void setPropriedadeWhatsapp(BooleanProperty whatsapp) {
        this.whatsapp = whatsapp;
    }

    @Override
    public void cadastrarTelefone() throws Exception {
        this.setId(celularDao.inserirTelefone(this));
    }

    @Override
    public void atualizarTelefone() throws Exception {
        celularDao.alterarTelefone(this);
    }

    @Override
    public void apagarTelefone() throws Exception {
        celularDao.excluirTelefone(this.getId());
    }

    @Override
    public Object pesquisarIdTelefone() throws Exception {
        return celularDao.pesquisarPorId(this.getId());
    }

    @Override
    public Object pesquisarNumeroTelefone() throws Exception {
        return celularDao.pesquisarPorNumero(this.getNumero());
    }

    @Override
    public List pesquisarTodosTelefones() throws Exception {
        return celularDao.pesquisarTodos();
    }
}
