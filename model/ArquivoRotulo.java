package model;

import aed3.*;
import java.util.ArrayList;

public class ArquivoRotulo extends aed3.Arquivo<Rotulo> {

    private ArvoreBMais<ParNomeId> indiceIndiretoNome;
    private ArvoreBMais<ParRotuloTarefa> indiceIndiretoRotuloTarefa;
    private ArvoreBMais<ParTarefaRotulo> indiceIndiretoTarefaRotulo;
    private ArquivoTarefa arqTarefa;

    public ArquivoRotulo() throws Exception {
        super("rotulos", Rotulo.class.getConstructor());
        indiceIndiretoNome = new ArvoreBMais<>(
                ParNomeId.class.getConstructor(),
                4,
                ".\\dados\\indiceNomeRotulo.db");
        indiceIndiretoRotuloTarefa = new ArvoreBMais<>(
                ParRotuloTarefa.class.getConstructor(),
                4,
                ".\\dados\\indiceRotuloTarefa.db");
        indiceIndiretoTarefaRotulo = new ArvoreBMais<>(
                ParTarefaRotulo.class.getConstructor(),
                4,
                ".\\dados\\indiceTarefaRotulo.db");
        arqTarefa = new ArquivoTarefa();
    }

    @Override
    public int create(Rotulo r) throws Exception {
        int id = super.create(r);
        return id;
    }

    public Rotulo read(String nome) throws Exception {
        ArrayList<ParNomeId> paresRotuloId = indiceIndiretoNome.read(new ParNomeId(nome));
        if (paresRotuloId.isEmpty()) {
            return null;
        }
        return super.read(paresRotuloId.get(0).getId());
    }

    public boolean delete(String nome) throws Exception {
        ArrayList<ParNomeId> paresRotuloId = indiceIndiretoNome.read(new ParNomeId(nome));

        if (paresRotuloId.isEmpty()) {
            return false;
        }

        ParNomeId parNomeId = paresRotuloId.get(0);

        ArrayList<ParRotuloTarefa> paresRotuloTarefa = indiceIndiretoRotuloTarefa
                .read(new ParRotuloTarefa(parNomeId.getId()));

        for (ParRotuloTarefa parRotuloTarefa : paresRotuloTarefa) {
            indiceIndiretoRotuloTarefa.delete(parRotuloTarefa);
            indiceIndiretoTarefaRotulo
                    .delete(new ParTarefaRotulo(parRotuloTarefa.getTarefa(), parRotuloTarefa.getRotulo()));
        }

        if (super.delete(parNomeId.getId())) {
            return indiceIndiretoNome.delete(new ParNomeId(nome, parNomeId.getId()));
        }

        return false;
    }

    @Override
    public boolean update(Rotulo novoRotulo) throws Exception {
        Rotulo rotuloAntigo = read(novoRotulo.getId());
        if (rotuloAntigo == null) {
            return false;
        }
        if (super.update(novoRotulo)) {
            if (!novoRotulo.getRotulo().equals(rotuloAntigo.getRotulo())) {
                indiceIndiretoNome.delete(new ParNomeId(rotuloAntigo.getRotulo(), rotuloAntigo.getId()));
                indiceIndiretoNome.create(new ParNomeId(novoRotulo.getRotulo(), novoRotulo.getId()));
            }
            return true;
        }
        return false;
    }

    public ArrayList<Rotulo> readAll() throws Exception {
        ArrayList<Rotulo> rotulos = new ArrayList<>();
        for (ParNomeId parNomeId : indiceIndiretoNome.read(null)) {
            rotulos.add(super.read(parNomeId.getId()));
        }
        return rotulos;
    }

    public ArrayList<Tarefa> readTarefasByRotulo(String nome) throws Exception {
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        ArrayList<ParNomeId> paresRotuloId = indiceIndiretoNome.read(new ParNomeId(nome));
        if (paresRotuloId.isEmpty()) {
            return tarefas;
        }
        ParNomeId parNomeId = paresRotuloId.get(0);

        ArrayList<ParRotuloTarefa> paresRotuloTarefa = indiceIndiretoRotuloTarefa
                .read(new ParRotuloTarefa(parNomeId.getId()));

        for (ParRotuloTarefa parRotuloTarefa : paresRotuloTarefa) {
            Tarefa tarefa = arqTarefa.read(parRotuloTarefa.getTarefa());
            if (tarefa != null) {
                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }
}
