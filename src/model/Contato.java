package model;

import dao.ContatoDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Contato {

    private IntegerProperty id = new SimpleIntegerProperty();
    private Celular celular;
    private Fixo fixo;
    private Email email;
    private List<Contato> contatos = new ArrayList<>();
    private ContatoDao contatoDao;

    public Contato() throws Exception {
        contatoDao = new ContatoDao();
    }

    public Contato(int id, Celular celular, Fixo fixo, Email email) {
        setId(id);
        setCelular(celular);
        setFixo(fixo);
        setEmail(email);
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

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public Fixo getFixo() {
        return fixo;
    }

    public void setFixo(Fixo fixo) {
        this.fixo = fixo;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void adcionarContato() throws Exception {
        contatoDao.inserirContato(this);
    }

    public void atualizarContato() throws Exception {
        contatoDao.alterarContato(this);
    }

    public void excluirContato() throws Exception {
        contatoDao.deletarContato(this.getId());
    }

    public Contato pesquisarContato() throws Exception {
        return contatoDao.pesquisarUm(this.getId());
    }

    public List<Contato> pesquisarTodosContatos() throws Exception {
        return contatoDao.pesquisarTodos();
    }

}
