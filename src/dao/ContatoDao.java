package dao;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao extends ModelDao {

    public ContatoDao() throws Exception{

    }

    public int inserirContato(Contato contato) throws Exception {

        try {

            prepararSQL(
                    "INSERT INTO contato VALUES(default, ?, ?, ?)"
            );
            getPs().setInt(1, contato.getCelular().getId());
            getPs().setInt(2,contato.getFixo().getId());
            getPs().setInt(3,contato.getEmail().getId());
            if (executarSQL()) {
                return mostrarIdGerado();
            } else {
                new Exception("Erro ao tentar inserir contato");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
        return 0;
    }

    public void alterarContato(Contato contato) throws Exception {

        try {

            prepararSQL(
                    "UPDATE contato SET celular_id = ?, fix0_id = ?, email_id = ? WHERE id = ?"
            );
            getPs().setInt(1, contato.getCelular().getId());
            getPs().setInt(2, contato.getFixo().getId());
            getPs().setInt(3, contato.getEmail().getId());
            getPs().setInt(4, contato.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public void deletarContato(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM contato WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public Contato pesquisarUm(int id) throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM contato WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while (getRs().next()) {
                new Contato(
                  getRs().getInt(1),
                  new Celular(getRs().getInt(2), false),
                  new Fixo(getRs().getInt(3), 0),
                  new Email(getRs().getInt(4), null, null)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }

        return null;
    }

    public List<Contato> pesquisarTodos() throws Exception {

        List<Contato> listaContato = new ArrayList<>();

        try {

            prepararSQL(
                    "SELECT * FROM contato"
            );
            executarQuerySQL();
            while (getRs().next()) {
                listaContato.add(
                        new Contato(
                                getRs().getInt(1),
                                new Celular(getRs().getInt(2), false),
                                new Fixo(getRs().getInt(3), 0),
                                new Email(getRs().getInt(4), null, null)
                        )
                );
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }

        return listaContato;

    }

}
