package dao;

import model.Religiao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReligiaoDao extends ModelDao {

    public ReligiaoDao() throws Exception {
    }

    public void inseirReligiao(Religiao religiao) throws Exception {

    }

    public void alterarReligao(Religiao religiao) throws Exception {

    }

    public void deletarReligiao(Religiao religiao) throws Exception {

    }

    public List<Religiao> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM religiao"
            );

            consultarBanco();

            List<Religiao> religiaos = new ArrayList<>();

            while (getResultados().next()) {
                religiaos.add(
                        new Religiao(
                            getResultados().getInt(1),
                            getResultados().getString(2)
                        )
                );
            }

            return religiaos;

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }
    }

    public int contarQuantidadeReligioes() throws Exception {
        try {
            prepararSQL(
                    "SELECT COUNT(*) FROM religiao"
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
