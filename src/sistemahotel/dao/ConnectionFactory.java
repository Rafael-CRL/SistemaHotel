package sistemahotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conn;
    private static final String URL = "jdbc:mysql://turntable.proxy.rlwy.net:58281/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "agWtwlhBYNvbBAUWwcRMioXReQzvQRlS";

    public static Connection getConexao() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao conectar ao banco.");
            e.printStackTrace();
            return null;
        }
        return conn;
        }
}
