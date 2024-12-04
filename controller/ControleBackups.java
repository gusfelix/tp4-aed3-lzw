package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.*;
import view.VisaoBackups;

public class ControleBackups {

    private VisaoBackups visaoBackups;
    private Backup backup;

    public ControleBackups() {
        backup = new Backup();
        visaoBackups = new VisaoBackups();
    }

    public void iniciar() throws Exception {
        int opcao;
        do {
            opcao = visaoBackups.menu();

            switch (opcao) {
                case 1:
                    fazerBackup();
                    break;
                case 2:
                    restaurarBackup();
                    break;
                case 3:
                    excluirBackup();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void fazerBackup() throws Exception {
        backup.criarBackup();
    }

    private void restaurarBackup() throws Exception {
        ArrayList<String> backups = backup.listar();

        if (!backups.isEmpty()) {
            int idBackup = visaoBackups.leOpcao();

            if (idBackup < backups.size() && idBackup >= 0) {
                backup.restaurarBackup("backups/" + backups.get(idBackup));
            } else {
                System.out.println("\nEsse backup não existe");
            }
        } else {
            System.out.println("\nNão há backups para restaurar");
        }
    }

    private void excluirBackup() throws IOException {
        ArrayList<String> backups = backup.listar();

        if (!backups.isEmpty()) {
            int idBackup = visaoBackups.leOpcao();

            if (idBackup < backups.size() && idBackup >= 0) {
                backup.excluirBackup("backups/" + backups.get(idBackup));
            } else {
                System.out.println("\nEsse backup não existe");
            }
        } else {
            System.out.println("\nNão há backups para excluir");
        }
    }
}