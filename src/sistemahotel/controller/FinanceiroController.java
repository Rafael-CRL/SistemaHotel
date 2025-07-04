package sistemahotel.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import sistemahotel.dao.FinanceiroDAO;
import sistemahotel.model.Funcionario;
import sistemahotel.model.Reserva;
import sistemahotel.model.Transacao;

public class FinanceiroController {

    private final FinanceiroDAO financeiroDAO;

    public FinanceiroController() {
        this.financeiroDAO = new FinanceiroDAO();
    }

    // ✅ Buscar todas as transações (sem filtro)
    public List<Transacao> buscarTodasTransacoes() {
        try {
            return financeiroDAO.buscarTodasTransacoes();
        } catch (Exception e) {
            System.err.println("Erro ao buscar transações: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // ✅ Buscar transações com filtros aplicados
    public List<Transacao> buscarTransacoesFiltradas(LocalDate dataInicio, LocalDate dataFim, String tipo, String categoria) {
        try {
            return financeiroDAO.buscarTransacoesFiltradas(dataInicio, dataFim, tipo, categoria);
        } catch (Exception e) {
            System.err.println("Erro ao filtrar transações: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Orquestra o registro de um pagamento de reserva (uma ENTRADA).
     */
    public boolean registrarPagamentoReserva(Reserva reservaFinalizada, Funcionario funcionarioResponsavel, String formaPagamento) {
        if (reservaFinalizada == null || !"Finalizada".equalsIgnoreCase(reservaFinalizada.getStatus())) {
            return false;
        }

        Transacao transacao = new Transacao();
        transacao.setTipo("Entrada");
        transacao.setCategoria("Reserva");
        transacao.setValor(reservaFinalizada.getValorTotal());
        transacao.setDataTransacao(LocalDateTime.now());
        transacao.setDescricao("Pagamento da reserva ID: " + reservaFinalizada.getId());
        transacao.setFormaPagamento(formaPagamento); // Usa o parâmetro recebido
        transacao.setReserva(reservaFinalizada);
        transacao.setFuncionario(funcionarioResponsavel);

        try {
            financeiroDAO.inserirTransacao(transacao);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao registrar pagamento da reserva: " + e.getMessage());
            return false;
        }
    }

    /**
     * Orquestra o registro de um pagamento de salário (uma SAÍDA).
     */
    public boolean registrarPagamentoFuncionario(Funcionario funcionarioPago, BigDecimal valor, String descricao, String formaPagamento) {
        Transacao transacao = new Transacao();
        transacao.setTipo("Saída");
        transacao.setCategoria("Salário");
        transacao.setValor(valor);
        transacao.setDataTransacao(LocalDateTime.now());
        transacao.setDescricao(descricao);
        transacao.setFormaPagamento(formaPagamento);
        transacao.setFuncionario(funcionarioPago); // Ligação por Composição

        try {
            financeiroDAO.inserirTransacao(transacao);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao registrar pagamento de funcionário: " + e.getMessage());
            return false;
        }
    }
}
