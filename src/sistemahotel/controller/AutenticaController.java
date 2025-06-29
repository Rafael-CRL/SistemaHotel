package sistemahotel.controller;

import java.sql.SQLException; // Importe a classe SQLException
import javax.swing.JOptionPane;
import sistemahotel.dao.FuncionarioDAO;
import sistemahotel.model.Funcionario;

public class AutenticaController {
    
    private FuncionarioDAO usuarioDAO;

    public AutenticaController(){
        this.usuarioDAO = new FuncionarioDAO();
    }

    /**
     * Autentica um usuário com base no CPF e na senha.
     */
    public Funcionario autenticar(String cpf, String senha) {
        try {
            Funcionario usuarioDoBanco = usuarioDAO.buscarPorCPF(cpf);

            if (usuarioDoBanco != null) {
                // Futuramente, esta linha será trocada pela verificação com BCrypt
                if (usuarioDoBanco.getSenha().equals(senha)) {
                    System.out.println("Login bem-sucedido! Bem-vindo(a), " + usuarioDoBanco.getNome());
                    // MUDANÇA IMPORTANTE: Retorna o objeto do funcionário completo
                    return usuarioDoBanco;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao tentar autenticar: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }

        // Se o CPF não for encontrado, a senha não bater ou ocorrer um erro, retorna null
        System.out.println("CPF ou senha inválidos");
        return null;
    }
}