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
import sistemahotel.dao.QuartoDAO;
import sistemahotel.model.Reserva;
import sistemahotel.model.Quarto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReservaController {
    private ReservaDAO reservaDAO;
    private QuartoDAO quartoDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
        this.quartoDAO = new QuartoDAO();
    }

    public void criarReserva(int idQuarto, int idHospede, Date dataEntrada, Date dataSaida, double valorTotal, String status) throws Exception {
        List<Reserva> reservas = reservaDAO.listarTodas();
        for (Reserva r : reservas) {
            if (r.getIdQuarto() == idQuarto && 
                !(dataSaida.before(r.getDataEntrada()) || dataEntrada.after(r.getDataSaida())) &&
                !r.getStatus().equals("Concluída")) {
                throw new Exception("Quarto indisponível para o período solicitado.");
            }
        }
        if (dataSaida.before(dataEntrada)) {
            throw new Exception("Data de saída deve ser após a data de entrada.");
        }
        Reserva reserva = new Reserva(idQuarto, idHospede, dataEntrada, dataSaida, valorTotal, status);
        reservaDAO.criarReserva(reserva);

        // Atualizar o status do quarto para "Ocupado"
        Quarto quarto = quartoDAO.buscarPorId(idQuarto);
        if (quarto != null) {
            quarto.setStatus("Ocupado");
            quartoDAO.atualizarQuarto(quarto);
        } else {
            throw new Exception("Quarto não encontrado.");
        }
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

    // Método temporário para teste
    public static void main(String[] args) {
        try {
            ReservaController controller = new ReservaController();
            
            // Teste: Criar uma reserva
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataEntrada = sdf.parse("01/11/2023");
            Date dataSaida = sdf.parse("03/11/2023");
            controller.criarReserva(1, 1, dataEntrada, dataSaida, 0.0, "Pendente");
            System.out.println("Reserva criada com sucesso!");

            // Teste: Listar reservas
            List<Reserva> reservas = controller.listarReservas();
            for (Reserva r : reservas) {
                System.out.println("ID: " + r.getId() + ", Quarto: " + r.getIdQuarto() + 
                                   ", Hóspede: " + r.getIdHospede() + ", Status: " + r.getStatus());
            }

            // Teste: Check-in
            controller.realizarCheckIn(1);
            System.out.println("Check-in realizado!");

            // Teste: Check-out
            double valorTotal = controller.realizarCheckOut(1, 100.0);
            System.out.println("Check-out realizado! Valor total: R$" + valorTotal);
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}