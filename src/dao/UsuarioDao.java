package dao;

import model.Usuario;

import java.sql.SQLException;
import java.util.*;

public class UsuarioDao extends ModelDao {


    public UsuarioDao() throws Exception {

    }

    public void inserirUsuario(Usuario usuario) throws Exception {

        try {
            prepararSQL(
                    "INSERT INTO Usuario(login,senha) value(?,?)"
            );
            getPs().setString(1, usuario.getLogin());
            getPs().setString(2, usuario.getSenha());
             executarSQL();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }

    }

    public void alterarUsuario(Usuario usuario) throws Exception {
        try {
            prepararSQL(
                "UPDATE usuario SET login = ?, senha = ? where id = ?"
            );
            getPs().setString(1, usuario.getLogin());
            getPs().setString(2,usuario.getSenha());
            getPs().setInt(3,usuario.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public void deletarUsuario(int id) throws Exception {
        try {
            prepararSQL(
                    "DELETE FROM usuario WHERE id=?"
            );
            executarSQL();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public Usuario pesquisarUm(int id) throws Exception {

        try {
            prepararSQL(
                "Select * from Usuario WHERE id=?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            if(getRs().next()) {
                return new Usuario(
                        id,
                        getRs().getString("login"),
                        getRs().getString("senha")
                );
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }

        return null;
    }

    public List<Usuario> pesquisarTodos() throws Exception {
        try {

            prepararSQL(
                    "SELECT * FROM usuario"
            );

            executarQuerySQL();

            List<Usuario> listaUsuario = new ArrayList<>();

            while (getRs().next()) {

                listaUsuario.add(
                       new Usuario(
                               getRs().getInt(1),
                               getRs().getString(2),
                               getRs().getString(3)
                       )
                );
            }

            return listaUsuario;

        } catch (SQLException sqle) {
            throw new  RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public boolean validarId(int id) throws Exception {

        try {
            prepararSQL("select * from Usuario where id=?");
            getPs().setInt(1, id);
            executarQuerySQL();

            if(getRs().next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }

    }

    public boolean validarLogin(String login) throws Exception {

        try {
            prepararSQL("select * from Usuario where login=?");
            getPs().setString(1, login);
            executarQuerySQL();

            if(getRs().next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }

    }

    public boolean validarSenha(String senha) throws Exception {

        try {
            prepararSQL("select * from Usuario where login=?");
            getPs().setString(1, senha);
            executarQuerySQL();

            if(getRs().next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            getPs().close();
            getRs().close();
        }

    }
}
