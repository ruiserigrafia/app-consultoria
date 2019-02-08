package dao;

import model.Religiao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReligiaoDao extends ModelDao {

    public ReligiaoDao() throws Exception {
    }

    public void inserirReligiao(Religiao religiao) throws Exception {

        try {
            prepararSQL(
                    "INSERT INTO religiao VALUES (default, ?)"
            );
            getPs().setString(1, religiao.getDefinicao());
            executarSQL();
        } catch (SQLException sgle) {
            throw new Exception(sgle);
        } finally {
            getPs().close();
        }

    }

    public void alterarReligao(Religiao religiao) throws Exception {

        try {
            prepararSQL(
                    "UPDATE religiao SET definicao = ? WHERE id = ?"
            );
            getPs().setString(1, religiao.getDefinicao());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public void deletarReligiao(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM religiao WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    public Religiao pesquisarId(int id) throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM religiao WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while (getRs().next()) {
                return new Religiao(
                        getRs().getInt(1),
                        getRs().getString(2)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

        return null;
    }

    public Religiao pesquisarDefinicao(String nome) throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM religiao WHERE definicao = ?"
            );
            getPs().setString(1, nome);
            executarQuerySQL();
            while (getRs().next()) {
                return new Religiao(
                        getRs().getInt(1),
                        getRs().getString(2)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

        return null;

    }

    public List<Religiao> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM religiao"
            );

            executarQuerySQL();

            List<Religiao> religiaos = new ArrayList<>();

            while (getRs().next()) {
                religiaos.add(
                        new Religiao(
                            getRs().getInt(1),
                            getRs().getString(2)
                        )
                );
            }

            return religiaos;

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int contarQuantidadeReligioes() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM religiao"
            );

            executarQuerySQL();

            while (getRs().next()) {
                return getRs().getInt(1);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }

        return 0;

    }

}
