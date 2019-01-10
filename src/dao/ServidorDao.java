package dao;

import model.Servidor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServidorDao extends ModelDao {

    public ServidorDao() throws Exception {

    }

    public List<Servidor> pesquisarTodos() throws Exception {
        try {
            prepararSQL(
                    "SELECT * FROM servidor"
            );
            consultarBanco();

            List<Servidor> servidores = new ArrayList<>();

            while (getResultados().next()) {
                servidores.add(
                        new Servidor(
                                getResultados().getInt(1),
                                getResultados().getString(2)
                        )
                );
            };

            return servidores;

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        }
    }

    public int contarQuantidadeServidores() throws Exception {
        try {

            prepararSQL(
                    "SELECT COUNT(*) FROM servidor"
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
