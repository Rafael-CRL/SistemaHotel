package sistemahotel;

import java.sql.Connection;
import sistemahotel.dao.ConnectionFactory; // Importe a classe

public class SistemaHotel {
    public static void main(String[] args) {
        Connection testeConn = ConnectionFactory.getConexao();
        
        if (testeConn != null) {
            System.out.println("Conexão com o banco de dados do Railway bem-sucedida!");
            // Aqui você pode fechar a conexão se desejar, mas para o Singleton não é sempre necessário
        } else {
            System.out.println("Falha ao conectar com o banco de dados do Railway.");
        }
    }
}