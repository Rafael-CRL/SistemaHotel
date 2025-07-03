/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import sistemahotel.model.Transacao;
import sistemahotel.dao.FinanceiroDAO;
import sistemahotel.model.Reserva;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // A conexão é um atributo da classe, obtido uma única vez.
    private Connection conn;

    public ReservaDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    public void criarReserva(Reserva reserva) throws SQLException {
    // Verificação de segurança: garantir que o valor total não seja nulo
    if (reserva.getValorTotal() == null) {
        throw new SQLException("Valor da reserva está nulo. Defina o valor total antes de salvar.");
    }

    String sql = "INSERT INTO reservas (id_quarto, id_hospede, data_entrada, data_saida, valor_total, status) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, reserva.getIdQuarto());
        stmt.setInt(2, reserva.getIdHospede());
        stmt.setDate(3, Date.valueOf(reserva.getDataEntrada()));
        stmt.setDate(4, Date.valueOf(reserva.getDataSaida()));
        stmt.setBigDecimal(5, reserva.getValorTotal());
        stmt.setString(6, reserva.getStatus());
        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                reserva.setId(rs.getInt(1));
            }
        }
    }
}

    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToReserva(rs);
                }
            }
        }
        return null;
    }

    public List<Reserva> listarTodas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservas.add(mapRowToReserva(rs));
            }
        }
        return reservas;
    }
    
    // Atualiza apenas os campos que mudam durante a operação (status, valor).
    public void atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reservas SET status = ?, valor_total = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getStatus());
            stmt.setBigDecimal(2, reserva.getValorTotal());
            stmt.setInt(3, reserva.getId());
            stmt.executeUpdate();
        }
    }
    
    // Método auxiliar para mapear uma linha do ResultSet para um objeto Reserva.
    // Isso evita a repetição de código.
    private Reserva mapRowToReserva(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setId(rs.getInt("id"));
        r.setIdQuarto(rs.getInt("id_quarto"));
        r.setIdHospede(rs.getInt("id_hospede"));
        // Converte o java.sql.Date do banco para o LocalDate moderno.
        r.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
        r.setDataSaida(rs.getDate("data_saida").toLocalDate());
        // Usa getBigDecimal para ler o valor com precisão.
        r.setValorTotal(rs.getBigDecimal("valor_total"));
        r.setStatus(rs.getString("status"));
        return r;
    }
}