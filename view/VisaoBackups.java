package view;

import java.util.Scanner;

public class VisaoBackups {
    private static Scanner sc = new Scanner(System.in);

    public int menu() {
        
        System.out.println("\nPUCTASK 1.0");
        System.out.println("-----------");
        System.out.println("> Início > Backups");
        System.out.println("1) Fazer Backup");
        System.out.println("2) Restaurar Backup");
        System.out.println("3) Excluir Backup");
        System.out.println("0) Sair");
        
        System.out.print("Opção: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int leOpcao(){
        System.out.print("Opção: ");
        return Integer.parseInt(sc.nextLine());
    }
}