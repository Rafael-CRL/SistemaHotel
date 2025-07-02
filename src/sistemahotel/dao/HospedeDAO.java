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
      
    public Hospede buscarPorCPF(String cpf) throws SQLException {
        // Agora busca na tabela 'hospedes' e carrega todos os campos:
        String sql = "SELECT * FROM hospedes WHERE cpf = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, cpf);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Hospede hospede = new Hospede();
                    hospede.setId(rs.getInt("id"));
                    hospede.setNome(rs.getString("nome"));
                    hospede.setCpf(rs.getString("cpf"));
                    
                    return hospede;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    } 
 
}

