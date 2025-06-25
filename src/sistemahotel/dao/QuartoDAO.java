/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

/**
 *
 * @author LabLa
 */
import sistemahotel.model.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuartoDAO {
    public Quarto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM quartos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Quarto q = new Quarto();
                    q.setId(rs.getInt("id"));
                    q.setNumero(rs.getString("numero"));
                    q.setTipo(rs.getString("tipo"));
                    q.setStatus(rs.getString("status"));
                    return q;
                }
            }
        }
        return null;
    }

    public void atualizar(Quarto quarto) throws SQLException {
        String sql = "UPDATE quartos SET status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quarto.getStatus());
            stmt.setInt(2, quarto.getId());
            stmt.executeUpdate();
        }
    }
}
