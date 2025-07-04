package sistemahotel.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import sistemahotel.dao.QuartoDAO;
import sistemahotel.dao.ReservaDAO;
import sistemahotel.model.Funcionario;
import sistemahotel.model.Hospede;
import sistemahotel.model.Quarto;
import sistemahotel.model.Reserva;

/**
 * Controller refatorado para trabalhar com composição de objetos.
 */
public class ReservaController {

    private ReservaDAO reservaDAO;
    private QuartoDAO quartoDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
        this.quartoDAO = new QuartoDAO();
    }

    public boolean criarReserva(Quarto quarto, List<Hospede> hospedes, Funcionario funcionario, LocalDate dataEntrada, LocalDate dataSaida) {
        try {
            if (dataSaida.isBefore(dataEntrada)) {
                throw new IllegalArgumentException("A data de saída deve ser posterior à data de entrada.");
            }
            if (!"Disponível".equalsIgnoreCase(quarto.getStatus())) {
                throw new IllegalStateException("O quarto selecionado não está disponível.");
            }
            
            Reserva novaReserva = new Reserva();
            novaReserva.setQuarto(quarto);
            novaReserva.setHospedes(hospedes);
            novaReserva.setFuncionarioResponsavel(funcionario);
            novaReserva.setDataEntrada(dataEntrada);
            novaReserva.setDataSaida(dataSaida);
            novaReserva.setStatus("Pendente");
            novaReserva.setValorTotal(BigDecimal.ZERO);

            reservaDAO.criarReserva(novaReserva);

            quarto.ocupar(); 
            quartoDAO.atualizarQuarto(quarto);
            
            return true;

        } catch (SQLException | IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro no Controller ao criar reserva: " + e.getMessage());
            return false;
        }
    }

    public boolean realizarCheckIn(int idReserva) {
        try {
            Reserva reserva = reservaDAO.buscarPorId(idReserva);
            if (reserva != null && "Pendente".equalsIgnoreCase(reserva.getStatus())) {
                reserva.setStatus("Ativa");
                reservaDAO.atualizar(reserva);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao fazer check-in: " + e.getMessage());
            return false;
        }
    }
    
    public BigDecimal realizarCheckOut(int idReserva) {
        try {
             Reserva reserva = reservaDAO.buscarPorId(idReserva);
             if (reserva == null || !"Ativa".equalsIgnoreCase(reserva.getStatus())) {
                throw new IllegalStateException("A reserva não está ativa para fazer check-out.");
             }
             
        
             BigDecimal valorTotal = reserva.calcularValorTotal();
             
             reserva.setValorTotal(valorTotal);
             reserva.setStatus("Finalizada");
             reservaDAO.atualizar(reserva);

             Quarto quarto = reserva.getQuarto();
             if(quarto != null){
                 quarto.liberar();
                 quartoDAO.atualizarQuarto(quarto);
             }
             
             return valorTotal;

        } catch (Exception e) {
            System.err.println("Erro no Controller ao fazer check-out: " + e.getMessage());
            return null;
        }
    }
    public Reserva buscarReservaPorId(int idReserva) {
    try {
        return reservaDAO.buscarPorId(idReserva);
    } catch (SQLException e) {
        System.err.println("Erro ao buscar reserva por ID: " + e.getMessage());
        return null;
    }
}
}