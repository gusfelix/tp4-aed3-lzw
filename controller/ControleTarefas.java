package controller;

import java.util.ArrayList;

import model.*;
import view.VisaoTarefas;

public class ControleTarefas {
    private ArquivoTarefa arqTarefas;
    private ArquivoCategoria arqCategorias;
    private ArquivoRotulo arqRotulos;
    private VisaoTarefas visaoTarefas;

    public ControleTarefas() throws Exception {
        arqTarefas = new ArquivoTarefa();
        arqCategorias = new ArquivoCategoria();
        arqRotulos = new ArquivoRotulo();
        visaoTarefas = new VisaoTarefas();
    }

    public void iniciar() throws Exception {
        int opcao;
        do {
            opcao = visaoTarefas.menu();
            switch (opcao) {
                case 1:
                    incluirTarefa();
                    break;
                case 2:
                    buscarTarefa();
                    break;
                case 3:
                    String busca = visaoTarefas.leBusca();
                    buscarTarefasPorTermos(busca);
                    break;
                case 4:
                    alterarTarefa();
                    break;
                case 5:
                    excluirTarefa();
                    break;
                case 6:
                    listarTarefas();
                    break;
                case 7:
                    listarTarefasPorCategoria();
                    break;
                case 8:
                    listarTarefasPorRotulo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void incluirTarefa() throws Exception {
        ArrayList<Categoria> categorias = arqCategorias.readAll();
        ArrayList<Rotulo> rotulos = arqRotulos.readAll();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria antes de incluir uma tarefa.");
            return;
        }

        visaoTarefas.mostrarCategorias(categorias);
        int numeroCategoria = visaoTarefas.selecionaCategoria(categorias.size());
        int idCategoria = categorias.get(numeroCategoria - 1).getId();

        ArrayList<Integer> idsRotulos = new ArrayList<>();
        if (rotulos.size() > 0) {
            visaoTarefas.mostrarRotulos(rotulos);
            int[] rotulosSelecionados = visaoTarefas.selecionaMultiplosRotulos(rotulos.size());
            for (int i : rotulosSelecionados) {
                idsRotulos.add(rotulos.get(i - 1).getId());
            }
        } else {
            System.out.println("Nenhum rótulo cadastrado.");
        }

        Tarefa novaTarefa = visaoTarefas.leTarefa(idCategoria);
        int idGerado = arqTarefas.create(novaTarefa, idsRotulos);
        novaTarefa.setId(idGerado);
        System.out.println("Tarefa incluída com sucesso.");
    }

    private void buscarTarefa() throws Exception {
        int id = visaoTarefas.leIdTarefa();
        Tarefa tarefa = arqTarefas.read(id);
        if (tarefa != null) {
            visaoTarefas.mostraTarefa(tarefa);
            visaoTarefas.mostrarRotulos(arqTarefas.readRotulosByTarefa(tarefa.getId()));
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    public Tarefa buscarTarefa(int id) throws Exception {
        return arqTarefas.read(id);
    }

    private void buscarTarefasPorTermos(String busca) {
        try {
            ArrayList<Tarefa> tarefas = arqTarefas.readAllByTerms(busca);
            if (tarefas.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada.");
            } else {
                for (Tarefa tarefa : tarefas) {
                    visaoTarefas.mostraTarefa(tarefa);
                    visaoTarefas.mostrarRotulos(arqTarefas.readRotulosByTarefa(tarefa.getId()));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    private void alterarTarefa() throws Exception {
        int id = visaoTarefas.leIdTarefa();
        Tarefa tarefaAntiga = arqTarefas.read(id);
        if (tarefaAntiga != null) {
            ArrayList<Categoria> categorias = arqCategorias.readAll();
            ArrayList<Rotulo> rotulos = arqRotulos.readAll();
            ArrayList<Rotulo> rotulosTarefa = arqTarefas.readRotulosByTarefa(tarefaAntiga.getId());

            Tarefa novaTarefa = visaoTarefas.editaTarefa(tarefaAntiga, categorias);
            int[] rotulosSelecionadosParaAdicionar = visaoTarefas.adicionaRotulosTarefa(rotulos);

            for (int i : rotulosSelecionadosParaAdicionar) {
                Rotulo rotulo = rotulos.get(i - 1);

                if (rotulosTarefa.contains(rotulo) == false) {
                    rotulosTarefa.add(rotulo);
                    arqTarefas.newParTarefaRotulo(novaTarefa.getId(), rotulo.getId());
                }
            }

            int[] rotulosSelecionadosParaRemover = visaoTarefas.removeRotulosTarefa(rotulosTarefa);

            for (int i : rotulosSelecionadosParaRemover) {
                Rotulo rotulo = rotulosTarefa.get(i - 1);
                rotulosTarefa.remove(rotulo);
                arqTarefas.deleteParTarefaRotulo(novaTarefa.getId(), rotulo.getId());
            }

            if (arqTarefas.update(novaTarefa)) {
                System.out.println("Tarefa alterada com sucesso.");
            } else {
                System.out.println("Erro ao atualizar a tarefa.");
            }
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private void excluirTarefa() throws Exception {
        int id = visaoTarefas.leIdTarefa();
        if (arqTarefas.delete(id)) {
            System.out.println("Tarefa excluída com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private void listarTarefas() throws Exception {
        try {
            ArrayList<Tarefa> tarefas = arqTarefas.readAll();
            if (tarefas.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada.");
            } else {
                for (Tarefa tarefa : tarefas) {
                    visaoTarefas.mostraTarefa(tarefa);
                    visaoTarefas.mostrarRotulos(arqTarefas.readRotulosByTarefa(tarefa.getId()));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    private int listarCategorias(boolean selecionaCategoria) {
        try {
            ArrayList<Categoria> categorias = arqCategorias.readAll();
            if (categorias.isEmpty()) {
                System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria antes de continuar.");
                return -1;
            }
            visaoTarefas.mostrarCategorias(categorias);

            if (selecionaCategoria) {
                int numeroCategoria = visaoTarefas.selecionaCategoria(categorias.size());
                int idCategoria = categorias.get(numeroCategoria - 1).getId();
                return idCategoria;
            }

            return -1;
        } catch (Exception e) {
            System.out.println("Erro ao listar ou encontrar categoria(s): " + e.getMessage());
            return -1;
        }
    }

    private int listarRotulos(boolean selecionaRotulo) {
        try {
            ArrayList<Rotulo> rotulos = arqRotulos.readAll();
            if (rotulos.isEmpty()) {
                System.out.println("Nenhum rótulo cadastrado. Cadastre um rótulo antes de continuar.");
                return -1;
            }
            visaoTarefas.mostrarRotulos(rotulos);

            if (selecionaRotulo) {
                int numeroRotulo = visaoTarefas.selecionaRotulo(rotulos.size());
                int idRotulo = rotulos.get(numeroRotulo - 1).getId();
                return idRotulo;
            }

            return -1;
        } catch (Exception e) {
            System.out.println("Erro ao listar ou encontrar rotulo(s): " + e.getMessage());
            return -1;
        }
    }

    private void listarTarefasPorCategoria() {
        try {
            int idCategoria = listarCategorias(true);

            if (idCategoria != -1) {
                ArrayList<Tarefa> tarefas = arqTarefas.readByCategoria(idCategoria);

                if (tarefas.isEmpty()) {
                    System.out.println("Nenhuma tarefa encontrada para a categoria informada.");
                } else {
                    for (Tarefa tarefa : tarefas) {
                        visaoTarefas.mostraTarefa(tarefa);
                        visaoTarefas.mostrarRotulos(arqTarefas.readRotulosByTarefa(tarefa.getId()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas por categoria: " + e.getMessage());
        }
    }

    private void listarTarefasPorRotulo() {
        try {
            int idRotulo = listarRotulos(true);

            if (idRotulo != -1) {
                ArrayList<Tarefa> tarefas = arqTarefas.readByRotulo(idRotulo);

                if (tarefas.isEmpty()) {
                    System.out.println("Nenhuma tarefa encontrada para o rótulo informado.");
                } else {
                    for (Tarefa tarefa : tarefas) {
                        visaoTarefas.mostraTarefa(tarefa);
                        visaoTarefas.mostrarRotulos(arqTarefas.readRotulosByTarefa(tarefa.getId()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas por rótulo: " + e.getMessage());
        }
    }
}
