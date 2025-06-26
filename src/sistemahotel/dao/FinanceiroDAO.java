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

<<<<<<< Updated upstream
    try {
        Connection conn = ConnectionFactory.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, transacao.getId_reservas());
        stmt.setDouble(2, transacao.getValor());
        stmt.setString(3, transacao.getTipo());
        stmt.setDate(4, new java.sql.Date(transacao.getDataTransacao().getTime()));
        stmt.setString(5, transacao.getDescricao());

        stmt.executeUpdate();
        System.out.println("Transação registrada com sucesso");
        stmt.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao registrar transação: " + e);
=======
    public FinanceiroDAO() {
        this.conn = ConnectionFactory.getConexao();
>>>>>>> Stashed changes
    }

<<<<<<< Updated upstream
    
    //metodo para buscar transção por data efetuada no banco de dados
    public List<Financeiro> buscarTransacoesDoDia(Date date){
        List<Financeiro> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE dataTransacao = ?";
        
        
        try {
            Connection conn = ConnectionFactory.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(date.getTime()));
=======
    public void registrarPagamento(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacoes (id_reserva, valor, tipo, "
                + "data_transacao, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transacao.getIdReserva());
            stmt.setBigDecimal(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            stmt.setTimestamp(4, Timestamp.valueOf(transacao.getDataTransacao()));
            stmt.setString(5, transacao.getStatus());
            
            stmt.executeUpdate();
        }
    }

    public List<Transacao> buscarTransacoesPorData(LocalDate data) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE DATE(data_transacao) = ?";
        
        try (
                PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
                stmt.setDate(1, Date.valueOf(data));
>>>>>>> Stashed changes
            
            try (
                    ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Transacao t = new Transacao();
                    t.setId(rs.getInt("id"));
                    t.setIdReserva(rs.getInt("id_reserva"));
                    t.setValor(rs.getBigDecimal("valor"));
                    t.setTipo(rs.getString("tipo"));
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
        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
            try (
                    ResultSet rs = stmt.executeQuery()) {
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
<<<<<<< Updated upstream
          }
            
           rs.close();
           stmt.close();
           conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao calcular o saldo: " + e);
      }
        return entradas - saida;
    }
    
    public String emitirRecibo(Financeiro transacao) {
    StringBuilder recibo = new StringBuilder();
    recibo.append("------ RECIBO DE PAGAMENTO ------\n");
    recibo.append("ID da Transação: ").append(transacao.getId()).append("\n");
    recibo.append("ID da Reserva: ").append(transacao.getId_reservas()).append("\n");
    recibo.append("Tipo: ").append(transacao.getTipo()).append("\n");
    recibo.append(String.format("Valor: R$ %.2f\n", transacao.getValor()));

    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
    recibo.append("Data: ").append(sdf.format(transacao.getDataTransacao())).append("\n");

    recibo.append("----------------------------------\n");

    // Mostrar no console
    System.out.println(recibo.toString());

    // Salvar em arquivo .txt
    try {
        // Cria pasta "recibos" se não existir
        java.io.File pasta = new java.io.File("recibos");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        // Define caminho do arquivo
        String caminho = "recibos/recibo_" + transacao.getId() + ".txt";

        // Escreve o arquivo
        java.io.FileWriter writer = new java.io.FileWriter(caminho);
        writer.write(recibo.toString());
        writer.close();

        System.out.println("Recibo salvo em: " + caminho);

    } catch (Exception e) {
        System.out.println("Erro ao salvar recibo: " + e.getMessage());
    }

    return recibo.toString();
}

}
=======
        }
        return null;
    }
}
>>>>>>> Stashed changes
