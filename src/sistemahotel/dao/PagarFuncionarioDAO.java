package sistemahotel.dao;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import sistemahotel.model.PagamentoFuncionario;

public class PagarFuncionarioDAO {

    public List<String> listarNomesFuncionarios() {
    List<String> nomes = new ArrayList<>();
    String sql = "SELECT nome FROM funcionarios";

    try (Connection conn = ConnectionFactory.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            nomes.add(rs.getString("nome"));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar nomes: " + e.getMessage());
    }

    return nomes;
}
    
    //Buscar dados do funcionário pelo nome
    public Optional<PagamentoFuncionario> buscarFuncionarioPorNome(String nome) {
        String sql = "SELECT id, cargo, salario FROM funcionarios WHERE nome = ?";

        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PagamentoFuncionario funcionario = new PagamentoFuncionario();
                funcionario.setNome(nome);
                funcionario.setId(rs.getInt("id"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getBigDecimal("salario"));
                return Optional.of(funcionario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar funcionário: " + e.getMessage());
        }

        return Optional.empty();
    }

    //Registrar pagamento na tabela Transacoes
    public void registrarPagamento(PagamentoFuncionario pagamento) {
    String sql = "INSERT INTO transacoes (tipo, valor, data_transacao, "
            + "forma_pagamento, categoria, descricao, id_funcionario) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = ConnectionFactory.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "Saida");
        stmt.setBigDecimal(2, pagamento.getValorPago());
        stmt.setTimestamp(3, new java.sql.Timestamp(pagamento.getDataPagamento().getTime()));
        stmt.setString(4, pagamento.getFormaDePagamento());
        stmt.setString(5, "Salário");
        stmt.setString(6, pagamento.getDescricao());
        stmt.setInt(7, pagamento.getId());

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "✅ Pagamento registrado com sucesso!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao registrar pagamento: " + e.getMessage());
    }
}
}