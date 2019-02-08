package dao;

import model.Etnia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtniaDao extends ModelDao {

    public EtniaDao() throws Exception {
    }

    public void inserirEtnia(Etnia etnia) throws Exception {
        try {

            prepararSQL(
                    "INSERT INTO Etnia(definicao) VALUE(?)"
            );
            getPs().setString(1,etnia.getDefinicao());
            executarSQL();

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            getPs().close();
        }
    }

    public void alterarEtnia(Etnia etnia) throws Exception {
        try{
            prepararSQL(
                    "UPDATE etnia SET = ? WHERE id = ?"
            );
            getPs().setString(1,etnia.getDefinicao());
            getPs().setInt(2,etnia.getId());
            executarSQL();
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void deletarEtnia(int id) throws Exception {
        try{

            prepararSQL(
                    "DELETE FROM etnia WHERE id = ?"
            );
            getPs().setInt(1, id);
            executarSQL();

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public Etnia pesquisaPorId(int id) throws Exception {
        return null;
    }

    public Etnia pesquisarPorDefinicao(String definicao) throws Exception {
        return null;
    }

    public List<Etnia> pesquisarTodos() throws Exception {
        try {
            prepararSQL("SELECT * FROM etnia");
            executarQuerySQL();

            List<Etnia> etnias = new ArrayList<>();

            while (getRs().next() ) {

                etnias.add(
                        new Etnia(
                                getRs().getInt(1),
                                getRs().getString(2)
                        )
                );

            }
            return etnias;
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int contarQuantidadeEtnia() throws Exception {

        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM etnia"
            );

            executarQuerySQL();

            while (getRs().next()) {
                return getRs().getInt(1);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return 0;

    }

}
