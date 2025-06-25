/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;

/**
 *
 * @author LabLa
 */

import sistemahotel.dao.ReservaDAO;
import sistemahotel.model.Reserva;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    public void criarReserva(int idQuarto, int idHospede, Date dataEntrada, Date dataSaida, double valorTotal, String status) throws Exception {
        if (dataSaida.before(dataEntrada)) {
            throw new Exception("Data de saída deve ser após a data de entrada.");
        }
        Reserva reserva = new Reserva(idQuarto, idHospede, dataEntrada, dataSaida, valorTotal, status);
        reservaDAO.criarReserva(reserva);
    }

    public void realizarCheckIn(int idReserva) throws Exception {
        Reserva reserva = reservaDAO.buscarPorId(idReserva);
        if (reserva == null) {
            throw new Exception("Reserva não encontrada.");
        }
        if (!"Pendente".equals(reserva.getStatus())) {
            throw new Exception("Reserva não está pendente.");
        }
        reserva.setStatus("Ativa");
        reservaDAO.atualizar(reserva);
    }

    public double realizarCheckOut(int idReserva, double precoPorNoite) throws Exception {
        Reserva reserva = reservaDAO.buscarPorId(idReserva);
        if (reserva == null) {
            throw new Exception("Reserva não encontrada.");
        }
        if (!"Ativa".equals(reserva.getStatus())) {
            throw new Exception("Reserva não está ativa.");
        }
        long diff = reserva.getDataSaida().getTime() - reserva.getDataEntrada().getTime();
        int diarias = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        double valorTotal = diarias * precoPorNoite;
        reserva.setValorTotal(valorTotal);
        reserva.setStatus("Concluída");
        reservaDAO.atualizar(reserva);
        return valorTotal;
    }

    public List<Reserva> listarReservas() throws Exception {
        return reservaDAO.listarTodas();
    }
}
