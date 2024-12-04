import java.util.Scanner;

import controller.*;
public class Main {
    public static void main(String[] args) {
        try {
            int opcao;
            Scanner console = new Scanner(System.in);

            do {
                System.out.println("\nPUCTASK 1.0");
                System.out.println("-----------");
                System.out.println("1) Tarefas");
                System.out.println("2) Categorias");
                System.out.println("3) Rótulos");
                System.out.println("4) Backup");
                System.out.println("0) Sair");

                System.out.print("Opção: ");
                opcao = Integer.parseInt(console.nextLine());
                ControleTarefas controleTarefas = new ControleTarefas();

                switch (opcao) {
                    case 1:
                        controleTarefas.iniciar();
                        break;
                    case 2:
                        ControleCategorias controleCategorias = new ControleCategorias();
                        controleCategorias.iniciar();
                        break;
                    case 3:
                        ControleRotulos controleRotulos = new ControleRotulos(controleTarefas);
                        controleRotulos.iniciar();
                        break;
                    case 4:
                        ControleBackups controleBackups = new ControleBackups();
                        controleBackups.iniciar();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 0);

            console.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
