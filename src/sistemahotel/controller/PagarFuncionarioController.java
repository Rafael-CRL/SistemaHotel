package sistemahotel.controller;

import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import sistemahotel.dao.PagarFuncionarioDAO;
import sistemahotel.model.PagamentoFuncionario;

public class PagarFuncionarioController {

    private final PagarFuncionarioDAO dao = new PagarFuncionarioDAO();
    //Buscar dados do funcionário pelo nome
    public Optional<PagamentoFuncionario> buscarFuncionario(String nome) {
        return dao.buscarFuncionarioPorNome(nome);
    }

    //egistrar pagamento
    public void registrarPagamento(PagamentoFuncionario pagamento) {
        if (pagamento.getValorPago() == null || pagamento.getValorPago().doubleValue() <= 0) {
            JOptionPane.showMessageDialog(null, "O valor do pagamento deve ser maior que zero.");
            return;
        }

        if (pagamento.getDataPagamento() == null) {
            JOptionPane.showMessageDialog(null, "A data do pagamento é obrigatória.");
            return;
        }

        dao.registrarPagamento(pagamento);
    }

    public List<String> listarNomesFuncionarios() {
        return dao.listarNomesFuncionarios();
    }
}