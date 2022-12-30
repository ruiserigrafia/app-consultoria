package dao;

import model.Pais;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDao extends ModelDao {
	
    public PaisDao() throws Exception {
    	this.setTabela("Pais_tb");
    }

    public void inserirPais(Pais pais) throws Exception {
        try {
            prepararSQL(
                "INSERT INTO" + this.getTabela() + "(nome, isp, ddi, nacionalidade) VALUES (?, ?, ?, ?)"
            );
            getPs().setString(1, pais.getNome());
            getPs().setString(2, pais.getISP());
            getPs().setInt(3, pais.getDDI());
            getPs().setString(4, pais.getNacionalidade());
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
                "UPDATE" + this.getTabela() + " SET nome = ?, isp = ?, ddd = ? WHERE id = ?"
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
    
    public void alterarNacionalidade(Pais pais) throws Exception {
    	try {
			prepararSQL("UPDATE " + this.getTabela() + "SET nacionalidade = ? WHERE id = ?" );
			getPs().setString(1, pais.getNacionalidade());
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public void deletarDados(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM" + this.getTabela() + " WHERE id = ?"
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
                    "SELECT * FROM " + this.getTabela() +" WHERE id = ?"
            );
            getPs().setInt(1, id);
            
            executarQuerySQL();
            
            while ( getRs().next()) {
            	
            	
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4),
                        getRs().getString(5)    
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
                    "SELECT * FROM " + this.getTabela() +" WHERE nome = ?"
            );
            getPs().setString(1, nome);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4),
                       getRs().getString(5)                     
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
                    "SELECT * FROM " + this.getTabela() + " WHERE isp = ?"
            );
            getPs().setString(1, isp);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4),
                        getRs().getString(5)
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
                    "SELECT * FROM " + this.getTabela() + " WHERE ddi = ?"
            );
            getPs().setInt(1, ddi);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4),
                        getRs().getString(5)   
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
                "SELECT * FROM " + this.getTabela() + " WHERE nacionalidade = ?"
            );
            getPs().setString(1, nacionalidade);
            executarQuerySQL();
            while ( getRs().next()) {
                return new Pais(
                        getRs().getInt(1),
                        getRs().getString(2),
                        getRs().getString(3),
                        getRs().getInt(4),
                        getRs().getString(5)
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
                    "SELECT * FROM " + this.getTabela()
            );
            executarQuerySQL();
            List<Pais> listaPaises = new ArrayList<>();

            while (getRs().next()) {
                listaPaises.add(
                        new Pais(
                                getRs().getInt("id"),
                                getRs().getString("nome"),
                                getRs().getString("isp"),
                                getRs().getInt("ddi"),
                                getRs().getString("nacionalidade")
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
                    "SELECT COUNT(*) FROM " + this.getTabela()
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
                    "SELECT nome FROM " + this.getTabela() + " WHERE nome LIKE '^?'"
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
