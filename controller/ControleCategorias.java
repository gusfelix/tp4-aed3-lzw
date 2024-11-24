package controller;

import java.util.ArrayList;

import model.ArquivoCategoria;
import model.ArquivoTarefa;
import model.Categoria;
import view.VisaoCategorias;

public class ControleCategorias {
    private ArquivoCategoria arqCategorias;
    private ArquivoTarefa arqTarefas;
    private VisaoCategorias visaoCategorias;

    public ControleCategorias() throws Exception {
        arqCategorias = new ArquivoCategoria();
        arqTarefas = new ArquivoTarefa();
        visaoCategorias = new VisaoCategorias();
    }

    public void iniciar() throws Exception {
        int opcao;
        do {
            opcao = visaoCategorias.menu();
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
                case 5:
                    listarCategorias();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void incluirCategoria() throws Exception {
        String nome = visaoCategorias.leNomeCategoria();

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido. A operação foi cancelada.");
            return;
        }

        Categoria categoria = new Categoria(nome);
        arqCategorias.create(categoria);
        System.out.println("Categoria incluída com sucesso.");
    }

    private void buscarCategoria() throws Exception {
        String nome = visaoCategorias.leNomeCategoria();
        Categoria categoria = arqCategorias.read(nome);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
        } else {
            visaoCategorias.mostraCategoria(categoria);
        }
    }

    private void alterarCategoria() throws Exception {
        String nomeAtual = visaoCategorias.leNomeCategoria();
        Categoria categoriaAntiga = arqCategorias.read(nomeAtual);

        if (categoriaAntiga == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        String novoNome = visaoCategorias.leNomeCategoria();

        if (novoNome == null || novoNome.trim().isEmpty()) {
            System.out.println("Nome inválido. A operação foi cancelada.");
            return;
        }

        Categoria novaCategoria = new Categoria(novoNome);
        novaCategoria.setId(categoriaAntiga.getId());

        boolean sucesso = arqCategorias.update(novaCategoria);

        if (sucesso) {
            System.out.println("Categoria alterada com sucesso.");
        } else {
            System.out.println("Erro ao alterar a categoria.");
        }
    }

    private void excluirCategoria() throws Exception {
        String nome = visaoCategorias.leNomeCategoria();
        Categoria categoria = arqCategorias.read(nome);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }
        int qntTarefas = arqTarefas.readByCategoria(categoria.getId()).size();
        if (qntTarefas < 1) {
            boolean isDeleted = arqCategorias.delete(nome);
            if (isDeleted) {
                System.out.println("Categoria excluída com sucesso.");
            } else {
                System.out.println("Erro ao excluir categoria.");
            }
        } else {
            System.out.println("Não é possível excluir uma categoria com tarefas associadas.");
        }
    }

    public void listarCategorias() {
        try {
            ArrayList<Categoria> categorias = arqCategorias.readAll();
            if (categorias.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada.");
            } else {
                for (Categoria categoria : categorias) {
                    visaoCategorias.mostraCategoria(categoria);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar categorias.");
            return;
        }
    }
}
