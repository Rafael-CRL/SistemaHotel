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

        stmt.executeUpdate();
        System.out.println("Transação registrada com sucesso");
        stmt.close();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao registrar transação: " + e);
    }
}

    
    //metodo para buscar transção por data efetuada no banco de dados
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
        
        String sql = "SELEC tipo, valor FROM transacoes WHERE dataTransacao = ?";
        
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
