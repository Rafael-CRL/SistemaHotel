package sistemahotel.dao;

import sistemahotel.model.Funcionario;
import sistemahotel.model.Hospede;
import sistemahotel.model.Quarto;
import sistemahotel.model.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sistemahotel.model.Transacao;

/**
 * DAO para a entidade Reserva. Lida com a persistência e recuperação de
 * reservas, incluindo suas relações de composição com Quarto, Hóspedes e
 * Funcionário.
 */
public class ReservaDAO {

    private Connection conn;

    public ReservaDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    public void criarReserva(Reserva reserva) throws SQLException {
        // CORREÇÃO: Adicionamos a coluna 'id_hospede' ao SQL.
        String sqlReserva = "INSERT INTO reservas (id_quarto, id_hospede, id_funcionario_responsavel, data_entrada, data_saida, valor_total, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sqlReservaHospede = "INSERT INTO reserva_hospedes (id_reserva, id_hospede) VALUES (?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS)) {
                stmtReserva.setInt(1, reserva.getQuarto().getId());
                // CORREÇÃO: Pegamos o ID do primeiro hóspede da lista como o hóspede principal.
                stmtReserva.setInt(2, reserva.getHospedes().get(0).getId());
                stmtReserva.setInt(3, reserva.getFuncionarioResponsavel().getId());
                stmtReserva.setDate(4, Date.valueOf(reserva.getDataEntrada()));
                stmtReserva.setDate(5, Date.valueOf(reserva.getDataSaida()));
                stmtReserva.setBigDecimal(6, reserva.getValorTotal());
                stmtReserva.setString(7, reserva.getStatus());
                stmtReserva.executeUpdate();

                try (ResultSet rs = stmtReserva.getGeneratedKeys()) {
                    if (rs.next()) {
                        reserva.setId(rs.getInt(1));
                    } else {
                        throw new SQLException("Falha ao obter o ID da reserva.");
                    }
                }
            }

            // Associa cada hóspede da lista à reserva na tabela 'reserva_hospedes'
            try (PreparedStatement stmtHospede = conn.prepareStatement(sqlReservaHospede)) {
                for (Hospede hospede : reserva.getHospedes()) {
                    stmtHospede.setInt(1, reserva.getId());
                    stmtHospede.setInt(2, hospede.getId());
                    stmtHospede.addBatch();
                }
                stmtHospede.executeBatch();
            }

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        Reserva reserva = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = mapRowToReserva(rs);

                    QuartoDAO quartoDAO = new QuartoDAO();
                    reserva.setQuarto(quartoDAO.buscarPorId(rs.getInt("id_quarto")));

                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    reserva.setFuncionarioResponsavel(funcionarioDAO.buscarPorId(rs.getInt("id_funcionario_responsavel")));

                    reserva.setHospedes(buscarHospedesDaReserva(id));
                }
            }
        }
        return reserva;
    }

    public void atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reservas SET status = ?, valor_total = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getStatus());
            stmt.setBigDecimal(2, reserva.getValorTotal());
            stmt.setInt(3, reserva.getId());
            stmt.executeUpdate();
        }
    }

    private List<Hospede> buscarHospedesDaReserva(int idReserva) throws SQLException {
        List<Hospede> hospedes = new ArrayList<>();
        String sql = "SELECT h.id FROM hospedes h JOIN reserva_hospedes rh ON h.id = rh.id_hospede WHERE rh.id_reserva = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            try (ResultSet rs = stmt.executeQuery()) {
                HospedeDAO hospedeDAO = new HospedeDAO();
                while (rs.next()) {
                    // CORREÇÃO: Reutiliza o método público buscarPorId do HospedeDAO.
                    int idHospede = rs.getInt("id");
                    Hospede hospede = hospedeDAO.buscarPorId(idHospede);
                    if (hospede != null) {
                        hospedes.add(hospede);
                    }
                }
            }
        }
        return hospedes;
    }

    private Reserva mapRowToReserva(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setId(rs.getInt("id"));
        r.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
        r.setDataSaida(rs.getDate("data_saida").toLocalDate());
        r.setValorTotal(rs.getBigDecimal("valor_total"));
        r.setStatus(rs.getString("status"));
        return r;
    }
    
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
        r.getHospedes().add(h);  // apenas o hóspede principal
        t.setReserva(r);
    }

    return t;
}
}
