package sistemahotel;

import java.sql.Connection;
import sistemahotel.dao.ConnectionFactory; // Importe a classe
import sistemahotel.view.LoginView;
import sistemahotel.view.LoginViewGUI;

public class SistemaHotel {
    public static void main(String[] args) {
        Connection testeConn = ConnectionFactory.getConexao();
        
        if (testeConn != null) {
            System.out.println("Conexão com o banco de dados do Railway bem-sucedida!");
            
        } else {
            System.out.println("Falha ao conectar com o banco de dados do Railway.");
        }
        LoginViewGUI loginView = new LoginViewGUI();
        loginView.setVisible(true);
    }
}