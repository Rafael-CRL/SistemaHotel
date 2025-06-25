/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;

/**
 *
 * @author Ray Carvalho
 */

import java.util.Date;
import java.util.List;
import sistemahotel.dao.FinanceiroDAO;
import sistemahotel.model.Financeiro;

public class FinanceiroController {
    
    private FinanceiroDAO dao;
    
    public FinanceiroController() {
        dao = new FinanceiroDAO();
    }

    public void registrarPagamento(Date data, String tipo, double valor, String descricao) {
    Financeiro fin = new Financeiro();
    fin.setDataTransacao((java.sql.Date) data);
    fin.setTipo(tipo);
    fin.setValor(valor);
    fin.setDescricao(descricao);

    FinanceiroDAO dao = new FinanceiroDAO();
    dao.registrarPagamento(fin);
}


    public double calcularSaldoDoDia(Date data) {
        return dao.calcularSaldoDoDia((java.sql.Date) data);
    }

    public List<Financeiro> listarTransacoesPorData(Date data) {
        return dao.buscarTransacoesDoDia((java.sql.Date) data);
    }

    public String emitirRecibo(Financeiro transacao) {
        return dao.emitirRecibo(transacao);
    }
}
