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
import sistemahotel.dao.TransacaoDAO;
import sistemahotel.model.Transacao;

public class TransacaoController {
    
    private TransacaoDAO dao;
    
    public TransacaoController() {
        dao = new TransacaoDAO();
    }

    public void registrarPagamento(Transacao transacao) {
        dao.registrarPagamento(transacao);
    }

    public double calcularSaldoDoDia(Date data) {
        return dao.calcularSaldoDoDia((java.sql.Date) data);
    }

    public List<Transacao> listarTransacoesPorData(Date data) {
        return dao.buscarTransacoesDoDia((java.sql.Date) data);
    }

    public String emitirRecibo(Transacao transacao) {
        return dao.emitirRecibo(transacao);
    }
}
