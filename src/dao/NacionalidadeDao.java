package dao;

import model.Nacionalidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NacionalidadeDao extends ModelDao{
    public NacionalidadeDao() throws Exception {
    }

    public void inserirNacionalidade(Nacionalidade nacionalidade) throws Exception {
        try {
            if (existeNacionalidade(nacionalidade.getNacionalidade())) {
                alterarNacionalidade(nacionalidade);
            } else {
                throw new Exception("O registro de nacionalidade já existe!");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public void alterarNacionalidade(Nacionalidade nacionalidade) throws Exception {
        try {
            prepararSQL(
                    "UPDATE pais SET nacionalidade = ? WHERE id = ?"
            );
            getPs().setString(1, nacionalidade.getNacionalidade());
            getPs().setInt(2, nacionalidade.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public Nacionalidade pesquisarUm(Nacionalidade nacionalidade) throws Exception {
        try {
            prepararSQL(
                    "SELECT nome, nacionalidade FROM pais WHERE id = ?"
            );
            getPs().setInt(1, nacionalidade.getId());
            executarQuerySQL();
            if (getRs().next()) {
                nacionalidade.setPais(getRs().getString(1));
                nacionalidade.setNacionalidade(getRs().getString(2));
            } else {
                throw new Exception("Erro ao pesquisar registro");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return nacionalidade;
    }

    public List<Nacionalidade> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT id, nome, nacionalidade FROM pais WHERE nacionalidade IS NOT NULL"
            );
            executarQuerySQL();
            List<Nacionalidade> nacionalidades = new ArrayList<>();
            while (getRs().next()) {
                nacionalidades.add(
                        new Nacionalidade(
                                getRs().getInt(1),
                                getRs().getString(2),
                                getRs().getString(3)
                        )
                );
            }

            return nacionalidades;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public int contarTotalNacionalidades() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(nacionalidade) As total FROM pais"
            );
            executarQuerySQL();
            if (getRs().next()) {
                return getRs().getInt(1);
            } else {
                throw new Exception("Erro tentar obter o total de registros de nacionalidades");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public Nacionalidade pesquisarPorNomePais(Nacionalidade nacionalidade) throws Exception {
        try {
            prepararSQL(
                    "SELECT id, nacionalidade FROM pais WHERE nome = ?"
            );
            getPs().setString(1, nacionalidade.getPais());
            executarQuerySQL();
            while ( getRs().next() ){
                nacionalidade.setId(getRs().getInt(1));
                nacionalidade.setNacionalidade(getRs().getString(2));
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return nacionalidade;
    }

    public boolean existeNacionalidade(String nacionalidade) throws Exception {
        try {
            prepararSQL("SELECT * FROM pais WHERE nacionalidade = ?");
            getPs().setString(1, nacionalidade);
            executarQuerySQL();
            if(getRs().next()) {
                return getRs().getBoolean(1);
            } else {
                throw new Exception("Erro ao pesquisar por " + nacionalidade);
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }
}
