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
            getPrepararInstrucao().setString(1,etnia.getDefinicao());
            executarInstrucao();

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        } finally {
            getPrepararInstrucao().close();
        }
    }

    public void alterarEtnia(Etnia etnia) throws Exception {
        try{
            prepararSQL(
                    "UPDATE etnia SET = ? WHERE id = ?"
            );
            getPrepararInstrucao().setString(1,etnia.getDefinicao());
            getPrepararInstrucao().setInt(2,etnia.getId());
            executarInstrucao();
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public void deletarEtnia(Etnia etnia) throws Exception {
        try{

            prepararSQL(
                    "DELETE FROM etnia WHERE id = ?"
            );
            getPrepararInstrucao().setInt(1, etnia.getId());
            executarInstrucao();

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public List<Etnia> pesquisarTodos() throws Exception {
        try {
            prepararSQL("SELECT * FROM etnia");
            consultarBanco();

            List<Etnia> etnias = new ArrayList<>();

            while (getResultados().next() ) {

                etnias.add(
                        new Etnia(
                                getResultados().getInt(1),
                                getResultados().getString(2)
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

            consultarBanco();

            while (getResultados().next()) {
                return getResultados().getInt(1);
            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
        return 0;

    }


}
