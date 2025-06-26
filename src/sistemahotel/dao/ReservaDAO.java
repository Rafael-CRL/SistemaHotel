/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import sistemahotel.model.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    //1. A conexão é um atributo da classe
    private Connection conn;

    // 2. O construtor é responsável por obter a conexão uma únicavez
    public ReservaDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    public void criarReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas (id_quarto, id_hospede, data_entrada, data_saida, valor_total, status) VALUES (?, ?, ?, ?, ?, ?)";
        // 3. O try-with-resources agora gerencia apenas o PreparedStatement. A conexão (conn) já existe.
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, reserva.getIdQuarto());
            stmt.setInt(2, reserva.getIdHospede());
            stmt.setDate(3, new java.sql.Date(reserva.getDataEntrada().getTime()));
            stmt.setDate(4, new java.sql.Date(reserva.getDataSaida().getTime()));
            stmt.setDouble(5, reserva.getValorTotal());
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
                    Reserva r = new Reserva();
                    r.setId(rs.getInt("id"));
                    r.setIdQuarto(rs.getInt("id_quarto"));
                    r.setIdHospede(rs.getInt("id_hospede"));
                    r.setDataEntrada(rs.getDate("data_entrada"));
                    r.setDataSaida(rs.getDate("data_saida"));
                    r.setValorTotal(rs.getDouble("valor_total"));
                    r.setStatus(rs.getString("status"));
                    return r;
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
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setIdQuarto(rs.getInt("id_quarto"));
                r.setIdHospede(rs.getInt("id_hospede"));
                r.setDataEntrada(rs.getDate("data_entrada"));
                r.setDataSaida(rs.getDate("data_saida"));
                r.setValorTotal(rs.getDouble("valor_total"));
                r.setStatus(rs.getString("status"));
                reservas.add(r);
            }
        }
        return reservas;
    }

    public void atualizar(Reserva reserva) throws SQLException {
        String sql = "UPDATE reservas SET status = ?, valor_total = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getStatus());
            stmt.setDouble(2, reserva.getValorTotal());
            stmt.setInt(3, reserva.getId());
            stmt.executeUpdate();
        }
    }
}