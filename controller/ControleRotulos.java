package controller;

import java.util.List;

import model.ArquivoRotulo;
import model.Rotulo;
import view.VisaoRotulos;
import java.util.List;

public class ControleRotulos {
    private ArquivoRotulo arquivoRotulo;
    private VisaoRotulos visaoRotulos;
    private ControleTarefas controleTarefas;

    public ControleRotulos(ControleTarefas controleTarefas) {
        this.arquivoRotulo = new ArquivoRotulo();
        this.controleTarefas = controleTarefas;
        this.visaoRotulos = new VisaoRotulos(this, controleTarefas);
    }

    public void iniciar() {
        visaoRotulos.menu();
    }

    public void incluirRotulo(String rotulo) {
        Rotulo novoRotulo = new Rotulo(rotulo);
        arquivoRotulo.incluir(novoRotulo);
    }

    public Rotulo buscarRotulo(int id) {
        return arquivoRotulo.buscar(id);
    }

    public void alterarRotulo(int id, String novoRotulo) {
        Rotulo rotulo = arquivoRotulo.buscar(id);
        if (rotulo != null) {
            rotulo.setRotulo(novoRotulo);
            arquivoRotulo.alterar(rotulo);
        }
    }

    public void excluirRotulo(int id) {
        arquivoRotulo.excluir(id);
    }

    public List<Rotulo> listarRotulos() {
        return arquivoRotulo.listar();
    }

    public List<Integer> listarTarefas(int idRotulo) {
        return arquivoRotulo.listarTarefas(idRotulo);
    }
}