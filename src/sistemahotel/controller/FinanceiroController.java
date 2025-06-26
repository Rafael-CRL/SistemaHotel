/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import sistemahotel.dao.FinanceiroDAO;
import sistemahotel.model.Transacao;

public class FinanceiroController {
    
    private FinanceiroDAO financeiroDAO;
    
    public FinanceiroController() {
        this.financeiroDAO = new FinanceiroDAO();
    }

    /**
     * Recebe os dados da View, cria um objeto Transacao e solicita ao DAO para salvá-lo.
     * @param idReserva ID da reserva associada.
     * @param valor Valor do pagamento.
     * @param tipo Tipo de transação (ex: "Pagamento de Reserva").
     * @return true se o registro foi bem-sucedido, false caso contrário.
     */
    public boolean registrarPagamento(int idReserva, BigDecimal valor, String tipo, LocalDate data) {
    try {
        Transacao novaTransacao = new Transacao();
        novaTransacao.setIdReserva(idReserva);
        novaTransacao.setValor(valor);
        novaTransacao.setTipo(tipo);
        novaTransacao.setStatus("Quitado");
        // ADICIONADO AQUI: Define a data no objeto antes de salvar.
        // .atStartOfDay() converte LocalDate para LocalDateTime (às 00:00).
        novaTransacao.setDataTransacao(data.atStartOfDay()); 
        
        financeiroDAO.registrarPagamento(novaTransacao);
        return true;
    } catch (Exception e) {
        System.err.println("Erro no Controller ao registrar pagamento: " + e.getMessage());
        return false;
    }
}

    /**
     * Calcula o saldo total do dia, buscando as transações no DAO e somando-as.
     * @param data A data para a qual o saldo será calculado.
     * @return O saldo do dia como um BigDecimal.
     */
    public BigDecimal calcularSaldoDiario(LocalDate data) {
        BigDecimal saldo = BigDecimal.ZERO;
        try {
            // CORREÇÃO AQUI: A conversão foi removida, pois o DAO agora aceita LocalDate diretamente.
            List<Transacao> transacoesDoDia = financeiroDAO.buscarTransacoesPorData(data);
            
            for (Transacao t : transacoesDoDia) {
                // Assumindo que tipos como "Pagamento" e "Recebimento" são entradas.
                // E tipos como "Despesa" são saídas.
                if ("Pagamento".equalsIgnoreCase(t.getTipo()) || "Recebimento".equalsIgnoreCase(t.getTipo())) {
                    saldo = saldo.add(t.getValor());
                } else {
                    saldo = saldo.subtract(t.getValor());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no Controller ao calcular saldo: " + e.getMessage());
            // Retorna zero em caso de erro para não quebrar a View
        }
        return saldo;
    }

    /**
     * Busca os dados de uma transação e formata um texto simples para o recibo.
     * @param idTransacao O ID da transação para gerar o recibo.
     * @return Uma String com o recibo formatado ou uma mensagem de erro.
     */
    public String gerarReciboTexto(int idTransacao) {
        try {
            Transacao transacao = financeiroDAO.buscarPorId(idTransacao);
            if (transacao != null) {
                // A responsabilidade de formatar o texto é do Controller
                StringBuilder recibo = new StringBuilder();
                recibo.append("--- RECIBO DE PAGAMENTO ---\n");
                recibo.append("ID da Transação: ").append(transacao.getId()).append("\n");
                recibo.append("ID da Reserva: ").append(transacao.getIdReserva()).append("\n");
                recibo.append("Data: ").append(transacao.getDataTransacao()).append("\n");
                recibo.append("Tipo: ").append(transacao.getTipo()).append("\n");
                recibo.append("Status: ").append(transacao.getStatus()).append("\n");
                recibo.append("Valor: R$ ").append(transacao.getValor()).append("\n");
                recibo.append("---------------------------");
                return recibo.toString();
            } else {
                return "Transação com ID " + idTransacao + " não encontrada.";
            }
        } catch (Exception e) {
            System.err.println("Erro no Controller ao gerar recibo: " + e.getMessage());
            return "Erro ao gerar recibo.";
        }
    }
}