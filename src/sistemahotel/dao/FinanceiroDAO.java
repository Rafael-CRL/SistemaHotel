/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

/**
 *
 * @author Ray Carvalho
 */
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistemahotel.model.Financeiro;

public class FinanceiroDAO {
    
    
    //metodo para registrar um pagamento no banco de dados
public void registrarPagamento(Financeiro transacao) {
    String sql = "INSERT INTO transacoes (Id_reservas, valor, tipo, dataTransacao, descricao) VALUES (?, ?, ?, ?, ?)";

    try {
        Connection conn = ConnectionFactory.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, transacao.getId_reservas());
        stmt.setDouble(2, transacao.getValor());
        stmt.setString(3, transacao.getTipo());
        stmt.setDate(4, new java.sql.Date(transacao.getDataTransacao().getTime()));
        stmt.setString(5, transacao.getDescricao());
        
        JOptionPane.showMessageDialog(null, "Registro relizado com sucesso!");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao registrar transação: " + e);
    }
}

    
    //metodo para buscar transação por data efetuada no banco de dados
    public List<Financeiro> buscarTransacoesDoDia(Date date){
        List<Financeiro> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE dataTransacao = ?";
        
        try {
            Connection conn = ConnectionFactory.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Financeiro t = new Financeiro();
                t.setId(rs.getInt("id"));
                t.setId_reservas(rs.getInt("id_reservas"));
                t.setValor(rs.getDouble("valor"));
                t.setTipo(rs.getString("tipo"));
                t.setDataTransacao(rs.getDate("dataTransacao"));
                
                transacoes.add(t);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar transações: "
                    + e.getMessage());
        }
        return transacoes;
    }
    
    //metodo para calcular o saldo do dia.
    public double calcularSaldoDoDia(Date data){
        double entradas = 0;
        double saida = 0;
        
        String sql = "SELECT tipo, valor FROM transacoes WHERE dataTransacao = ?";
        
        try {
            Connection conn = ConnectionFactory.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(data.getTime()));
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String tipo = rs.getString("tipo");
                double valor = rs.getDouble("valor");
                
                if(tipo.equalsIgnoreCase("Entrada")){
                    entradas += valor;
            }else if(tipo.equalsIgnoreCase("Saida")){
                saida += valor;
            }
          }
            
           stmt.close();
           conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao calcular o saldo: " + e);
      }
        return entradas - saida;
    }

    public Financeiro buscarPorId(int id) {
    Financeiro f = null;
    String sql = "SELECT * FROM transacoes WHERE id = ?";

    try (
         Connection conn = ConnectionFactory.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            f = new Financeiro();
            f.setId(rs.getInt("id"));
            f.setId_reservas(rs.getInt("id_reservas"));
            f.setValor(rs.getDouble("valor"));
            f.setTipo(rs.getString("tipo"));
            f.setDataTransacao(rs.getDate("dataTransacao"));
            f.setDescricao(rs.getString("descricao")); // opcional
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar transação por ID: " + e.getMessage());
    }

    return f;
}
    public void imprimirRecibo(int idTransacao) {
    String sql = "SELECT * FROM transacoes WHERE id = ?";

    try (Connection conn = ConnectionFactory.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idTransacao);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String recibo = "Recibo\n"
                          + "ID: " + rs.getInt("id") + "\n"
                          + "Tipo: " + rs.getString("tipo") + "\n"
                          + "Valor: R$ " + rs.getDouble("valor") + "\n"
                          + "Data: " + rs.getDate("dataTransacao");
            
            JOptionPane.showMessageDialog(null, recibo);
        } else {
            JOptionPane.showMessageDialog(null, "Transação não encontrada.");
        }
        
        conn.close();
        stmt.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar transação: " + e.getMessage());
    }
}

}
