package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.Categoria;
import model.Tarefa;
import model.Rotulo;

public class VisaoTarefas {
    private static Scanner sc = new Scanner(System.in);

    public Tarefa leTarefa(int idCategoria) {
        System.out.println("\nInclusão de Tarefa");
        System.out.print("Nome da tarefa: ");
        String nome = sc.nextLine();

        short prioridade;
        do {
            System.out.print("Prioridade (1 a 3): ");
            prioridade = Short.parseShort(sc.nextLine());
            if (prioridade < 1 || prioridade > 3) {
                System.out.println("Prioridade deve ser entre 1 e 3. Tente novamente.");
            }
        } while (prioridade < 1 || prioridade > 3);

        return new Tarefa(idCategoria, nome, LocalDate.now(), (short) 0, prioridade);
    }

    public Tarefa editaTarefa(Tarefa tarefa, ArrayList<Categoria> categorias) {
        System.out.println("\nEdição de Tarefa");

        System.out.print("Deseja alterar o nome da tarefa? (s/n): ");
        if (sc.nextLine().trim().toLowerCase().equals("s")) {
            System.out.print("Novo nome da tarefa: ");
            tarefa.setNome(sc.nextLine());
        }

        System.out.print("Deseja marcar a tarefa como concluída? (s/n): ");
        if (sc.nextLine().trim().toLowerCase().equals("s")) {
            tarefa.setConclusao(LocalDate.now());
        }

        System.out.print("Deseja alterar a prioridade da tarefa? (s/n): ");
        if (sc.nextLine().trim().toLowerCase().equals("s")) {
            short prioridade;
            do {
                System.out.print("Nova prioridade (1 a 3): ");
                prioridade = Short.parseShort(sc.nextLine());
                if (prioridade < 1 || prioridade > 3) {
                    System.out.println("Prioridade deve ser entre 1 e 3. Tente novamente.");
                }
            } while (prioridade < 1 || prioridade > 3);
            tarefa.setPrioridade(prioridade);
        }

        System.out.print("Deseja alterar a categoria da tarefa? (s/n): ");
        if (sc.nextLine().trim().toLowerCase().equals("s")) {
            mostrarCategorias(categorias);
            int novaCategoria = selecionaCategoria(categorias.size());
            tarefa.setIdCategoria(novaCategoria);
        }

        return tarefa;
    }

    public int[] adicionaRotulosTarefa(ArrayList<Rotulo> rotulos) {
        System.out.println("\nAdição de Rótulos");
        int[] novosRotulos = new int[0];

        System.out.print("Deseja adicionar rótulos? (s/n): ");
        String escolha = sc.nextLine().trim().toLowerCase();
        if (escolha.equals("s")) {
            mostrarRotulos(rotulos);
            novosRotulos = selecionaMultiplosRotulos(rotulos.size());
        }

        return novosRotulos;
    }

    public int[] removeRotulosTarefa(ArrayList<Rotulo> rotulosAtuais) {
        System.out.println("\nRemoção de Rótulos");
        int[] rotulosParaRemover = new int[0];

        System.out.print("Deseja remover rótulos? (s/n): ");
        String escolha = sc.nextLine().trim().toLowerCase();
        if (escolha.equals("s")) {
            mostrarRotulos(rotulosAtuais);
            rotulosParaRemover = selecionaMultiplosRotulos(rotulosAtuais.size());
        }

        return rotulosParaRemover;
    }

    public String leBusca() {
        System.out.print("Termos da busca: ");
        String busca = sc.nextLine();
        return busca;
    }

    public int leIdTarefa() {
        System.out.print("Digite o ID da tarefa: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void mostraTarefa(Tarefa tarefa) {
        System.out.println(tarefa.toString());
    }

    public int menu() {
        System.out.println("PUCTASK 1.0");
        System.out.println("-----------");
        System.out.println("> Início > Tarefas");
        System.out.println("1) Incluir");
        System.out.println("2) Buscar por ID");
        System.out.println("3) Buscar por termos");
        System.out.println("4) Alterar");
        System.out.println("5) Excluir");
        System.out.println("6) Listar");
        System.out.println("7) Listar por Categoria");
        System.out.println("8) Listar por Rótulo");
        System.out.println("0) Retornar ao menu anterior");

        System.out.print("Opção: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarCategorias(ArrayList<Categoria> categorias) {
        System.out.println("\nCategorias disponíveis:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNome());
        }
    }

    public int selecionaCategoria(int totalCategorias) {
        int numeroCategoria;
        do {
            System.out.print("Selecione a categoria da tarefa: ");
            numeroCategoria = Integer.parseInt(sc.nextLine());
            if (numeroCategoria < 1 || numeroCategoria > totalCategorias) {
                System.out.println("Número de categoria inválido. Tente novamente.");
            }
        } while (numeroCategoria < 1 || numeroCategoria > totalCategorias);
        return numeroCategoria;
    }

    public void mostrarRotulos(ArrayList<Rotulo> rotulos) {
        System.out.println("\nRotulos:");
        for (int i = 0; i < rotulos.size(); i++) {
            System.out.println((i + 1) + ". " + rotulos.get(i).getRotulo());
        }
    }

    public int selecionaRotulo(int totalRotulos) {
        int numeroRotulo;
        do {
            System.out.print("Selecione o rotulo da tarefa: ");
            numeroRotulo = Integer.parseInt(sc.nextLine());
            if (numeroRotulo < 1 || numeroRotulo > totalRotulos) {
                System.out.println("Número de rotulo inválido. Tente novamente.");
            }
        } while (numeroRotulo < 1 || numeroRotulo > totalRotulos);
        return numeroRotulo;
    }

    public int[] selecionaMultiplosRotulos(int totalRotulos) {
        ArrayList<Integer> rotulosSelecionados = new ArrayList<>();
        int numeroRotulo;
        String continuar;

        do {
            do {
                System.out.print("Selecione o rótulo da tarefa: ");
                numeroRotulo = Integer.parseInt(sc.nextLine());
                if (numeroRotulo < 1 || numeroRotulo > totalRotulos) {
                    System.out.println("Número de rótulo inválido. Tente novamente.");
                } else if (rotulosSelecionados.contains(numeroRotulo)) {
                    System.out.println("Rótulo já selecionado. Tente novamente.");
                }
            } while (numeroRotulo < 1 || numeroRotulo > totalRotulos || rotulosSelecionados.contains(numeroRotulo));

            rotulosSelecionados.add(numeroRotulo);

            System.out.print("Deseja selecionar outro rótulo? (s/n): ");
            continuar = sc.nextLine().trim().toLowerCase();
        } while (continuar.equals("s"));

        return rotulosSelecionados.stream().mapToInt(i -> i).toArray();
    }

}
