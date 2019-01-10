package dao;


import model.Cidade;
import model.Estado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDao extends ModelDao {

    public CidadeDao() throws Exception{

    }

    public void inserirCidade(Cidade cidade) throws Exception {
        try {
            prepararSQL(
                "INSERT INTO cidade VALUES (DEFAULT, ?, ?)"
            );
            getPrepararInstrucao().setString(1, cidade.getNome());
            getPrepararInstrucao().setInt(2, cidade.getEstado().getId());
            executarInstrucao();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }

    public void alterarCidade(Cidade cidade) throws Exception {
        try {
            prepararSQL(
                    "UPDATE cidade SET nome = ?, Estado_id = ? WHERE id = ?"
            );
            getPrepararInstrucao().setInt(1,cidade.getId());
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }

    public void deletarCidade(int id) throws Exception {
        try {
            prepararSQL(
                    "DELETE FROM cidade WHERE id = ?"
            );
            getPrepararInstrucao().setInt(1, id);
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }

    public Cidade pesquisarUm(int id) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM cidade WHERE id = ?"
            );
            getPrepararInstrucao().setInt(1, id);
            consultarBanco();
            Cidade cidade = new Cidade();
            while (getResultados().next()) {
                cidade.setId(getResultados().getInt(1));
                cidade.setNome(getResultados().getString(2));
                cidade.getEstado().setId(getResultados().getInt(3));
            }
            return cidade;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public List<Cidade> pesquisarTodos() throws Exception{
        try {
            prepararSQL(
                "SELECT * from cidade"
            );

            consultarBanco();

            List<Cidade> cidades = new ArrayList<>();


            while (getResultados().next()) {
                cidades.add(
                        new Cidade(
                                getResultados().getInt(1),
                                getResultados().getString(2),
                                new Estado(getResultados().getInt(3))
                        )
                );
            }
            return cidades;

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public int contarQuantidadeCidade() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM cidade"
            );

            consultarBanco();

            while (getResultados().next()) {
                return getResultados().getInt(1);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

        return 0;
    };



}
