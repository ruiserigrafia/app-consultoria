package dao;

import model.Fixo;
import model.Telefone;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FixoDao extends TelefoneDao {
    public FixoDao() throws Exception {
    }

    @Override
    public int inserirTelefone(Telefone telefone) throws Exception {

        try {
            Fixo fixo = (Fixo) telefone;
            prepararSQL(
                    "INSERT INTO fixo VALUES(default, ?)"
            );
            getPs().setInt(1, fixo.getNumero());
            if(executarSQL()) {
                return mostrarIdGerado();
            } else {
                throw new Exception("Erro a tentar cadastrar telefone fixo.");
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }
    }

    @Override
    public void alterarTelefone(Telefone telefone) throws Exception {
        try {
            Fixo fixo = (Fixo) telefone;
            prepararSQL(
                    "UPDATE fixo SET numero = ? WHERE id = ?"
            );
            getPs().setInt(1, fixo.getNumero());
            getPs().setInt(2, fixo.getId());
            if( ! executarSQL()){
                throw new Exception("Erro ao tentar alterar telefone fixo.");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    @Override
    public void excluirTelefone(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM fixo WHERE id = ?"
            );
            getPs().setInt(1, id);
            if(! executarSQL() ) {
                throw new Exception("Erro ao tentar excluir telefone fixo.");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
    }

    @Override
    public Telefone pesquisarPorId(int id) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM fixo WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while (getRs().next()) {
                return new Fixo(
                    getRs().getInt(1),
                    getRs().getInt(2)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
       return null;
    }

    @Override
    public Telefone pesquisarPorNumero(int numero) throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM fixo WHERE numero = ?"
            );
            getPs().setInt(1, numero);
            executarQuerySQL();
            while (getRs().next()) {
                return new Fixo(
                        getRs().getInt(1),
                        getRs().getInt(2)
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return null;
    }

    @Override
    public List pesquisarTodos() throws Exception {
        List<Fixo> listaFixos = new ArrayList<>();
        try {
            prepararSQL(
                    "SELECT * FROM fixo"
            );
            executarQuerySQL();
            while (getRs().next()) {
                listaFixos.add(
                        new Fixo(
                                getRs().getInt(1),
                                getRs().getInt(2)
                       )
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return listaFixos;
    }

}
