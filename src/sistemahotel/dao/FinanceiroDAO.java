/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sistemahotel.model.Funcionario;
import sistemahotel.model.Hospede;
import sistemahotel.model.Reserva;
import sistemahotel.model.Transacao;

/**
 * DAO para a entidade Transacao. Lida com a persistência e recuperação de
 * transações financeiras.
 */
public class FinanceiroDAO {

    private Connection conn;

    public FinanceiroDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    /**
     * Insere uma nova transação no banco, extraindo os IDs dos objetos.
     */
    public void inserirTransacao(Transacao transacao) throws SQLException {
        // SQL alinhado com a nova estrutura da tabela 'transacoes'
        String sql = "INSERT INTO transacoes (tipo, categoria, valor, descricao, data_transacao, forma_pagamento, id_reserva, id_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transacao.getTipo());
            stmt.setString(2, transacao.getCategoria());
            stmt.setBigDecimal(3, transacao.getValor());
            stmt.setString(4, transacao.getDescricao());
            stmt.setTimestamp(5, Timestamp.valueOf(transacao.getDataTransacao()));
            stmt.setString(6, transacao.getFormaPagamento());

            // Lógica para salvar as chaves estrangeiras, permitindo valores nulos
            if (transacao.getReserva() != null) {
                stmt.setInt(7, transacao.getReserva().getId());
            } else {
                stmt.setNull(7, Types.INTEGER);
            }

            if (transacao.getFuncionario() != null) {
                stmt.setInt(8, transacao.getFuncionario().getId());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }
            stmt.executeUpdate();
        }
    }

    /**
     * Busca todas as transações e popula os objetos relacionados.
     */
    public List<Transacao> buscarTodasTransacoes() throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        // Query ajustada para fazer JOIN com reservas e obter o nome do hóspede principal.
        String sql = """
            SELECT t.*, f.nome AS nome_funcionario, h.nome AS nome_hospede
            FROM transacoes t
            LEFT JOIN funcionarios f ON t.id_funcionario = f.id
            LEFT JOIN reservas res ON t.id_reserva = res.id
            LEFT JOIN hospedes h ON res.id_hospede = h.id 
            ORDER BY t.data_transacao DESC
        """;

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                transacoes.add(mapRowToTransacao(rs));
            }
        }
        return transacoes;
    }

    /**
     * Busca transações com filtros dinâmicos.
     */
    public List<Transacao> buscarTransacoesFiltradas(LocalDate dataInicio, LocalDate dataFim, String tipo, String categoria) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT t.*, f.nome AS nome_funcionario, h.nome AS nome_hospede
            FROM transacoes t
            LEFT JOIN funcionarios f ON t.id_funcionario = f.id
            LEFT JOIN reservas res ON t.id_reserva = res.id
            LEFT JOIN hospedes h ON res.id_hospede = h.id 
            WHERE 1=1
        """);

        List<Object> parametros = new ArrayList<>();
        if (dataInicio != null) {
            sql.append(" AND DATE(t.data_transacao) >= ?");
            parametros.add(Date.valueOf(dataInicio));
        }
        if (dataFim != null) {
            sql.append(" AND DATE(t.data_transacao) <= ?");
            parametros.add(Date.valueOf(dataFim));
        }
        if (tipo != null && !"Todos".equalsIgnoreCase(tipo)) {
            sql.append(" AND t.tipo = ?");
            parametros.add(tipo);
        }
        if (categoria != null && !"Todas".equalsIgnoreCase(categoria)) {
            sql.append(" AND t.categoria = ?");
            parametros.add(categoria);
        }
        sql.append(" ORDER BY t.data_transacao DESC");

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transacoes.add(mapRowToTransacao(rs));
                }
            }
        }
        return transacoes;
    }

    /**
     * Método auxiliar para mapear uma linha do ResultSet para um objeto
     * Transacao.
     */
    private Transacao mapRowToTransacao(ResultSet rs) throws SQLException {
        Transacao t = new Transacao();
        t.setId(rs.getInt("id"));
        t.setTipo(rs.getString("tipo"));
        t.setValor(rs.getBigDecimal("valor"));
        t.setDataTransacao(rs.getTimestamp("data_transacao").toLocalDateTime());
        t.setFormaPagamento(rs.getString("forma_pagamento"));
        t.setCategoria(rs.getString("categoria"));
        t.setDescricao(rs.getString("descricao"));

        // Popula funcionário
        int idFuncionario = rs.getInt("id_funcionario");
        if (idFuncionario > 0) {
            Funcionario f = new Funcionario();
            f.setId(idFuncionario);
            f.setNome(rs.getString("nome_funcionario"));
            t.setFuncionario(f);
        }

        // Popula reserva com hóspede principal
        int idReserva = rs.getInt("id_reserva");
        if (idReserva > 0) {
            Hospede h = new Hospede();
            h.setNome(rs.getString("nome_hospede"));

            Reserva r = new Reserva();
            r.setId(idReserva);
            r.getHospedes().add(h);
            t.setReserva(r);
        }

        return t;
    }
}
