import java.util.Scanner;

public class VisaoCategorias {
    private static Scanner sc = new Scanner(System.in);

    public String leNomeCategoria() {
        System.out.print("Nome da categoria: ");
        return sc.nextLine();
    }

    public void mostraCategoria(Categoria categoria) {
        System.out.println(categoria.toString());
    }

    public int menu() {
        System.out.println("PUCBOOK 1.0");
        System.out.println("-----------");
        System.out.println("> Início > Categorias");
        System.out.println("1) Buscar");
        System.out.println("2) Incluir");
        System.out.println("3) Alterar");
        System.out.println("4) Excluir");
        System.out.println("5) Listar");
        System.out.println("0) Voltar");

        System.out.print("Opção: ");
        return Integer.parseInt(sc.nextLine());
    }
}
