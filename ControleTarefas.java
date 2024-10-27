import java.util.ArrayList;

public class ControleTarefas {
    private ArquivoTarefa arqTarefas;
    private VisaoTarefas visaoTarefas;

    public ControleTarefas() throws Exception {
        arqTarefas = new ArquivoTarefa();
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
                    alterarTarefa();
                    break;
                case 4:
                    excluirTarefa();
                    break;
                case 5:
                    listarTarefas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void incluirTarefa() throws Exception {
        Tarefa novaTarefa = visaoTarefas.leTarefa();
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

    private void alterarTarefa() throws Exception {
        int id = visaoTarefas.leIdTarefa();
        Tarefa tarefaAntiga = arqTarefas.read(id);
        if (tarefaAntiga != null) {
            Tarefa novaTarefa = visaoTarefas.leTarefa();
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

    private void listarTarefas() {
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
}
