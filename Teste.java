import java.io.*;
import java.time.LocalDate;

import aed3.Arquivo;

public class Teste {

  // Arquivo declarado fora de main() para ser poder ser usado por outros métodos
  private static Arquivo<Tarefa> arqTarefas;

  public static void main(String[] args) {

    // Tarefas de exemplo
    Tarefa t1 = new Tarefa(-1, "Tarefa 1", LocalDate.now(), null, (short) 0, (short) 1);
    Tarefa t2 = new Tarefa(-1, "Tarefa 2", LocalDate.now(), null, (short) 0, (short) 2);
    Tarefa t3 = new Tarefa(-1, "Tarefa 3", LocalDate.now(), null, (short) 0, (short) 3);
    int id1, id2, id3;

    try {

      // Abre (cria) o arquivo de tarefas
      new File("tarefas.db").delete();  // apaga o arquivo anterior (apenas para testes)
      arqTarefas = new Arquivo<>("tarefas", Tarefa.class.getConstructor());

      // Insere as três tarefas
      id1 = arqTarefas.create(t1); 
      t1.setId(id1);
      id2 = arqTarefas.create(t2);
      t2.setId(id2);
      id3 = arqTarefas.create(t3);
      t3.setId(id3);

      // Busca por duas tarefas
      System.out.println(arqTarefas.read(id3));
      System.out.println(arqTarefas.read(id1));

      // Altera uma tarefa e exibe o resultado
      t2.setNome("Tarefa 2 Atualizada");
      arqTarefas.update(t2);
      System.out.println(arqTarefas.read(id2));

      // Altera outra tarefa e exibe o resultado
      t1.setNome("Tarefa 1 Atualizada");
      arqTarefas.update(t1);
      System.out.println(arqTarefas.read(id1));

      // Excluir uma tarefa e mostra que não existe mais
      arqTarefas.delete(id3);
      Tarefa t = arqTarefas.read(id3);
      if(t == null)
        System.out.println("Tarefa excluída");
      else
        System.out.println(t);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}