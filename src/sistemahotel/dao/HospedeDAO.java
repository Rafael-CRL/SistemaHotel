/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sistemahotel.model.Hospede;

public class HospedeDAO {
    private Connection conn;
    
    public HospedeDAO(){
        this.conn = ConnectionFactory.getConexao();    
    }
    public boolean cadastrarHospede(Hospede hospede) {
    String sql = "INSERT INTO hospedes (nome, cpf) VALUES (?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, hospede.getNome());
        stmt.setString(2, hospede.getCpf());
        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
      public List<Hospede> listarHospedes() {
        List<Hospede> hospedes = new ArrayList<>();
        String sql = "SELECT * FROM hospedes";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Hospede h = new Hospede(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf")
                );
                hospedes.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hospedes;
    }
}

