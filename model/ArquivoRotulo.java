package model;

import java.util.*;

public class ArquivoRotulo {
    private Map<Integer, Rotulo> rotulos;
    private List<ParCategoriaId> relacoes;
    private int nextId;

    public ArquivoRotulo() {
        rotulos = new HashMap<>();
        relacoes = new ArrayList<>();
        nextId = 1;
    }

    public void incluir(Rotulo rotulo) {
        rotulo.setId(nextId++);
        rotulos.put(rotulo.getId(), rotulo);
    }

    public Rotulo buscar(int id) {
        return rotulos.get(id);
    }

    public void alterar(Rotulo rotulo) {
        rotulos.put(rotulo.getId(), rotulo);
    }

    public void excluir(int id) {
        rotulos.remove(id);
        relacoes.removeIf(relacao -> relacao.getIdCategoria() == id);
    }

    public List<Rotulo> listar() {
        return new ArrayList<>(rotulos.values());
    }

    public void adicionarTarefa(int idRotulo, int idTarefa) {
        relacoes.add(new ParCategoriaId(idRotulo, idTarefa));
    }

    public List<Integer> listarTarefas(int idRotulo) {
        List<Integer> tarefas = new ArrayList<>();
        for (ParCategoriaId relacao : relacoes) {
            if (relacao.getIdCategoria() == idRotulo) {
                tarefas.add(relacao.getId());
            }
        }
        return tarefas;
    }
}