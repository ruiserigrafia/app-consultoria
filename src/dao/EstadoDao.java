package dao;

import model.Estado;
import model.Pais;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDao extends ModelDao {

    public EstadoDao() throws Exception{

    }

    public int inserirEstado(Estado estado) throws Exception {
        try {
            prepararSQL(
                    "INSERT INTO estado VALUES(DEFAULT, ?, ?, ?)"
            );
            getPs().setString(1,estado.getNome());
            getPs().setString(2,estado.getUf());
            getPs().setString(3,estado.getPais().getNome());
            if(executarSQL()){
                return mostrarIdGerado();
            } else {
                throw new Exception("Erro ao tentar inserir estado");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public void alterarEstado(Estado estado) throws Exception {
        try {
            prepararSQL(
                    "UPDATE estado SET nome = ?, uf = ?, Pais_id = ? WHERE id = ?"
            );
            getPs().setString(1, estado.getNome());
            getPs().setString(2, estado.getUf());
            getPs().setInt(3, estado.getPais().getId());
            getPs().setInt(4,estado.getId());
            if( ! executarSQL() ){
                throw new Exception("Erro ao tentar alterar estado");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public void deletarEstado(int id) throws Exception {
        try {
            prepararSQL(
                    "DELETE FROM usuario WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarSQL();
        } catch(SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public Estado pesquisarUm(int id) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM estado WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while (getRs().next()) {
                return new Estado(
                    getRs().getInt(1),
                    getRs().getString(2),
                    getRs().getString(3),
                    new Pais(
                        getRs().getInt(5),
                            null,
                            null,
                            0
                    )
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

    public List<Estado> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM estado"
            );
            executarQuerySQL();
            List<Estado> listaEstados = new ArrayList<>();
            while ( getRs().next()) {
                listaEstados.add(
                        new Estado(
                                getRs().getInt(1),
                                getRs().getString(2),
                                getRs().getString(3),
                                new Pais(
                                        getRs().getInt(5),
                                        null,
                                        null,
                                        0
                                )
                        )
                );
            }
            return listaEstados;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public Estado pesquisarPorNome(String nome) throws Exception {
        return pesquisarString("SELECT * FROM estado WHERE nome = ?", nome);
    }

    public Estado pesquisarPorUF(String uf) throws Exception {
        return pesquisarString("SELECT * FROM estado WHERE uf = ?", uf);
    }

    public Estado pesquisarPorNaturalidade(String naturalidade) throws Exception {
       return pesquisarString("SELECT * FROM estado WHERE naturalidade = ?", naturalidade);
    }

    public int contarQuantidadeEstado() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM estado"
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

    public boolean existeNomeEstado (String nome) throws Exception{
        try{
            prepararSQL(
                "SELECT * FROM estado WHERE nome = ?"
            );
            getPs().setString(1,nome);
            executarQuerySQL();

            return (getRs().next()) ? true: false;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public Estado pesquisarString(String sql, String string) throws Exception {
        try {
            prepararSQL(sql);
            getPs().setString(1, string);
            executarQuerySQL();
            while (getRs().next()) {
                return new Estado(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        new Pais(
                                getRs().getInt(5),
                                null,
                                null,
                                0
                        )
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

}
