import java.time.LocalDate;
import java.util.Scanner;

public class VisaoTarefas {
    private static Scanner sc = new Scanner(System.in);

    public Tarefa leTarefa() {
        System.out.println("\nInclusão de Tarefa");
        System.out.print("Nome da tarefa: ");
        String nome = sc.nextLine();
        
        System.out.print("ID da Categoria: ");
        int idCategoria = Integer.parseInt(sc.nextLine());

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

    public int leIdTarefa() {
        System.out.print("Digite o ID da tarefa: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int leIdCategoria() {
        System.out.print("Digite o ID da categoria: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void mostraTarefa(Tarefa tarefa) {
        System.out.println(tarefa.toString());
    }

    public int menu() {
        System.out.println("PUCBOOK 1.0");
        System.out.println("-----------");
        System.out.println("> Início > Tarefas");
        System.out.println("1) Incluir");
        System.out.println("2) Buscar");
        System.out.println("3) Alterar");
        System.out.println("4) Excluir");
        System.out.println("5) Listar");
        System.out.println("0) Retornar ao menu anterior");

        System.out.print("Opção: ");
        return Integer.parseInt(sc.nextLine());
    }
}
