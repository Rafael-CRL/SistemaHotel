package sistemahotel.controller;

import javax.swing.JOptionPane;
import sistemahotel.dao.UsuarioDAO;
import sistemahotel.model.Usuario;

public class AutenticaController {
    
    private UsuarioDAO usuarioDAO;

    public AutenticaController(){
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Autentica um usuário com base no CPF e na senha.
     */
    public boolean autenticar(String cpf, String senha) {
        Usuario usuarioDoBanco = usuarioDAO.buscarPorCPF(cpf);

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
        
        System.out.println("CPF ou senha inválidos");
        return false;
    }
}