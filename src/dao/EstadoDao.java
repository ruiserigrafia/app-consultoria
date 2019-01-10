package dao;

import model.Estado;
import model.Pais;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDao extends ModelDao {

    public EstadoDao() throws Exception{

    }

    public void inserirEstado(Estado estado) throws Exception {
        try {
            prepararSQL(
                    "INSERT INTO estado(id, nome, uf, naturalidade, Pais_id) VALUES(DEFAULT ,?,?,?,?)"
            );

            getPrepararInstrucao().setString(1,estado.getNome());
            getPrepararInstrucao().setString(2,estado.getUf());
            getPrepararInstrucao().setString(3,estado.getNaturalidade());
            getPrepararInstrucao().setString(4,estado.getPais().getNome());
            getPrepararInstrucao().execute();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }

    public void alterarEstado(Estado estado) throws Exception {
        try {
            prepararSQL(
                    "UPDATE estado SET nome = ?, uf = ?, naturalidade = ?, Pais_id = ? WHERE id = ?"
            );

            getPrepararInstrucao().setString(1, estado.getNome());
            getPrepararInstrucao().setString(2, estado.getUf());
            getPrepararInstrucao().setString(3, estado.getNaturalidade());
            getPrepararInstrucao().setInt(4, estado.getPais().getId());
            getPrepararInstrucao().setInt(5,estado.getId());
            getPrepararInstrucao().execute();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }


    public void deletarEstado(int id) throws Exception {
        try {
            prepararSQL(
                    "DELETE FROM usuario where id = " + id
            );
            getPrepararInstrucao().execute();
        } catch(SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }

    public Object pesquisarID(int id) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM estado WHERE estado.id = ?"
            );
            getPrepararInstrucao().setInt(1, id);
            consultarBanco();
            Estado estado = new Estado();
            while (getResultados().next()) {
                estado.setId(getResultados().getInt(1));
                estado.setNome(getResultados().getString(2));
                estado.setUf(getResultados().getString(3));
                estado.getPais().setId(getResultados().getInt(4));
            }
            return  estado;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public List<Estado> pesquisarTodos() throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM estado"
            );
            consultarBanco();

            List<Estado> listaEstados = new ArrayList<>();



            while ( getResultados().next()) {

                listaEstados.add(
                        new Estado(
                                getResultados().getInt(1),
                                getResultados().getString(2),
                                getResultados().getString(3),
                                getResultados().getString(4),
                                new Pais(getResultados().getInt(5))
                        )

                );

            }

            return listaEstados;

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        }
    }


    public int contarQuantidadeEstado() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM estado"
            );

            consultarBanco();

            if(getResultados().next()) {
                return getResultados().getInt(1);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

        return 0;

    }


    public boolean existeNomeEstado (String nome) throws Exception{

        try{
            prepararSQL(
                "SELECT * FROM estado WHERE nome=?"
            );
            getPrepararInstrucao().setString(1,nome);
            consultarBanco();
            boolean existe = false;
            if(getResultados().next()) {
                existe = true;
            }
            return existe;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        }

    }



}
