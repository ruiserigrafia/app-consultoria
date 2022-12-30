package dao;

import model.Celular;
import model.Telefone;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CelularDao extends TelefoneDao{

    public CelularDao() throws Exception {
    }

    @Override
    public int inserirTelefone(Telefone telefone) throws Exception {

        try {
            Celular celular = (Celular) telefone;
            prepararSQL(
                    "INSERT Celular_tb VALUES(default, ?, ?)"
            );
            getPs().setInt(1, celular.getNumero());
            getPs().setBoolean(2, celular.isWhatsapp());
            if(executarSQL()){
                return mostrarIdGerado();
            }else{
                throw new Exception("Erro tentar inserir celular");
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
            Celular celular = (Celular) telefone;
            prepararSQL(
                    "UPDATE Celular_tb SET numero = ?, zap = ? where id = ?"
            );
            getPs().setInt(1, celular.getNumero());
            getPs().setBoolean(2, celular.isWhatsapp());
            getPs().setInt(3, celular.getId());
            if( ! executarSQL() ) {
                throw new Exception("Erro ao tentar atualizar Celular");
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
        }

    }

    @Override
    public void excluirTelefone(int id) throws Exception {

        try {
            prepararSQL(
                    "DELETE FROM Celular_tb WHERE id = ?"
            );
            getPs().setInt(1, id);
            if( ! executarSQL() ){
                throw new Exception("Erro ao tentar excluir Celular");
            }
        } catch (SQLException sqle) {
            getPs().close();
        }

    }

    @Override
    public Telefone pesquisarPorId(int id) throws Exception {

        try {

            prepararSQL(
                    "SELECT * FROM Celular_tb WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarQuerySQL();
            while (getRs().next()) {
                return new Celular(
                        getRs().getInt(1),
                        getRs().getInt(2),
                        getRs().getBoolean(3)
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

    @Override
    public Telefone pesquisarPorNumero(int numero) throws Exception {

        try {
            prepararSQL(
                    "SELECT * FROM Celular_tb WHERE numero = ?"
            );
            getPs().setInt(1, numero);
            executarQuerySQL();
            while (getRs().next()) {
                return new Celular(
                        getRs().getInt(1),
                        getRs().getInt(2),
                        getRs().getBoolean(3)
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

    @Override
    public List pesquisarTodos() throws Exception {

        List<Celular> listaCelular = new ArrayList<>();
        try {
            prepararSQL(
                    "SELECT * FROM Celular"
            );
            executarQuerySQL();
            while (getRs().next()) {
                listaCelular.add(
                        new Celular(
                            getRs().getInt(1),
                            getRs().getInt(2),
                            getRs().getBoolean(3)
                        )
                );
            }
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            getPs().close();
            getRs().close();
        }
        return listaCelular;
    }
}
