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
                "INSERT INTO pais(nome, isp, ddd, nacionalidade) VALUES (?, ?, ?, ?)"
            );
            getPrepararInstrucao().setString(1, pais.getNome());
            getPrepararInstrucao().setString(2, pais.getISP());
            getPrepararInstrucao().setInt(3,pais.getDDI());
            getPrepararInstrucao().setString(4,pais.getNacionalidade());
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public void alterarPais(Pais pais) throws Exception {

        try{
            prepararSQL(
                "UPDATE pais SET nome = ?, isp = ?, ddd = ?, nacionalidade = ? WHERE id = ?"
            );
            getPrepararInstrucao().setString(1, pais.getNome());
            getPrepararInstrucao().setString(2, pais.getISP());
            getPrepararInstrucao().setInt(3,pais.getDDI());
            getPrepararInstrucao().setString(4, pais.getNacionalidade());
            getPrepararInstrucao().setInt(5, pais.getId());
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }

    public void deletarDados(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM pais WHERE id = ?"
            );
            getPrepararInstrucao().setInt(1 , id);
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }

    public int idPorNacionalidade(Pais pais) throws Exception {

        try {
            prepararSQL(
                "SELECT id FROM pais WHERE nacionalidade = ?"
            );
            getPrepararInstrucao().setString(1, pais.getNacionalidade());
            consultarBanco();
            int id = 0;
            while (getResultados().next()) {
                id = getResultados().getInt(1);
            }
            return id;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }

    public List<Pais> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM pais"
            );
            consultarBanco();
            List<Pais> listaPaises = new ArrayList<>();

            while (getResultados().next()) {
                listaPaises.add(
                        new Pais(
                                getResultados().getInt("id"),
                                getResultados().getString("nome"),
                                getResultados().getString("isp"),
                                getResultados().getInt("ddi"),
                                getResultados().getString("nacionalidade")
                        )
                );
            }

            return listaPaises;

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }
    }

    public int contarPaises() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM pais"
            );
            consultarBanco();
            if(getResultados().next()) {
                return getResultados().getInt(1);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return 0;
    }

    public String procurarPorIniciais(String iniciais) throws Exception {

        try {
            prepararSQL(
                    "SELECT nome FROM pais where nome LIKE '^?'"
            );
            getPrepararInstrucao().setString(1,iniciais);
            consultarBanco();
            System.out.println(getPrepararInstrucao().executeQuery().getStatement());
            String nome = "";

            while (getResultados().next()) {
                nome = getResultados().getString(1);
            }
            return nome;
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
            getResultados().close();
        }

    }



}
