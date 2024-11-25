package view;

import java.util.List;
import java.util.Scanner;

import controller.ControleRotulos;
import controller.ControleTarefas;
import model.Rotulo;
import model.Tarefa;

public class VisaoRotulos {
    private ControleRotulos controleRotulos;
    private ControleTarefas controleTarefas;
    private Scanner scanner;

    public VisaoRotulos(ControleRotulos controleRotulos, ControleTarefas controleTarefas) {
        this.controleRotulos = controleRotulos;
        this.controleTarefas = controleTarefas;
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
            System.out.println("6. Mostrar Tarefas Associadas a um Rótulo");
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
                case 6:
                    mostrarTarefasAssociadas();
                    break;
            }
        } while (opcao != 0);
    }

    private void incluirRotulo() {
        System.out.print("Nome do Rótulo: ");
        String nome = scanner.nextLine();
        controleRotulos.incluirRotulo(nome);
    }

    private void buscarRotulo() {
        System.out.print("ID do Rótulo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Rotulo rotulo = controleRotulos.buscarRotulo(id);
        if (rotulo != null) {
            System.out.println("Rótulo: " + rotulo.getRotulo());
        } else {
            System.out.println("Rótulo não encontrado.");
        }
    }

    private void alterarRotulo() {
        System.out.print("ID do Rótulo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Novo Nome do Rótulo: ");
        String novoNome = scanner.nextLine();
        controleRotulos.alterarRotulo(id, novoNome);
    }

    private void excluirRotulo() {
        System.out.print("ID do Rótulo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        controleRotulos.excluirRotulo(id);
    }

    private void listarRotulos() {
        List<Rotulo> rotulos = controleRotulos.listarRotulos();
        for (Rotulo rotulo : rotulos) {
            System.out.println(rotulo.getId() + ": " + rotulo.getRotulo());
        }
    }

    private void mostrarTarefasAssociadas() {
        System.out.print("ID do Rótulo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        List<Integer> tarefas = controleRotulos.listarTarefas(id);
        for (int idTarefa : tarefas) {
            Tarefa tarefa;
            try {
                tarefa = controleTarefas.buscarTarefa(idTarefa);
                if (tarefa != null) {
                    System.out.println(tarefa.getId() + ": " + tarefa.getNome());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
}