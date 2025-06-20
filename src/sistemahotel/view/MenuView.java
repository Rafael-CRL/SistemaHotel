package sistemahotel.view;

import java.util.Scanner;

public class MenuView {

    private Scanner scanner;

    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = -1;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Gestão de Clientes/Quartos");
            System.out.println("2. Reservas");
            System.out.println("3. Financeiro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.println("-> Chamando módulo de Gestão de Clientes/Quartos (Klayton)...");
                    break;
                case 2:
                    System.out.println("-> Chamando módulo de Reservas (Daniel)...");
                    break;
                case 3:
                    System.out.println("-> Chamando módulo Financeiro (Ray)...");
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}