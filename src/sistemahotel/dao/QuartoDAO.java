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

    public void cadastrarQuarto(Quarto quarto) throws SQLException {
        Connection con = ConnectionFactory.getConexao();
        String sql = "INSERT INTO quartos (numero, tipo, status) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, quarto.getNumero());
        stmt.setString(2, quarto.getTipo());
        stmt.setString(3, quarto.getStatus());
        stmt.executeUpdate();
        con.close();
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
}
 
