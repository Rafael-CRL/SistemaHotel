/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemahotel.model.Usuario;

/**
 *
 * @author rafael
 */
public class UsuarioDAO {
    private Connection conn;
    
    public UsuarioDAO(){
        this.conn = ConnectionFactory.getConexao();
    }
    
    public Usuario buscarPorCPF(String cpf){
        //aqui eu to buscando o usuário pelo cpf no banco de dados
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setSenha(rs.getString("senha")); // Pega a senha (que no futuro será criptografada)
                    usuario.setCargo(rs.getString("cargo"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por CPF.");
            e.printStackTrace();
        }
        return null; 
    }
}
