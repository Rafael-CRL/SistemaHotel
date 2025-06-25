/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.sql.*;
import sistemahotel.model.Hospede;

public class HospedeDAO {

    public void cadastrarHospede(Hospede hospede) throws SQLException {
        Connection con = ConnectionFactory.getConexao();
        String sql = "INSERT INTO hospedes (nome, cpf) VALUES (?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, hospede.getNome());
        stmt.setString(2, hospede.getCpf());
        stmt.executeUpdate();
        con.close();
    }
}
