import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuTarefas {
    
    ArquivoTarefa arqTarefas;
    private static Scanner console = new Scanner(System.in);

    public MenuTarefas() throws Exception {
        arqTarefas = new ArquivoTarefa();
    }

    public void menu() {

        int opcao;
        do {

            System.out.println("AEDsIII");
            System.out.println("-------");
            System.out.println("\n> Início > Tarefas");
            System.out.println("1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            try {
                opcao = Integer.valueOf(console.nextLine());
            } catch(NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarTarefa();
                    break;
                case 2:
                    incluirTarefa();
                    break;
                case 3:
                    alterarTarefa();
                    break;
                case 4:
                    excluirTarefa();
                    break;
                case 0:
                Teste.main(null);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    public void incluirTarefa() {
        String nome;
        boolean dadosCompletos = false;

        System.out.println("\nInclusão de tarefa");
        do {
            System.out.print("\nNome da tarefa (min. de 5 letras): ");
            nome = console.nextLine();
            if(nome.length()>=5 || nome.length()==0)
                dadosCompletos = true;
            else 
                System.err.println("O nome da tarefa deve ter no mínimo 5 caracteres.");
        } while(!dadosCompletos);

        if(nome.length()==0) 
            return;

        System.out.println("Categoria (índice): ");
        int categoria = Integer.parseInt(console.nextLine());
        System.out.println("Data de início: ");
        LocalDate dataini = null, datafim = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataini = LocalDate.parse(console.nextLine(),formatter);
        System.out.println("Data de fim: ");
        datafim = LocalDate.parse(console.nextLine(),formatter);
        System.out.println("Status: ");
        int status = Integer.parseInt(console.nextLine());
        System.out.println("Prioridade: ");
        int prioridade = Integer.parseInt(console.nextLine());
        System.out.println("Confirma a inclusão da tarefa? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                Tarefa c = new Tarefa(-1, categoria, nome, dataini, datafim, (short) status, (short) prioridade);
                arqTarefas.create(c);
                System.out.println("Tarefa criada com sucesso.");
                System.out.println("ID: "+c.id);
                System.out.println("Nome: "+c.nome);
                System.out.println("Categoria: "+c.idCategoria);
                
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível criar a categoria!");
            }
        }
    }

    public void buscarTarefa(){
        int indice;
    
        Tarefa t;

        System.out.println("\nBuscar tarefas");
    
        System.out.println("\nId da tarefa: ");
        indice = Integer.parseInt(console.nextLine());
       
        try {
            t = arqTarefas.read(indice);
            if(t!=null)
            System.out.println(t.toString());
            else
            System.out.println("tarefa não encontrada");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        
        
    }
    
    public void alterarTarefa(){
        String nome;
        boolean dadosCompletos = false;

        System.out.println("\nAlteração de tarefa");
        do {
            System.out.print("\nNome da tarefa (min. de 5 letras): ");
            nome = console.nextLine();
            if(nome.length()>=5 || nome.length()==0)
                dadosCompletos = true;
            else 
                System.err.println("O nome da tarefa deve ter no mínimo 5 caracteres.");
        } while(!dadosCompletos);

        if(nome.length()==0) 
            return;

        System.out.println("Categoria (índice): ");
        int categoria = Integer.parseInt(console.nextLine());
        System.out.println("Data de início: ");
        LocalDate dataini = null, datafim = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataini = LocalDate.parse(console.nextLine(),formatter);
        System.out.println("Data de fim: ");
        datafim = LocalDate.parse(console.nextLine(),formatter);
        System.out.println("Status: ");
        int status = Integer.parseInt(console.nextLine());
        System.out.println("Prioridade: ");
        int prioridade = Integer.parseInt(console.nextLine());
        System.out.println("Confirma a inclusão da tarefa? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                Tarefa c = new Tarefa(-1, categoria, nome, dataini, datafim, (short) status, (short) prioridade);
                arqTarefas.update(c);
                System.out.println("Tarefa alterada com sucesso.");
                System.out.println("ID: "+c.id);
                System.out.println("Nome: "+c.nome);
                System.out.println("Categoria: "+c.idCategoria);
                
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível alterar a tarefa!");
            }
        }
    }

    public void excluirTarefa(){
        int id;

        System.out.println("\nExcluir tarefas");
    
        System.out.println("\nId da tarefa: ");
        id = Integer.parseInt(console.nextLine());
        System.out.println("Confirma a exclusão da tarefa? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                if(arqTarefas.delete(id))
                System.out.println("Excluída com sucesso");
                else
                System.out.println("Tarefa não encontrada");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    


}
