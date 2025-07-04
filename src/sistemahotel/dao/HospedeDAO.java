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

    public void cadastrarHospede(Hospede hospede) throws SQLException {
        String sql = "INSERT INTO hospedes (nome, cpf) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hospede.getNome());
            stmt.setString(2, hospede.getCpf());
            stmt.executeUpdate();
        }
    }

    public List<Hospede> listarHospedes() throws SQLException {
        List<Hospede> hospedes = new ArrayList<>();
        String sql = "SELECT * FROM hospedes";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                hospedes.add(mapRowToHospede(rs));
            }
        }
        return hospedes;
    }
    
    public Hospede buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM hospedes WHERE cpf = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, cpf);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return mapRowToHospede(rs);
                }
            }
        }
        return null;
    }
    

    public Hospede buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM hospedes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToHospede(rs);
                }
            }
        }
        return null;
    }

    // MÉTODO AUXILIAR ADICIONADO PARA REUTILIZAÇÃO DE CÓDIGO
    private Hospede mapRowToHospede(ResultSet rs) throws SQLException {
        Hospede h = new Hospede();
        h.setId(rs.getInt("id"));
        h.setNome(rs.getString("nome"));
        h.setCpf(rs.getString("cpf"));
        // No futuro, outros campos de Pessoa (email, telefone) seriam setados aqui
        return h;
    }
}