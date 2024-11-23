package util;

import java.io.*;
import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

import aed3.ElementoLista;
import aed3.ListaInvertida;

public class TextProcessor {
    private static final String STOP_WORDS_FILE = "util\\stopWords.txt"; // Nome do arquivo de stop words
    private Set<String> stopWords;
    private ListaInvertida listaInvertida;


    // Construtor que carrega as stop words automaticamente
    public TextProcessor() throws Exception {
        this.stopWords = loadStopWords();
        listaInvertida = new ListaInvertida(
            4, 
            "dados/dicionario.listainv.db", 
            "dados/blocos.listainv.db"
        );
    }

    // Método público para processar texto
    //public String processText(String text) {
    public String[] processText(String text) {
        // Divide o texto em palavras
        List<String> words = Arrays.asList(text.split("\\s+"));

        // Remove stop words e aplica transformações
        List<String> processedWords = words.stream()
                .map(TextProcessor::removeAccents)
                .map(String::toLowerCase)
                .filter(word -> !stopWords.contains(word))
                .collect(Collectors.toList());

        // Retorna o resultado como array
        return processedWords.toArray(new String[0]);
        //return String.join(" ", processedWords);
    }

    // Método privado para carregar stop words do arquivo
    private Set<String> loadStopWords() throws IOException {
        Set<String> stopWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STOP_WORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stopWords.add(line.trim().toLowerCase());
            }
        }
        return stopWords;
    }

    // Método privado para remover acentos de uma string
    private static String removeAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public float calcularTF(String termo, String[] termos) {
        int contador = 0;
        for (String t : termos) {
            if (t.equals(termo)) {
                contador++;
            }
        }
        return (float) contador / termos.length;
    }

    public float calcularIDF(String termo) throws Exception {
        // Total de documentos (tarefas)
        int totalDocumentos = listaInvertida.numeroEntidades();

        // Obter o número de documentos que contém o termo
        ElementoLista[] elementos = listaInvertida.read(termo);
        int documentosContendoTermo = elementos.length;

        // Calcular o IDF
        if (documentosContendoTermo == 0) {
            return 0; // Se o termo não aparecer em nenhum documento, o IDF é 0
        }
        return (float) totalDocumentos / documentosContendoTermo;
    }
}
