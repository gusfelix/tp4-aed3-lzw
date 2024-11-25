package model;

import aed3.*;
import util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArquivoTarefa extends aed3.Arquivo<Tarefa> {

    private ArvoreBMais<ParCategoriaId> indiceIndiretoCategoria;
    /* TODO descomentar linhas abaixo */
    // private ArvoreBMais<ParRotuloTarefa> indiceIndiretoRotulo;
    // private ArvoreBMais<ParTarefaRotulo> indiceIndiretoTarefa;
    private ListaInvertida listaInvertida;

    /*
     * TODO adicionar no construtor a inicialização de indiceIndiretoRotulo e
     * indiceIndiretoTarefa
     */
    public ArquivoTarefa() throws Exception {
        super("tarefas", Tarefa.class.getConstructor());
        indiceIndiretoCategoria = new ArvoreBMais<>(
                ParCategoriaId.class.getConstructor(),
                4,
                ".\\dados\\indiceCategoria.db");
        listaInvertida = new ListaInvertida(
                4,
                "dados/dicionario.listainv.db",
                "dados/blocos.listainv.db");
    }

    /*
     * TODO adicionar parametro 'rotulos' do tipo ArrayList<Rotulo> para lidar com a
     * criação de rótulos. Adicionar em 'create' associção de rótulos a tarefas,
     * criando um
     * registro em indiceIndiretoRotulo e em indiceIndiretoTarefa para cada rótulo
     * associado à nova tarefa
     */
    @Override
    public int create(Tarefa t) throws Exception {

        int id = super.create(t);
        TextProcessor txtPcsr = new TextProcessor();
        String[] termos = txtPcsr.processText(t.getNome());

        for (String termo : termos) {
            listaInvertida.create(termo, new ElementoLista(id, txtPcsr.calcularTF(termo, termos)));
        }

        listaInvertida.incrementaEntidades();

        indiceIndiretoCategoria.create(new ParCategoriaId(id, t.getIdCategoria()));
        return id;
    }

    public ArrayList<Tarefa> readByCategoria(int categoriaId) throws Exception {
        ArrayList<ParCategoriaId> pciList = indiceIndiretoCategoria.read(new ParCategoriaId(categoriaId));
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        if (pciList.isEmpty()) {
            return tarefas;
        }

        for (ParCategoriaId pci : pciList) {
            Tarefa tarefa = read(pci.getIdTarefa());
            if (tarefa != null) {
                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }

    public ArrayList<Tarefa> readByRotulo(int rotuloId) throws Exception {
        ArrayList<ParCategoriaId> pciList = indiceIndiretoCategoria.read(new ParCategoriaId(-1, rotuloId));
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        if (pciList.isEmpty()) {
            return tarefas;
        }

        for (ParCategoriaId pci : pciList) {
            Tarefa tarefa = read(pci.getIdTarefa());
            if (tarefa != null) {
                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }

    /*
     * TODO ao excliuir uma tarefa, excluir também os registros associados em
     * indiceIndiretoTarefa e indiceIndiretoRotulo
     */
    public boolean delete(int tarefaId) throws Exception {
        Tarefa tarefa = read(tarefaId);

        if (tarefa == null) {
            return false;
        }

        TextProcessor txtPcsr = new TextProcessor();
        String[] termos = txtPcsr.processText(tarefa.getNome());

        for (String termo : termos) {
            listaInvertida.delete(termo, tarefa.getId());
        }

        listaInvertida.decrementaEntidades();

        boolean removed = super.delete(tarefaId);
        if (removed) {
            indiceIndiretoCategoria.delete(new ParCategoriaId(tarefaId, tarefa.getIdCategoria()));
        }

        return removed;
    }

    /*
     * TODO adicionar no método 'update' a atualização dos rótulos associados à
     * tarefa utilizando os índices indiretos indiceIndiretoRotulo e
     * indiceIndiretoTarefa
     */
    @Override
    public boolean update(Tarefa novaTarefa) throws Exception {
        Tarefa tarefaAntiga = read(novaTarefa.getId());
        if (super.update(novaTarefa)) {
            if (tarefaAntiga != null && novaTarefa.getIdCategoria() != tarefaAntiga.getIdCategoria()) {
                indiceIndiretoCategoria.delete(new ParCategoriaId(tarefaAntiga.getId(), tarefaAntiga.getIdCategoria()));
                indiceIndiretoCategoria.create(new ParCategoriaId(novaTarefa.getId(), novaTarefa.getIdCategoria()));
            }
            return true;
        }
        return false;
    }

    public ArrayList<Tarefa> readAll() throws Exception {

        ArrayList<Tarefa> tarefas = new ArrayList<>();
        for (ParCategoriaId pci : indiceIndiretoCategoria.read(null)) {
            Tarefa tarefa = super.read(pci.getIdTarefa());
            if (tarefa != null) {
                tarefas.add(tarefa);
            }
        }
        return tarefas;
    }

    public ArrayList<Tarefa> readAllByTerms(String busca) throws Exception {

        TextProcessor txtPcsr = new TextProcessor();
        String[] termos = txtPcsr.processText(busca);

        ElementoLista elementoTmp;
        ArrayList<ElementoLista> listaFinal = new ArrayList<>();

        for (String termo : termos) {
            float IDF = txtPcsr.calcularIDF(termo);
            ElementoLista[] listaDados = listaInvertida.read(termo);

            for (ElementoLista elemento : listaDados) {
                elementoTmp = new ElementoLista(elemento.getId(), elemento.getFrequencia() * IDF);
                listaFinal.add(elementoTmp);
            }
        }

        Map<Integer, Float> mapSomas = new HashMap<>();

        // Somar as frequências por id
        for (ElementoLista elemento : listaFinal) {
            mapSomas.put(elemento.getId(), mapSomas.getOrDefault(elemento.getId(), 0.0f) + elemento.getFrequencia());
        }

        // Criar uma lista de novos ElementosLista com id único e frequências somadas
        ArrayList<ElementoLista> resultado = new ArrayList<>();
        for (Map.Entry<Integer, Float> entry : mapSomas.entrySet()) {
            resultado.add(new ElementoLista(entry.getKey(), entry.getValue()));
        }

        resultado.sort((e1, e2) -> Float.compare(e2.getFrequencia(), e1.getFrequencia()));

        // for (ElementoLista elemento : resultado) {
        // System.out.println("("+ elemento.getId() +", "+ elemento.getFrequencia()
        // +")");
        // }

        ArrayList<Tarefa> tarefas = new ArrayList<>();
        for (ElementoLista elemento : resultado) {
            Tarefa tarefa = read(elemento.getId()); // Obter a tarefa pelo ID
            if (tarefa != null) {
                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }

    /*
     * TODO implementar método readRotulosByTarefa que recebe o ID de uma tarefa e
     * busca em indiceIndiretoTarefa todos os registros com esse ID e retorna os
     * rotulos associados do tipo ArrayList<Rotulo>
     */
    public ArrayList<Rotulo> readRotulosByTarefa(int tarefaId) throws Exception {
        return null;
    }
}
