package bd;

import java.sql.*;

public class Conexao {

    public static Connection getConnection() throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return
                DriverManager.
                    getConnection(
                        "jdbc:mysql://localhost/consultoria?useTimezone=true&serverTimezone=UTC",
                        "root",
                        "root"
                    );

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println(Conexao.getConnection().isClosed());
    }

}
