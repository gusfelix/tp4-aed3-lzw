package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ControleRotulos;
import controller.ControleTarefas;
import model.Rotulo;

public class VisaoRotulos {
    private ControleRotulos controleRotulos;
    private Scanner scanner;

    public VisaoRotulos(ControleRotulos controleRotulos, ControleTarefas controleTarefas) {
        this.controleRotulos = controleRotulos;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("1. Incluir Rótulo");
            System.out.println("2. Buscar Rótulo");
            System.out.println("3. Alterar Rótulo");
            System.out.println("4. Excluir Rótulo");
            System.out.println("5. Listar Rótulos");
            System.out.println("6. Listar Tarefas por Rotulo");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (opcao) {
                case 1:
                    incluirRotulo();
                    break;
                case 2:
                    buscarRotulo();
                    break;
                case 3:
                    alterarRotulo();
                    break;
                case 4:
                    excluirRotulo();
                    break;
                case 5:
                    listarRotulos();
                    break;
            }
        } while (opcao != 0);
    }

    private void incluirRotulo() {
        System.out.print("Nome do Rótulo: ");
        String nome = scanner.nextLine();
        try {
            controleRotulos.incluirRotulo(nome);
        } catch (Exception e) {
            System.out.println("Erro ao incluir rótulo: " + e.getMessage());
        }
    }

    private void buscarRotulo() {
        try {
            System.out.print("Nome do Rótulo: ");
            String nomeRotulo = scanner.nextLine();
            Rotulo rotulo = controleRotulos.buscarRotulo(nomeRotulo);
            if (rotulo != null) {
                System.out.println("Rótulo: " + rotulo.getRotulo());
            } else {
                System.out.println("Rótulo não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar rótulo: " + e.getMessage());
        }
    }

    private void alterarRotulo() {
        System.out.print("Nome do Rótulo: ");
        String nomeRotulo = scanner.nextLine();
        System.out.print("Novo Nome do Rótulo: ");
        String novoNome = scanner.nextLine();
        try {
            controleRotulos.alterarRotulo(nomeRotulo, novoNome);
        } catch (Exception e) {
            System.out.println("Erro ao alterar rótulo: " + e.getMessage());
        }
    }

    private void excluirRotulo() {
        System.out.print("Nome do Rótulo: ");
        String nomeRotulo = scanner.nextLine();
        try {
            controleRotulos.excluirRotulo(nomeRotulo);
        } catch (Exception e) {
            System.out.println("Erro ao excluir rótulo: " + e.getMessage());
        }
    }

    private void listarRotulos() {
        try {
            ArrayList<Rotulo> rotulos = controleRotulos.listarRotulos();
            for (Rotulo rotulo : rotulos) {
                System.out.println(rotulo.getId() + ": " + rotulo.getRotulo());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar rótulos: " + e.getMessage());
        }
    }
}