/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemahotel.dao.QuartoDAO;
import sistemahotel.dao.HospedeDAO;
import sistemahotel.model.Quarto;
import sistemahotel.model.Hospede;
import java.util.List;
import javax.swing.JOptionPane;

public class CadastroController {
    private final QuartoDAO quartoDAO = new QuartoDAO();
    private final HospedeDAO hospedeDAO = new HospedeDAO();

    
    public boolean cadastrarQuarto(String numero, String tipo, String status) {
        if (numero == null || numero.isBlank() || tipo == null || tipo.isBlank()|| status == null || status.isBlank()) {
            JOptionPane.showMessageDialog(null, "Número e tipo do quarto não podem estar vazios.");
            throw new IllegalArgumentException("Número e tipo do quarto não podem estar vazios.");
        }

        Quarto quarto = new Quarto(0, numero.trim(), tipo.trim(), status.trim());

        return quartoDAO.cadastrarQuarto(quarto);
    }

    public boolean cadastrarHospede(String nome, String cpf) {
        if (nome == null || nome.isBlank() || cpf == null || cpf.isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome e CPF não podem estar vazios.");
            throw new IllegalArgumentException("Nome e CPF não podem estar vazios.");
        }

        // Remover pontuação do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        Hospede hospede = new Hospede(0, nome.trim(), cpf);

        return hospedeDAO.cadastrarHospede(hospede);
    }

    public void listarQuartosDisponiveis() {
        List<Quarto> quartos = quartoDAO.listarQuartosDisponiveis();
        for (Quarto q : quartos) {
            System.out.println("ID: " + q.getId() + ", Número: " + q.getNumero() + ", Tipo: " + q.getTipo());
        }
    }
    public void listarHospedes() {
    List<Hospede> hospedes = hospedeDAO.listarHospedes();
    for (Hospede h : hospedes) {
        System.out.println("ID: " + h.getId() + ", Nome: " + h.getNome() + ", CPF: " + h.getCpf());
    }
}

    public List<Quarto> listarQuartos() {
        return quartoDAO.listarTodos();
    }
    
    public boolean excluirQuarto(int id) {
        try {
            quartoDAO.excluirQuarto(id);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao excluir quarto: " + e.getMessage());
            return false;
        }
    }
     public Quarto buscarQuartoPorId(int id) {
    try {
        return quartoDAO.buscarPorId(id);
    } catch (SQLException e) {
        System.err.println("Erro no Controller ao buscar quarto por ID: " + e.getMessage());
        return null; // Retorna null em caso de erro de banco de dados
    }
}
      public boolean atualizarQuarto(Quarto qua) {
        try {
            quartoDAO.atualizarQuarto(qua);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar quarto: " + e.getMessage());
            return false;
        }
    }

}

