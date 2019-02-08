package dao;


import model.Cidade;
import model.Estado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDao extends ModelDao {

    public CidadeDao() throws Exception{

    }

    public int inserirCidade(Cidade cidade) throws Exception {
        try {
            prepararSQL(
                "INSERT INTO cidade VALUES (DEFAULT, ?, ?)"
            );
            getPs().setString(1, cidade.getNome());
            getPs().setInt(2, cidade.getEstado().getId());
            if(executarSQL()){
                return mostrarIdGerado();
            } else {
                throw new Exception("Erro ao tentar inserir cidade");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    public void alterarCidade(Cidade cidade) throws Exception {
        try {
            prepararSQL(
                    "UPDATE cidade SET nome = ?, Estado_id = ? WHERE id = ?"
            );
            getPs().setInt(1,cidade.getId());
            if(! executarSQL() ){
                throw new Exception("Erro tentar atualizar cidade");
            };
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
        }
    }

    public void deletarCidade(int id) throws Exception {
        try {
            prepararSQL(
                    "DELETE FROM cidade WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarSQL();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
        }
    }

    public Cidade pesquisarPorId(int id) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM cidade WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            Cidade cidade = new Cidade();
            while (getRs().next()) {
                cidade.setId(getRs().getInt(1));
                cidade.setNome(getRs().getString(2));
                cidade.getEstado().setId(getRs().getInt(3));
            }
            return cidade;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public Cidade pesquisarPorNome(String nome) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM cidade WHERE nome = ?"
            );
            getPs().setString(1, nome);
            executarQuerySQL();
            Cidade cidade = new Cidade();
            while (getRs().next()) {
                cidade.setId(getRs().getInt(1));
                cidade.setNome(getRs().getString(2));
                cidade.getEstado().setId(getRs().getInt(3));
            }
            return cidade;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public List<Cidade> pesquisarTodos() throws Exception{
        try {
            prepararSQL(
                "SELECT * from cidade"
            );
            executarQuerySQL();
            List<Cidade> cidades = new ArrayList<>();
            while (getRs().next()) {
                cidades.add(
                        new Cidade(
                                getRs().getInt(1),
                                getRs().getString(2),
                                new Estado(
                                        getRs().getInt(3),
                                        null,
                                        null,
                                        null
                                )
                        )
                );
            }
            return cidades;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    public int contarQuantidadeCidade() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM cidade"
            );
            executarQuerySQL();
            while (getRs().next()) {
                return getRs().getInt(1);
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return 0;
    }

}
