package model;


import dao.FixoDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Fixo extends Telefone {

    private IntegerProperty id = new SimpleIntegerProperty();
    private FixoDao fixoDao;

    public Fixo() throws Exception {
        super();
        fixoDao = new FixoDao();
    }

    public Fixo(int id, int numero){
        super(numero);
        setId(id);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty getProriedadeId() {
        return id;
    }

    public void getPropriedadeId(IntegerProperty id) {
        this.id = id;
    }

    @Override
    public void cadastrarTelefone() throws Exception {
        fixoDao.inserirTelefone(this);
    }

    @Override
    public void atualizarTelefone() throws Exception {
        fixoDao.alterarTelefone(this);
    }

    @Override
    public void apagarTelefone() throws Exception {
        fixoDao.excluirTelefone(this.getId());
    }

    @Override
    public Object pesquisarIdTelefone() throws Exception {
        return fixoDao.pesquisarPorId(this.getId());
    }

    @Override
    public Object pesquisarNumeroTelefone() throws Exception {
        return fixoDao.pesquisarPorNumero(this.getNumero());
    }

    @Override
    public List pesquisarTodosTelefones() throws Exception {
        return fixoDao.pesquisarTodos();
    }

}
