package sistemahotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Credenciais vêm de variáveis de ambiente: HOTEL_DB_URL, HOTEL_DB_USER, HOTEL_DB_PASSWORD
    private static final String URL = System.getenv("HOTEL_DB_URL");
    private static final String USER = System.getenv("HOTEL_DB_USER");
    private static final String PASSWORD = System.getenv("HOTEL_DB_PASSWORD");

    private static Connection conn;

    public static Connection getConexao() {
        if (URL == null || USER == null || PASSWORD == null) {
            System.err.println("ERRO: defina HOTEL_DB_URL, HOTEL_DB_USER e HOTEL_DB_PASSWORD.");
            return null;
        }
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("ERRO: falha ao conectar ao banco de dados.");
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    static ConnectionFactory getInstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
