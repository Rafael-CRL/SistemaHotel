/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;


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

    public boolean cadastrarQuarto(String numero, String tipo) {
        if (numero == null || numero.isBlank() || tipo == null || tipo.isBlank()) {
            JOptionPane.showMessageDialog(null, "Número e tipo do quarto não podem estar vazios.");
            throw new IllegalArgumentException("Número e tipo do quarto não podem estar vazios.");
        }

        Quarto quarto = new Quarto(0, numero.trim(), tipo.trim(), "Disponível");

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
        try {
            List<Quarto> quartos = quartoDAO.listarQuartosDisponiveis();
            for (Quarto q : quartos) {
                System.out.println("ID: " + q.getId() + ", Número: " + q.getNumero() + ", Tipo: " + q.getTipo());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar quartos: " + e.getMessage());
        }
    }
}
