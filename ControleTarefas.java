import java.util.ArrayList;

public class ControleTarefas {
    private ArquivoTarefa arqTarefas;
    private ArquivoCategoria arqCategorias;
    private VisaoTarefas visaoTarefas;

    public ControleTarefas() throws Exception {
        arqTarefas = new ArquivoTarefa();
        arqCategorias = new ArquivoCategoria();
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
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void incluirTarefa() throws Exception {
        ArrayList<Categoria> categorias = arqCategorias.readAll();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria antes de incluir uma tarefa.");
            return;
        }

        visaoTarefas.mostrarCategorias(categorias);

        int numeroCategoria = visaoTarefas.selecionaCategoria(categorias.size());
        int idCategoria = categorias.get(numeroCategoria - 1).getId();

        Tarefa novaTarefa = visaoTarefas.leTarefa(idCategoria);
        int idGerado = arqTarefas.create(novaTarefa);
        novaTarefa.setId(idGerado);
        System.out.println("Tarefa incluída com sucesso.");
    }

    private void buscarTarefa() throws Exception {
        int id = visaoTarefas.leIdTarefa();
        Tarefa tarefa = arqTarefas.read(id);
        if (tarefa != null) {
            visaoTarefas.mostraTarefa(tarefa);
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private void buscarTarefasPorTermos(String busca) {
        try {
            ArrayList<Tarefa> tarefas = arqTarefas.readAllByTerms(busca);
            if (tarefas.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada.");
            } else {
                for (Tarefa tarefa : tarefas) {
                    visaoTarefas.mostraTarefa(tarefa);
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
            visaoTarefas.mostrarCategorias(categorias);

            int numeroCategoria = visaoTarefas.selecionaCategoria(categorias.size());
            int idCategoria = categorias.get(numeroCategoria - 1).getId();

            Tarefa novaTarefa = visaoTarefas.leTarefa(idCategoria);
            novaTarefa.setId(id);
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
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    private void  listarTarefasPorCategoria() {
        try {
            int idCategoria = visaoTarefas.leIdCategoria();
            ArrayList<Tarefa> tarefas = arqTarefas.readByCategoria(idCategoria);
            if (tarefas.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada para a categoria informada.");
            } else {
                for (Tarefa tarefa : tarefas) {
                    visaoTarefas.mostraTarefa(tarefa);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas por categoria: " + e.getMessage());
        }
    }
}
