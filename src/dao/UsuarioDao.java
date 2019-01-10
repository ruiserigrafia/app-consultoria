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
            getPrepararInstrucao().setString(1, usuario.getLogin());
            getPrepararInstrucao().setString(2, usuario.getSenha());
             executarInstrucao();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }

    public void alterarUsuario(Usuario usuario) throws Exception {
        try {
            prepararSQL(
                "UPDATE usuario SET login = ?, senha = ? where id = ?"
            );
            getPrepararInstrucao().setString(1, usuario.getLogin());
            getPrepararInstrucao().setString(2,usuario.getSenha());
            getPrepararInstrucao().setInt(3,usuario.getId());
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public void deletarUsuario(int id) throws Exception {
        try {
            prepararSQL(
                    "DELETE FROM usuario WHERE id=?"
            );
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public Usuario pesquisarUm(int id) throws Exception {

        try {
            prepararSQL(
                "Select * from Usuario WHERE id=?"
            );
            getPrepararInstrucao().setInt(1, id);
            consultarBanco();
            if(getResultados().next()) {
                return new Usuario(
                        id,
                        getResultados().getString("login"),
                        getResultados().getString("senha")
                );
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

        return null;
    }

    public List<Usuario> pesquisarTodos() throws Exception {
        try {

            prepararSQL(
                    "SELECT * FROM usuario"
            );

            consultarBanco();

            List<Usuario> listaUsuario = new ArrayList<>();

            while (getResultados().next()) {

                listaUsuario.add(
                       new Usuario(
                               getResultados().getInt(1),
                               getResultados().getString(2),
                               getResultados().getString(3)
                       )
                );
            }

            return listaUsuario;

        } catch (SQLException sqle) {
            throw new  RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public boolean validarId(int id) throws Exception {

        try {
            prepararSQL("select * from Usuario where id=?");
            getPrepararInstrucao().setInt(1, id);
            consultarBanco();

            if(getResultados().next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }

    public boolean validarLogin(String login) throws Exception {

        try {
            prepararSQL("select * from Usuario where login=?");
            getPrepararInstrucao().setString(1, login);
            consultarBanco();

            if(getResultados().next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }

    public boolean validarSenha(String senha) throws Exception {

        try {
            prepararSQL("select * from Usuario where login=?");
            getPrepararInstrucao().setString(1, senha);
            consultarBanco();

            if(getResultados().next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }
}
