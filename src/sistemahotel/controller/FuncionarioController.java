package sistemahotel.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import sistemahotel.dao.FuncionarioDAO;
import sistemahotel.model.Funcionario;
// Ex: org.mindrot.jbcrypt.BCrypt; (para criptografar senha)

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    /**
     * Cadastra um novo funcionário.
     */
    public boolean cadastrarFuncionario(String nome, String cpf, String cargo, LocalDate dataAdmissao,
                                        BigDecimal salario, String login, String senha, String email, String telefone) {
        try {
            // Validações básicas
            if (cpf == null || cpf.trim().isEmpty() || nome == null || nome.trim().isEmpty()) {
                System.err.println("Erro: CPF e Nome são obrigatórios.");
                return false;
            }

            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.setNome(nome);
            novoFuncionario.setCpf(cpf);
            novoFuncionario.setCargo(cargo);
            novoFuncionario.setDataAdmissao(dataAdmissao);
            novoFuncionario.setSalario(salario);
            novoFuncionario.setLogin(login);
            // Criptografar senha aqui no futuro
            // String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());
            // novoFuncionario.setSenha(senhaCriptografada);
            novoFuncionario.setSenha(senha); // Senha em texto puro
            novoFuncionario.setStatus("ativo");
            novoFuncionario.setEmail(email);
            novoFuncionario.setTelefone(telefone);

            funcionarioDAO.cadastrarFuncionario(novoFuncionario);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao cadastrar funcionário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza os dados de um funcionário.
     */
    public boolean atualizarFuncionario(Funcionario func) {
        try {
            funcionarioDAO.atualizarFuncionario(func);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao atualizar funcionário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Exclui um funcionário pelo ID.
     */
    public boolean excluirFuncionario(int id) {
        try {
            funcionarioDAO.excluirFuncionario(id);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao excluir funcionário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna todos os funcionários.
     */
    public List<Funcionario> listarFuncionarios() {
        try {
            return funcionarioDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro no Controller ao listar funcionários: " + e.getMessage());
            return Collections.emptyList(); // Evita quebra na View
        }
    }
    
    public Funcionario buscarFuncionarioPorId(int id) {
    try {
        return funcionarioDAO.buscarPorId(id);
    } catch (SQLException e) {
        System.err.println("Erro no Controller ao buscar funcionário por ID: " + e.getMessage());
        return null; // Retorna null em caso de erro de banco de dados
    }
}
}
