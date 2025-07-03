/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.sql.*;
import sistemahotel.model.Quarto;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {
    private Connection conn;
    
public QuartoDAO(){
    this.conn = ConnectionFactory.getConexao(); 
}

public boolean cadastrarQuarto(Quarto quarto) {
    String sql = "INSERT INTO quartos (numero, tipo, status, capacidade, preco_diaria) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, quarto.getNumero());
        stmt.setString(2, quarto.getTipo());
        stmt.setString(3, quarto.getStatus());
        stmt.setInt(4, quarto.getCapacidade());
        stmt.setBigDecimal(5, quarto.getPrecoDiaria());
        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


        public List<Quarto> listarQuartosDisponiveis() {
            List<Quarto> quartos = new ArrayList<>();
            String sql = "SELECT * FROM quartos WHERE status = 'Disponível'";

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Quarto q = new Quarto(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("tipo"),
                        rs.getString("status"),
                        rs.getInt("capacidade"),
                        rs.getBigDecimal("preco_diaria")
            );
            quartos.add(q);
        }

            } catch (SQLException e) {
            e.printStackTrace();
    }

            return quartos;
}
        public List<Quarto> listarTodos() {
            List<Quarto> quartos = new ArrayList<>();
            String sql = "SELECT * FROM quartos";

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Quarto q = new Quarto(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("tipo"),
                        rs.getString("status"),
                        rs.getInt("capacidade"),
                        rs.getBigDecimal("preco_diaria")
            );
            quartos.add(q);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return quartos;
}

        public Quarto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM quartos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Quarto(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("tipo"),
                        rs.getString("status"),
                        rs.getInt("capacidade"),
                        rs.getBigDecimal("preco_diaria")
                    );
                }
            }
        }
        return null;
    }

    // 2) Atualiza os dados (especialmente o status) de um quarto existente
    public void atualizarQuarto(Quarto quarto) throws SQLException {
        String sql = "UPDATE quartos SET numero = ?, tipo = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quarto.getNumero());
            stmt.setString(2, quarto.getTipo());
            stmt.setString(3, quarto.getStatus());
            stmt.setInt   (4, quarto.getId());
            stmt.executeUpdate();
        }
    }
    public void excluirQuarto(int id) throws SQLException {
        String sql = "DELETE FROM Quarto WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
}
