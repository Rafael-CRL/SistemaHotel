/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import sistemahotel.dao.HospedeDAO;
import sistemahotel.dao.QuartoDAO;
import sistemahotel.model.Hospede;
import sistemahotel.model.Quarto;

/**
 * Controller para a lógica de negócio de cadastros e gestão de Quartos e Hóspedes.
 * Esta classe é o intermediário entre a View e os DAOs correspondentes.
 */
public class CadastroController {
    
    private final QuartoDAO quartoDAO;
    private final HospedeDAO hospedeDAO;
    
    public CadastroController() {
        this.quartoDAO = new QuartoDAO();
        this.hospedeDAO = new HospedeDAO();
    }

    public boolean cadastrarHospede(String nome, String cpf) {
        // Validações de negócio pertencem ao Controller.
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty()) {
            System.err.println("Controller: Nome e CPF do hóspede são obrigatórios.");
            return false;
        }
        
        Hospede hospede = new Hospede();
        hospede.setNome(nome.trim());
        hospede.setCpf(cpf);

        try {
            // Chama o método void do DAO e trata a exceção aqui.
            hospedeDAO.cadastrarHospede(hospede);
            return true; // Se nenhuma exceção foi lançada, a operação foi um sucesso.
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao cadastrar hóspede: " + e.getMessage());
            return false; // Se uma exceção foi capturada, a operação falhou.
        }
    }
    

    public List<Hospede> listarHospedes() {
        try {
            return hospedeDAO.listarHospedes();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar hóspedes: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    public boolean cadastrarQuarto(String numero, String tipo, int capacidade, BigDecimal precoDiaria) {
        if (numero == null || numero.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
            System.err.println("Controller: Dados do quarto são obrigatórios.");
            return false;
        }

        Quarto quarto = new Quarto();
        quarto.setNumero(numero.trim());
        quarto.setTipo(tipo.trim());
        quarto.setCapacidade(capacidade);
        quarto.setPrecoDiaria(precoDiaria);
        quarto.setStatus("Disponível"); // Regra de negócio: todo quarto novo está disponível.


        return quartoDAO.cadastrarQuarto(quarto);
    }


    public boolean atualizarQuarto(Quarto quarto) {
        try {
            quartoDAO.atualizarQuarto(quarto);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar quarto: " + e.getMessage());
            return false;
        }
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

    public boolean quartoTemReservas(int id) {
        try {
            return quartoDAO.temReservas(id);
        } catch (SQLException e) {
            System.err.println("Erro ao verificar reservas do quarto: " + e.getMessage());
            return false;
    }
}

    public List<Quarto> listarQuartosDisponiveis() {

        return quartoDAO.listarQuartosDisponiveis();
    }
    

    public List<Quarto> listarTodosQuartos() {

        return quartoDAO.listarTodos();
    }
    

    public Quarto buscarQuartoPorId(int id) {
        try {
            return quartoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao buscar quarto por ID: " + e.getMessage());
            return null;
        }
    }
}