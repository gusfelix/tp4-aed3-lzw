package model;

import aed3.*;

import java.util.ArrayList;

public class ArquivoCategoria extends aed3.Arquivo<Categoria> {

    private ArvoreBMais<ParNomeId> indiceIndiretoNome;

    public ArquivoCategoria() throws Exception {
        super("categorias", Categoria.class.getConstructor());
        indiceIndiretoNome = new ArvoreBMais<>(
                ParNomeId.class.getConstructor(),
                4,
                ".\\dados\\indiceNomeCategoria.db");
    }

    @Override
    public int create(Categoria c) throws Exception {
        int id = super.create(c);
        indiceIndiretoNome.create(new ParNomeId(c.getNome(), id));
        return id;
    }

    public Categoria read(String nome) throws Exception {
        ArrayList<ParNomeId> pcis = indiceIndiretoNome.read(new ParNomeId(nome));
        if (pcis.isEmpty()) {
            return null;
        }
        return super.read(pcis.get(0).getId());
    }

    public boolean delete(String nome) throws Exception {
        ArrayList<ParNomeId> pcis = indiceIndiretoNome.read(new ParNomeId(nome));
        if (pcis.isEmpty()) {
            return false;
        }
        ParNomeId pci = pcis.get(0);
        if (super.delete(pci.getId())) {
            return indiceIndiretoNome.delete(new ParNomeId(nome, pci.getId()));
        }
        return false;
    }

    @Override
    public boolean update(Categoria novaCategoria) throws Exception {
        Categoria categoriaAntiga = read(novaCategoria.getId());
        if (categoriaAntiga == null) {
            return false;
        }
        if (super.update(novaCategoria)) {
            if (!novaCategoria.getNome().equals(categoriaAntiga.getNome())) {
                indiceIndiretoNome.delete(new ParNomeId(categoriaAntiga.getNome(), categoriaAntiga.getId()));
                indiceIndiretoNome.create(new ParNomeId(novaCategoria.getNome(), novaCategoria.getId()));
            }
            return true;
        }
        return false;
    }

    public ArrayList<Categoria> readAll() throws Exception {
        ArrayList<Categoria> categorias = new ArrayList<>();
        for (ParNomeId pci : indiceIndiretoNome.read(null)) {
            categorias.add(super.read(pci.getId()));
        }
        return categorias;
    }
}
