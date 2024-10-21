import aed3.*;

public class ArquivoCategoria extends aed3.Arquivo<Categoria> {

    private ArvoreBMais<ParNomeId> indiceIndiretoNome;

    public ArquivoCategoria() throws Exception {
        super("categorias", Categoria.class.getConstructor());
        indiceIndiretoNome = new ArvoreBMais<>(
            ParNomeId.class.getConstructor(), 
            4,
            ".\\dados\\indiceNome.db"
        );
    }

    @Override
    public int create(Categoria c) throws Exception {
        int id = super.create(c);
        indiceIndiretoNome.create(new ParNomeId(c.getNome(), id));
        return id;
    }

    public Categoria read(String nome) throws Exception {
        ParNomeId pci = indiceIndiretoNome.read(new ParNomeId(nome)).get(0);
        if (pci == null) {
            return null;
        }
        return read(pci.getId());
    }
    
    public boolean delete(String nome) throws Exception {
        ParNomeId pci = indiceIndiretoNome.read(new ParNomeId(nome)).get(0);
        if (pci != null) {
            if (delete(pci.getId())) {
                return indiceIndiretoNome.delete(new ParNomeId(nome, pci.getId()));
            }
        }
        return false;
    }

    @Override
    public boolean update(Categoria novaCategoria) throws Exception {
        Categoria categoriaAntiga = read(novaCategoria.getNome());
        if (super.update(novaCategoria)) {
            if (!novaCategoria.getNome().equals(categoriaAntiga.getNome())) {
                indiceIndiretoNome.delete(new ParNomeId(categoriaAntiga.getNome(), categoriaAntiga.getId()));
                indiceIndiretoNome.create(new ParNomeId(novaCategoria.getNome(), novaCategoria.getId()));
            }
            return true;
        }
        return false;
    }
}
