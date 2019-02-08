package dao;

import model.Pais;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDao extends ModelDao {

    public PaisDao() throws Exception {

    }

    public void inserirPais(Pais pais) throws Exception {
        try {
            prepararSQL(
                "INSERT INTO pais(nome, isp, ddd) VALUES (?, ?, ?)"
            );
            getPs().setString(1, pais.getNome());
            getPs().setString(2, pais.getISP());
            getPs().setInt(3,pais.getDDI());
            executarSQL();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public void alterarPais(Pais pais) throws Exception {

        try{
            prepararSQL(
                "UPDATE pais SET nome = ?, isp = ?, ddd = ? WHERE id = ?"
            );
            getPs().setString(1, pais.getNome());
            getPs().setString(2, pais.getISP());
            getPs().setInt(3,pais.getDDI());
            getPs().setInt(4, pais.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

    }

    public void deletarDados(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM pais WHERE id = ?"
            );
            getPs().setInt(1 , id);
            executarSQL();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

    }

    public Pais pesquisarPorid(int id) throws Exception {

        try {

            prepararSQL(
                    "SELECT * FROM pais WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4)
                );
            };

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return null;
    }

    public Pais pesquisarPorNome(String nome) throws Exception {

        try {

            prepararSQL(
                    "SELECT * FROM pais WHERE nome = ?"
            );
            getPs().setString(1, nome);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4)
                );
            };

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return null;

    }

    public Pais pesquisarPorISP(String isp) throws Exception {
        try {

            prepararSQL(
                    "SELECT * FROM pais WHERE isp = ?"
            );
            getPs().setString(1, isp);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4)
                );
            };

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return null;
    }

    public Pais pesquisarPorDDI(int ddi) throws Exception {

        try {

            prepararSQL(
                    "SELECT * FROM pais WHERE ddi = ?"
            );
            getPs().setInt(1, ddi);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4)
                );
            };

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return null;

    }

    public Pais pesquisarPorNacionalidade(String nacionalidade) throws Exception {

        try {

            prepararSQL(
                "SELECT * FROM pais WHERE nacionalidade = ?"
            );
            getPs().setString(1, nacionalidade);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4)
                );
            };

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return null;
    }

    public List<Pais> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM pais"
            );
            executarQuerySQL();
            List<Pais> listaPaises = new ArrayList<>();

            while (getRs().next()) {
                listaPaises.add(
                        new Pais(
                                getRs().getInt("id"),
                                getRs().getString("nome"),
                                getRs().getString("isp"),
                                getRs().getInt("ddi")
                        )
                );
            }

            return listaPaises;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public int contarPaises() throws Exception {
        try {

            prepararSQL(
                    "SELECT COUNT(*) FROM pais"
            );
            executarQuerySQL();
            return (getRs().next()) ? getRs().getInt(1) : 0;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

    }

    public String procurarPorIniciais(String iniciais) throws Exception {

        try {
            prepararSQL(
                    "SELECT nome FROM pais where nome LIKE '^?'"
            );
            getPs().setString(1,iniciais);
            executarQuerySQL();
            return (getRs().next()) ? getRs().getString(1): null;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }

    }


}
