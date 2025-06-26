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

public boolean cadastrarQuarto(Quarto quarto) {
    String sql = "INSERT INTO quartos (numero, tipo, status) VALUES (?, ?, ?)";

    try (Connection con = ConnectionFactory.getConexao();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, quarto.getNumero());
        stmt.setString(2, quarto.getTipo());
        stmt.setString(3, quarto.getStatus());
        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<Quarto> listarQuartosDisponiveis() throws SQLException {
        List<Quarto> quartos = new ArrayList<>();
        Connection con = ConnectionFactory.getConexao();
        String sql = "SELECT * FROM quartos WHERE status = 'Disponível'";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        

        while (rs.next()) {
            Quarto q = new Quarto(
                rs.getInt("id"),
                rs.getString("numero"),
                rs.getString("tipo"),
                rs.getString("status")
            );
            quartos.add(q);
        }
        con.close();
        return quartos;
    }
        public Quarto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM quartos WHERE id = ?";
        try (Connection con = ConnectionFactory.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Quarto(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("tipo"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    // 2) Atualiza os dados (especialmente o status) de um quarto existente
    public void atualizar(Quarto quarto) throws SQLException {
        String sql = "UPDATE quartos SET numero = ?, tipo = ?, status = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, quarto.getNumero());
            stmt.setString(2, quarto.getTipo());
            stmt.setString(3, quarto.getStatus());
            stmt.setInt   (4, quarto.getId());
            stmt.executeUpdate();
        }
    }
}
