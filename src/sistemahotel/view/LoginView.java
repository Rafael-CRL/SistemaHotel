package sistemahotel.view;

import java.util.Scanner;
import sistemahotel.controller.AutenticaController;

public class LoginView {
    
    private Scanner scanner;
    private AutenticaController authController;

    public LoginView() {
        this.scanner = new Scanner(System.in);
        this.authController = new AutenticaController();
    }

    public void exibirLogin() {
        System.out.println("--- LOGIN DO SISTEMA HOTELEIRO ---");
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        boolean sucesso = authController.autenticar(cpf, senha);

        if (sucesso) {
            MenuView menu = new MenuView();
            menu.exibirMenu();
        } else {
            System.out.println("Falha na autenticação. Tente novamente.");
        }
    }
}