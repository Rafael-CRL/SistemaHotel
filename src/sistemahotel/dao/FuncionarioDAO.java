/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistemahotel.model.Funcionario;

/**
 *
 * @author rafael
 */
public class FuncionarioDAO {

    private Connection conn;

    public FuncionarioDAO() {
        this.conn = ConnectionFactory.getConexao();
    }

    public Funcionario buscarPorCPF(String cpf) throws SQLException {
        // Agora busca na tabela 'funcionarios' e carrega todos os campos.
        String sql = "SELECT * FROM funcionarios WHERE cpf = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario func = new Funcionario();
                    func.setId(rs.getInt("id"));
                    func.setNome(rs.getString("nome"));
                    func.setCpf(rs.getString("cpf"));
                    func.setCargo(rs.getString("cargo"));
                    // Carregando os novos campos
                    if (rs.getDate("data_admissao") != null) {
                        func.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
                    }
                    if (rs.getBigDecimal("salario") != null) {
                        func.setSalario(rs.getBigDecimal("salario"));
                    }
                    func.setLogin(rs.getString("login"));
                    func.setSenha(rs.getString("senha"));
                    func.setStatus(rs.getString("status"));
                    func.setEmail(rs.getString("email"));
                    func.setTelefone(rs.getString("telefone"));
                    return func;
                }
            }
        }
        return null;
    }

    public void cadastrarFuncionario(Funcionario func) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, cargo, data_admissao, salario, login, senha, status, email, telefone) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getCpf());
            stmt.setString(3, func.getCargo());
            stmt.setDate(4, Date.valueOf(func.getDataAdmissao()));
            stmt.setBigDecimal(5, func.getSalario());
            stmt.setString(6, func.getLogin());
            stmt.setString(7, func.getSenha()); // Lembre-se que aqui entrará a senha criptografada
            stmt.setString(8, func.getStatus());
            stmt.setString(9, func.getEmail());
            stmt.setString(10, func.getTelefone());
            stmt.executeUpdate();
        }
    }

    public void atualizarFuncionario(Funcionario func) throws SQLException {
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, data_admissao = ?, salario = ?, "
                + "login = ?, status = ?, email = ?, telefone = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getCargo());
            stmt.setDate(3, Date.valueOf(func.getDataAdmissao()));
            stmt.setBigDecimal(4, func.getSalario());
            stmt.setString(5, func.getLogin());
            stmt.setString(6, func.getStatus());
            stmt.setString(7, func.getEmail());
            stmt.setString(8, func.getTelefone());
            stmt.setInt(9, func.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setCargo(rs.getString("cargo"));
                if (rs.getDate("data_admissao") != null) {
                    func.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
                }
                if (rs.getBigDecimal("salario") != null) {
                    func.setSalario(rs.getBigDecimal("salario"));
                }
                func.setLogin(rs.getString("login"));
                // Não é comum carregar a senha em listagens por segurança, mas mantendo para consistência
                func.setSenha(rs.getString("senha"));
                func.setStatus(rs.getString("status"));
                func.setEmail(rs.getString("email"));
                func.setTelefone(rs.getString("telefone"));
                funcionarios.add(func);
            }
        }
        return funcionarios;
    }

    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario func = new Funcionario();
                    func.setId(rs.getInt("id"));
                    func.setNome(rs.getString("nome"));
                    func.setCpf(rs.getString("cpf"));
                    func.setCargo(rs.getString("cargo"));
                    if (rs.getDate("data_admissao") != null) {
                        func.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
                    }
                    if (rs.getBigDecimal("salario") != null) {
                        func.setSalario(rs.getBigDecimal("salario"));
                    }
                    func.setLogin(rs.getString("login"));
                    func.setSenha(rs.getString("senha"));
                    func.setStatus(rs.getString("status"));
                    func.setEmail(rs.getString("email"));
                    func.setTelefone(rs.getString("telefone"));
                    return func;
                }
            }
        }
        return null; // Retorna null se não encontrar o funcionário
    }

    /**
     * Método auxiliar para mapear uma linha do ResultSet para um objeto
     * Funcionario. Centraliza a lógica de criação de objetos para evitar
     * repetição de código.
     */
    private Funcionario mapRowToFuncionario(ResultSet rs) throws SQLException {
        Funcionario func = new Funcionario();

        // Herdados da classe Pessoa
        func.setId(rs.getInt("id"));
        func.setNome(rs.getString("nome"));
        func.setCpf(rs.getString("cpf"));
        func.setEmail(rs.getString("email"));
        func.setTelefone(rs.getString("telefone"));

        // Específicos do Funcionário
        func.setCargo(rs.getString("cargo"));
        func.setLogin(rs.getString("login"));
        func.setStatus(rs.getString("status"));
        func.setSalario(rs.getBigDecimal("salario"));
        if (rs.getDate("data_admissao") != null) {
            func.setDataAdmissao(rs.getDate("data_admissao").toLocalDate());
        }
        return func;
    }

    public Funcionario buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE nome = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Reutiliza a lógica de mapeamento que já existe
                    return mapRowToFuncionario(rs); // Supondo que você tenha um método auxiliar
                }
            }
        }
        return null;
    }
}
