package model;

import dao.EmailDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Email {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty usuario = new SimpleStringProperty();
    private Servidor servidor;
    private List<Email> emmails = new ArrayList<>();
    private EmailDao emailDao;

    public Email() throws Exception {
        emailDao = new EmailDao();
    }

    public Email(int id, String usuario, Servidor servidor) {
        setId(id);
        setUsuario(usuario);
        setServidor(servidor);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getPeopriedadeId() {
        return id;
    }

    public void setPropriedadeId(IntegerProperty id) {
        id = id;
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public StringProperty getPropriedadeUsuario() {
        return usuario;
    }

    public void setPropriedadeusuario(StringProperty usuario) {
        this.usuario = usuario;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public List<Email> getEmails() {
        return emmails;
    }

    public void setEmmails(List<Email> emmails) {
        this.emmails = emmails;
    }

    public void cadastarEmail() throws Exception {
        emailDao.inserirEmail(this);
    }

    public void alterarEmail() throws Exception {
        emailDao.alterarEmail(this);
    }

    public void apagarEmail() throws Exception {
        emailDao.deletarEmail(this.getId());
    }

    public Object pesquisarIdEmail() throws Exception {
        return emailDao.pesquisarPorId(this.getId());
    }

    public Object pesquisarUsuarioEmail() throws Exception {
        return emailDao.pesquisarPorUsuario(this.getUsuario());
    }

    public List<Email> pesquisatTodosEmails() throws Exception {
        return emailDao.pesquisarTodos();
    }

}
