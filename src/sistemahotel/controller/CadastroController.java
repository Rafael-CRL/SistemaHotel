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

public class CadastroController {
    private QuartoDAO quartoDAO = new QuartoDAO();
    private HospedeDAO hospedeDAO = new HospedeDAO();

    public void cadastrarQuarto(String numero, String tipo) {
        Quarto quarto = new Quarto(0, numero, tipo, "Disponível");
        try {
            quartoDAO.cadastrarQuarto(quarto);
            System.out.println("Quarto cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar quarto: " + e.getMessage());
        }
    }

    public void cadastrarHospede(String nome, String cpf) {
        Hospede hospede = new Hospede(0, nome, cpf);
        try {
            hospedeDAO.cadastrarHospede(hospede);
            System.out.println("Hóspede cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar hóspede: " + e.getMessage());
        }
    }

    public void listarQuartosDisponiveis() {
        try {
            List<Quarto> quartos = quartoDAO.listarQuartosDisponiveis();
            for (Quarto q : quartos) {
                System.out.println("ID: " + q.getId() + ", Número: " + q.getNumero() + ", Tipo: " + q.getTipo());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar quartos: " + e.getMessage());
        }
    }
}
