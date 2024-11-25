package controller;

import java.util.ArrayList;

import model.*;
import view.VisaoRotulos;

public class ControleRotulos {
    private ArquivoRotulo arquivoRotulo;
    private VisaoRotulos visaoRotulos;

    public ControleRotulos(ControleTarefas controleTarefas) throws Exception {
        this.arquivoRotulo = new ArquivoRotulo();
        this.visaoRotulos = new VisaoRotulos(this, controleTarefas);
    }

    public void iniciar() {
        visaoRotulos.menu();
    }

    public void incluirRotulo(String rotulo) throws Exception {
        Rotulo novoRotulo = new Rotulo(rotulo);
        arquivoRotulo.create(novoRotulo);
    }

    public Rotulo buscarRotulo(String rotulo) throws Exception {
        return arquivoRotulo.read(rotulo);
    }

    public void alterarRotulo(String antigoRotulo, String novoRotulo) throws Exception {
        Rotulo rotulo = arquivoRotulo.read(antigoRotulo);
        if (rotulo != null) {
            rotulo.setRotulo(novoRotulo);
            arquivoRotulo.update(rotulo);
        }
    }

    public void excluirRotulo(String rotulo) throws Exception {
        arquivoRotulo.delete(rotulo);
    }

    public ArrayList<Rotulo> listarRotulos() throws Exception {
        return arquivoRotulo.readAll();
    }

    public ArrayList<Tarefa> listarTarefas(String rotulo) throws Exception {
        return arquivoRotulo.readTarefasByRotulo(rotulo);
    }
}