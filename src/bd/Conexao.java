package bd;

import java.io.IOException;
import java.sql.*;

public class Conexao {

    public static Connection getConnection() throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return
                DriverManager.
                    getConnection(
                        "jdbc:mysql://localhost/consultoria?useTimezone=true&serverTimezone=UTC",
                        "developer",
                        "admin"
                    );

        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        Connection conn = Conexao.getConnection();

        Runnable r = () -> {
            System.out.println("Contagem regressiva");
            for (int i = 10; i > 0; i--) {
                try {
                    System.out.println("SGDB Aberto");
                    System.out.println("Faltam " + i + " para fechar o DATABASE");
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };

        r.run();

        System.out.println(conn.isClosed() ? "SGDB Aberto" : "SGDB Fechado");


    }

}
