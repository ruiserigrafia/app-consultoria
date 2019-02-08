package dao;

import model.Email;
import model.Servidor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDao extends ModelDao {

    public EmailDao() throws Exception {

    }

    public void inserirEmail(Email email) throws Exception {

        try {
            prepararSQL(
                    "SELECT email VALUES (default, ?, ?)"
            );
            getPs().setString(1, email.getUsuario());
            getPs().setInt(2, email.getServidor().getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public void alterarEmail(Email email) throws Exception {

        try {
            prepararSQL(
                    "UPDATE email SET usuario = ?, servidor_id = ? WHERE id = ?"
            );
            getPs().setString(1, email.getUsuario());
            getPs().setInt(2, email.getServidor().getId());
            getPs().setInt(3, email.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public void deletarEmail(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM email WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public Email pesquisarPorId(int id) throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM email WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while (getRs().next()) {

                return new Email(
                        getRs().getInt(1),
                        getRs().getString(2),
                        new Servidor(getRs().getInt(3), null)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
        return null;
    }

    public Email pesquisarPorUsuario(String usuario) throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM email WHERE usuario = ?"
            );
            getPs().setString(1, usuario);
            executarQuerySQL();
            while (getRs().next()) {

                return new Email(
                        getRs().getInt(1),
                        getRs().getString(2),
                        new Servidor(getRs().getInt(3), null)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
        return null;

    }


    public List<Email> pesquisarTodos() throws Exception {

        List<Email> listaEmail = new ArrayList<>();

        try {

            prepararSQL(
                    "SELECT * FROM email"
            );
            executarQuerySQL();
            while (getRs().next()) {
                listaEmail.add(
                        new Email(
                                getRs().getInt(1),
                                getRs().getString(2),
                                new Servidor(
                                        getRs().getInt(3),
                                        null
                                )
                        )
                );
            }


        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

        return listaEmail;

    }

}
