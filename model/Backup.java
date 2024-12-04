package model;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import aed3.LZW;

public class Backup {

    // Método para criar um backup
    public void criarBackup() throws Exception {
        String diretorioDados = "dados"; // Pasta padrão para buscar os arquivos
        File pastaDados = new File(diretorioDados);

        if (!pastaDados.exists() || !pastaDados.isDirectory()) {
            throw new IOException("A pasta 'dados' não existe ou não é um diretório.");
        }

        // Lista todos os arquivos dentro da pasta "dados"
        File[] arquivos = pastaDados.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            throw new IOException("A pasta 'dados' está vazia.");
        }

        String nomePasta = criarPastaDeBackup();
        String caminhoBackup = nomePasta + "/backup_" + gerarNomeBackup() + ".bkp";

        try (FileOutputStream fos = new FileOutputStream(caminhoBackup);
                DataOutputStream dos = new DataOutputStream(fos)) {

            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    // (1) Nome do arquivo
                    dos.writeUTF(arquivo.getName());

                    // (2) Compactação do vetor de bytes
                    byte[] dados = Files.readAllBytes(arquivo.toPath());
                    byte[] dadosCompactados = LZW.codifica(dados);

                    // (3) Escrever tamanho e dados compactados
                    dos.writeInt(dadosCompactados.length);
                    dos.write(dadosCompactados);
                }
            }
        }
        System.out.println("Backup criado em: " + caminhoBackup);
    }

    // Método para restaurar um backup
    public void restaurarBackup(String caminhoBackup) throws Exception {
        String diretorioDados = "dados";

        // Processa o arquivo de backup para restaurar os arquivos
        try (FileInputStream fis = new FileInputStream(caminhoBackup);
                DataInputStream dis = new DataInputStream(fis)) {

            while (dis.available() > 0) {
                // (1) Nome do arquivo
                String nomeArquivo = dis.readUTF();

                // (2) Tamanho do vetor compactado
                int tamanhoCompactado = dis.readInt();

                // (3) Dados compactados
                byte[] dadosCompactados = new byte[tamanhoCompactado];
                dis.readFully(dadosCompactados);

                // Descompactar os dados
                byte[] dados = LZW.decodifica(dadosCompactados);

                // Restaurar o arquivo na pasta "dados"
                Path caminhoRestaurado = Paths.get(diretorioDados, nomeArquivo);
                Files.write(caminhoRestaurado, dados);
            }
        }
        System.out.println("Restauração concluída. Os arquivos foram salvos na pasta 'dados'.");
    }

    // Método para excluir um arquivo de backup
    public void excluirBackup(String caminhoBackup) throws IOException {
        File arquivoBackup = new File(caminhoBackup);

        if (!arquivoBackup.exists()) {
            throw new IOException("O arquivo de backup não existe: " + caminhoBackup);
        }

        // Exclui o arquivo de backup
        if (arquivoBackup.delete()) {
            System.out.println("Backup excluído com sucesso: " + caminhoBackup);
        } else {
            throw new IOException("Falha ao excluir o backup: " + caminhoBackup);
        }
    }

    // Método para criar uma pasta para armazenar backups
    private String criarPastaDeBackup() throws IOException {
        String nomePasta = "backups";
        Files.createDirectories(Paths.get(nomePasta)); // Garante que a pasta exista
        return nomePasta;
    }

    // Método para gerar um nome para o arquivo de backup
    private String gerarNomeBackup() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(new Date());
    }

    public ArrayList<String> listar() {
        // Cria um objeto File com o caminho da pasta
        File pasta = new File("backups");
        ArrayList<String> backups = new ArrayList<>();

        // Verifica se o caminho é uma pasta válida
        if (pasta.isDirectory()) {
            // Lista todos os arquivos e diretórios dentro da pasta
            File[] arquivos = pasta.listFiles();

            if (arquivos != null) {
                int i = 0;
                System.out.println("\nBackups disponiveis:");

                for (File arquivo : arquivos) {
                    if (arquivo.isFile()) {
                        System.out.println("["+ (i++) +"] "+ arquivo.getName());
                        backups.add(arquivo.getName());
                    }
                }

            } else {
                System.out.println("Não foi possível listar os arquivos.");
            }
        } else {
            System.out.println("O caminho especificado não é uma pasta.");
        }

        return backups;
    }
}
