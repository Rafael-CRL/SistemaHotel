package sistemahotel.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import sistemahotel.dao.FinanceiroDAO;
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
}