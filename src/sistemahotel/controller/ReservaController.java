/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;

import sistemahotel.dao.ReservaDAO;
import sistemahotel.dao.QuartoDAO;
import sistemahotel.model.Reserva;
import sistemahotel.model.Quarto;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import sistemahotel.dao.FinanceiroDAO;
import sistemahotel.model.Transacao;

/**
 * Controller responsável pela lógica de negócio das reservas.
 * Esta versão está atualizada para usar LocalDate e BigDecimal.
 * @author Daniel
 */
public class ReservaController {

    private ReservaDAO reservaDAO;
    private QuartoDAO quartoDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
        this.quartoDAO = new QuartoDAO();
    }

    public boolean criarReserva(int idQuarto, int idHospede, LocalDate dataEntrada, LocalDate dataSaida) {
        try {
            // Validação de negócio: a data de saída não pode ser antes da de entrada.
            if (dataSaida.isBefore(dataEntrada)) {
                 
                 throw new IllegalArgumentException("A data de saída deve ser posterior à data de entrada.");
            }
            
            // A verificação de disponibilidade do quarto (contra outras reservas)
            // pode ser adicionada aqui como uma melhoria futura

            Reserva novaReserva = new Reserva(idQuarto, idHospede, dataEntrada, dataSaida, "Pendente");
            
            reservaDAO.criarReserva(novaReserva);

            // Após criar a reserva, atualiza o status do quarto para "Ocupado"
            Quarto quarto = quartoDAO.buscarPorId(idQuarto);
            if (quarto != null) {
                quarto.setStatus("Ocupado");
                quartoDAO.atualizarQuarto(quarto); 
            } else {
                // Caso o quarto não seja encontrado, lança um erro.
                throw new IllegalStateException("Quarto com ID " + idQuarto + " não encontrado.");
            }
            
            return true; // Sucesso

        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Erro no Controller ao criar reserva: " + e.getMessage());
            return false; // Falha
        }
    }

    public boolean realizarCheckIn(int idReserva) {
        try {
            Reserva reserva = reservaDAO.buscarPorId(idReserva);
            
            if (reserva != null && "Pendente".equalsIgnoreCase(reserva.getStatus())) {
                reserva.setStatus("Ativa");
                reservaDAO.atualizar(reserva); // Chama o método 'atualizar' que já existe no DAO.
                return true;
            } else {
                // Retorna false se a reserva não foi encontrada ou não está no status correto.
                return false; 
            }
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao fazer check-in: " + e.getMessage());
            return false;
        }
    }
    
    public BigDecimal realizarCheckOut(int idReserva, BigDecimal precoPorNoite) {
        try {
             Reserva reserva = reservaDAO.buscarPorId(idReserva);

             if (reserva == null || !"Ativa".equalsIgnoreCase(reserva.getStatus())) {
                throw new IllegalStateException("A reserva não está ativa para fazer check-out.");
             }

             // Cálculo de diárias usando a API moderna de datas.
             long diarias = ChronoUnit.DAYS.between(reserva.getDataEntrada(), reserva.getDataSaida());
             if (diarias == 0) {
                 diarias = 1; // Garante que seja cobrada pelo menos uma diária.
             }

             BigDecimal valorTotal = precoPorNoite.multiply(new BigDecimal(diarias));
             
             reserva.setValorTotal(valorTotal);
             reserva.setStatus("Finalizada");
             reservaDAO.atualizar(reserva);
             
             
            // Criar e registrar a transação financeira correspondente à reserva
            Transacao transacao = new Transacao();
            transacao.setTipo("Entrada");
            transacao.setValor(reserva.getValorTotal());
            transacao.setDataTransacao(java.time.LocalDateTime.now());
            transacao.setFormaPagamento("Reserva"); // ou use um campo real se houver
            transacao.setCategoria("Reserva");
            transacao.setDescricao("Reserva do hóspede ID " + reserva.getIdHospede());
            transacao.setIdHospede(reserva.getIdHospede());

            FinanceiroDAO financeiroDAO = new FinanceiroDAO();
            financeiroDAO.inserirTransacao(transacao);

             // Libera o quarto, mudando seu status para "Disponível".
             Quarto quarto = quartoDAO.buscarPorId(reserva.getIdQuarto());
             if (quarto != null){
                 quarto.setStatus("Disponível");
                 quartoDAO.atualizarQuarto(quarto);
             }
             
             return valorTotal;

        } catch (Exception e) {
            System.err.println("Erro no Controller ao fazer check-out: " + e.getMessage());
            return null; // Retorna null para indicar que a operação falhou.
        }
    }
}
