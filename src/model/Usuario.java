package model;

import dao.UsuarioDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty login = new SimpleStringProperty();
    private StringProperty senha = new SimpleStringProperty();
    private List<Usuario> usuarios = new ArrayList<>();
    private UsuarioDao usuarioDao;

    public Usuario() throws Exception {
        usuarioDao = new UsuarioDao();
    }

    public Usuario(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    public Usuario(int id, String login, String senha) {
        setId(id);
        setLogin(login);
        setSenha(senha);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getIdPropriedade() {
        return id;
    }

    public void setIdPropriedade(IntegerProperty id) {
        this.id = id;
    }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty getLoginPropriedade() {
        return login;
    }

    public void setLoginPropriedade(StringProperty login) {
        this.login = login;
    }

    public String getSenha() {
        return senha.get();
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public StringProperty getSenhaPropriedade() {
        return senha;
    }

    public void setSenhaPropriedade(StringProperty senha) {
        this.senha = senha;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void buscaUsuarios() throws Exception {
        setUsuarios(usuarioDao.pesquisarTodos());
    }

    public void cadastrarUsuario() throws Exception{
        usuarioDao.inserirUsuario(new Usuario(getLogin(), getSenha()));
    }

    public void atualizarUsuario() {

    }

    public void apagarUsuario() {

    }

    public boolean pesquisarId() throws Exception {
        return usuarioDao.validarId(this.id.get());
    }

    public boolean validarLogin() throws Exception {
        return usuarioDao.validarLogin(getLogin());
    }

    public boolean validarSenha() throws Exception {
        return usuarioDao.validarSenha(getSenha());
    }
}
