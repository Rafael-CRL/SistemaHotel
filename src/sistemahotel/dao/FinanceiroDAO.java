/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import sistemahotel.model.Transacao;

public class FinanceiroDAO {
    
    private Connection conn;

    public FinanceiroDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    public void registrarPagamento(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes (id_reserva, valor, tipo, data_transacao, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transacao.getIdReserva());
            // ALTERAÇÃO AQUI: Usar setBigDecimal para valores monetários.
            stmt.setBigDecimal(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            // ALTERAÇÃO AQUI: Usar Timestamp para salvar data e hora exata.
            stmt.setTimestamp(4, Timestamp.valueOf(transacao.getDataTransacao()));
            stmt.setString(5, transacao.getStatus());
            
            stmt.executeUpdate();
        }
    }

    public List<Transacao> buscarTransacoesPorData(LocalDate data) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE DATE(data_transacao) = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // ALTERAÇÃO AQUI: Converter o LocalDate moderno para o java.sql.Date que o JDBC espera.
            stmt.setDate(1, Date.valueOf(data));
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao t = new Transacao();
                    t.setId(rs.getInt("id"));
                    t.setIdReserva(rs.getInt("id_reserva"));
                    // ALTERAÇÃO AQUI: Usar getBigDecimal para ler valores monetários do banco.
                    t.setValor(rs.getBigDecimal("valor"));
                    t.setTipo(rs.getString("tipo"));
                    // ALTERAÇÃO AQUI: Ler um Timestamp do banco e converter para o moderno LocalDateTime.
                    t.setDataTransacao(rs.getTimestamp("data_transacao").toLocalDateTime());
                    t.setStatus(rs.getString("status"));
                    transacoes.add(t);
                }
            }
        }
        return transacoes;
    }

    public Transacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM transacoes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Transacao t = new Transacao();
                    t.setId(rs.getInt("id"));
                    t.setIdReserva(rs.getInt("id_reserva"));
                    t.setValor(rs.getBigDecimal("valor"));
                    t.setTipo(rs.getString("tipo"));
                    t.setDataTransacao(rs.getTimestamp("data_transacao").toLocalDateTime());
                    t.setStatus(rs.getString("status"));
                    return t;
                }
            }
        }
        return null;
    }
}