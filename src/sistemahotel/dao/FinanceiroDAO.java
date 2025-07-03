package sistemahotel.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import sistemahotel.model.Transacao;

public class FinanceiroDAO {

    private Connection conn;

    public FinanceiroDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    //Inserir nova transação
    public void inserirTransacao(Transacao t) throws SQLException {
        String sql = """
            INSERT INTO transacoes (tipo, valor, data_transacao, forma_pagamento, categoria, descricao, id_hospede, id_funcionario)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getTipo());
            stmt.setBigDecimal(2, t.getValor());
            stmt.setTimestamp(3, Timestamp.valueOf(t.getDataTransacao()));
            stmt.setString(4, t.getFormaPagamento());
            stmt.setString(5, t.getCategoria());
            stmt.setString(6, t.getDescricao());
            stmt.setObject(7, t.getIdHospede()); 
            stmt.setObject(8, t.getIdFuncionario()); 
            stmt.executeUpdate();
        }
    }

    // Buscar todas as transações com nome da pessoa relacionada
    public List<Transacao> buscarTodasTransacoes() throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();

        String sql = """
            SELECT t.id,
                   t.tipo, 
                   t.valor, 
                   t.data_transacao, 
                   t.forma_pagamento,
                   t.categoria, 
                   t.descricao,
                   COALESCE(f.nome, h.nome) AS nome_pessoa
            FROM transacoes t
            LEFT JOIN funcionarios f ON t.id_funcionario = f.id
            LEFT JOIN hospedes h ON t.id_hospede = h.id
            ORDER BY t.data_transacao DESC
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setTipo(rs.getString("tipo"));
                t.setValor(rs.getBigDecimal("valor"));
                t.setDataTransacao(rs.getTimestamp("data_transacao").toLocalDateTime());
                t.setFormaPagamento(rs.getString("forma_pagamento"));
                t.setCategoria(rs.getString("categoria"));
                t.setDescricao(rs.getString("descricao"));
                t.setNomeRelacionado(rs.getString("nome_pessoa"));

                transacoes.add(t);
            }
        }

        return transacoes;
    }

    // Buscar transações com filtros dinâmicos
    public List<Transacao> buscarTransacoesFiltradas(LocalDate dataInicio,
            LocalDate dataFim, String tipo, String categoria) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT t.id, 
                   t.tipo, 
                   t.valor, 
                   t.data_transacao, 
                   t.forma_pagamento,
                   t.categoria, 
                   t.descricao,
                   COALESCE(f.nome, h.nome) AS nome_pessoa
            FROM transacoes t
            LEFT JOIN funcionarios f ON t.id_funcionario = f.id
            LEFT JOIN hospedes h ON t.id_hospede = h.id
            WHERE 1=1
        """);

        List<Object> parametros = new ArrayList<>();

        if (dataInicio != null) {
            sql.append(" AND DATE(t.data_transacao) >= ? ");
            parametros.add(Date.valueOf(dataInicio));
        }

        if (dataFim != null) {
            sql.append(" AND DATE(t.data_transacao) <= ? ");
            parametros.add(Date.valueOf(dataFim));
        }

        if (tipo != null && !tipo.equalsIgnoreCase("Todos")) {
            sql.append(" AND t.tipo = ? ");
            parametros.add(tipo);
        }

        if (categoria != null && !categoria.equalsIgnoreCase("Todas")) {
            sql.append(" AND t.categoria = ? ");
            parametros.add(categoria);
        }

        sql.append(" ORDER BY t.data_transacao DESC");

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setTipo(rs.getString("tipo"));
                t.setValor(rs.getBigDecimal("valor"));
                t.setDataTransacao(rs.getTimestamp("data_transacao").toLocalDateTime());
                t.setFormaPagamento(rs.getString("forma_pagamento"));
                t.setCategoria(rs.getString("categoria"));
                t.setDescricao(rs.getString("descricao"));
                t.setNomeRelacionado(rs.getString("nome_pessoa"));

                transacoes.add(t);
            }
        }

        return transacoes;
    }
}