import java.util.Scanner;

public class MenuTarefas {
    
    ArquivoCategoria arqCategorias;
    private static Scanner console = new Scanner(System.in);

    public MenuTarefas() throws Exception {
        arqCategorias = new ArquivoCategoria();
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
                    buscarCategoria();
                    break;
                case 2:
                    incluirCategoria();
                    break;
                case 3:
                    alterarCategoria();
                    break;
                case 4:
                    excluirCategoria();
                    break;
                case 0:
                
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    public void incluirCategoria() {
        String nome;
        boolean dadosCompletos = false;

        System.out.println("\nInclusão de categoria");
        do {
            System.out.print("\nNome da categoria (min. de 5 letras): ");
            nome = console.nextLine();
            if(nome.length()>=5 || nome.length()==0)
                dadosCompletos = true;
            else 
                System.err.println("O nome da categoria deve ter no mínimo 5 caracteres.");
        } while(!dadosCompletos);

        if(nome.length()==0) 
            return;

        System.out.println("Confirma a inclusão da categoria? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                Categoria c = new Categoria(nome);
                arqCategorias.create(c);
                System.out.println("Categoria criada com sucesso.");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível criar a categoria!");
            }
        }
    }

    public void buscarCategoria(){
        String nome;
        boolean dadosCompletos = false;

        System.out.println("\nBuscar categoria");
        do {
            System.out.print("\nNome da categoria (min. de 5 letras): ");
            nome = console.nextLine();
            if(nome.length()>=5 || nome.length()==0)
                dadosCompletos = true;
            else 
                System.err.println("O nome da categoria deve ter no mínimo 5 caracteres.");
        } while(!dadosCompletos);

        if(nome.length()==0) 
            return;

            try {
                Categoria c = arqCategorias.read(nome);
                System.out.println(c.toString());
            } catch(Exception e) {
                System.out.println("Categoria não encontrada!");
            }
        
        
    }

    public void alterarCategoria(){
        String nome;
        boolean dadosCompletos = false;

        System.out.println("\nAlterar categoria");
        do {
            System.out.print("\nNome da categoria (min. de 5 letras): ");
            nome = console.nextLine();
            if(nome.length()>=5 || nome.length()==0)
                dadosCompletos = true;
            else 
                System.err.println("O nome da categoria deve ter no mínimo 5 caracteres.");
        } while(!dadosCompletos);

        if(nome.length()==0) 
            return;

        System.out.println("Confirma a alteração da categoria? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                Categoria c = new Categoria(nome);
                arqCategorias.update(c);
                System.out.println("Categoria atualizada com sucesso.");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível criar a categoria!");
            }
        }
    }

    public void excluirCategoria(){
        String nome;
        boolean dadosCompletos = false;

        System.out.println("\nAlterar categoria");
        do {
            System.out.print("\nNome da categoria (min. de 5 letras): ");
            nome = console.nextLine();
            if(nome.length()>=5 || nome.length()==0)
                dadosCompletos = true;
            else 
                System.err.println("O nome da categoria deve ter no mínimo 5 caracteres.");
        } while(!dadosCompletos);

        if(nome.length()==0) 
            return;

        System.out.println("Confirma a alteração da categoria? (S/N) ");
        char resp = console.nextLine().charAt(0);
        if(resp=='S' || resp=='s') {
            try {
                if(arqCategorias.delete(nome))
                System.out.println("Categoria atualizada com sucesso.");
                else
                System.out.println("Categoria inexistente");
            } catch(Exception e) {
                System.out.println("Erro do sistema. Não foi possível excluir a categoria!");
            }
        }
    }


}
