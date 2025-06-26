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
    public boolean autenticar(String cpf, String senha) {
        // Envolvemos a chamada ao DAO em um bloco try-catch
        try {
            Funcionario usuarioDoBanco = usuarioDAO.buscarPorCPF(cpf);

            // Verifica se o usuário existe no banco
            if (usuarioDoBanco != null) {
                // Compara a senha digitada com a senha do banco
                // Lembre-se: no futuro, aqui entrará a verificação da senha CRIPTOGRAFADA
                if (usuarioDoBanco.getSenha().equals(senha)) {
                    System.out.println("Login bem-sucedido! Bem-vindo(a), " + usuarioDoBanco.getNome());
                    // Aqui você pode guardar o usuário logado para uso futuro no sistema
                    return true;
                }
            }
            
        } catch (SQLException e) {
            // Se ocorrer um erro de SQL (ex: conexão caiu), informamos no console
            // e a autenticação falha.
            System.err.println("Erro de banco de dados ao tentar autenticar.");
            e.printStackTrace();
            // A View pode mostrar uma mensagem de erro genérica aqui
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Se o try terminar sem encontrar o usuário ou a senha não bater,
        // o fluxo continua aqui.
        System.out.println("CPF ou senha inválidos");
        return false;
    }
}