package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParCategoriaId implements aed3.RegistroArvoreBMais<ParCategoriaId> {

  private int idTarefa;
  private int idCategoria;
  private short TAMANHO = 8;

  public ParCategoriaId() {
    this(-1, -1);
  }

  public ParCategoriaId(int idCategoria) {
    this(-1, idCategoria);
  }

  public ParCategoriaId(int idTarefa, int idCategoria) {
    try {
      this.idTarefa = idTarefa;
      this.idCategoria = idCategoria;
    } catch (Exception ec) {
      ec.printStackTrace();
    }
  }

  public int getIdTarefa() {
    return this.idTarefa;
  }

  public int getIdCategoria() {
    return this.idCategoria;
  }

  @Override
  public ParCategoriaId clone() {
    return new ParCategoriaId(this.idTarefa, this.idCategoria);
  }

  public short size() {
    return this.TAMANHO;
  }

  public int compareTo(ParCategoriaId a) {
    if (this.idCategoria != a.idCategoria)
      return this.idCategoria - a.idCategoria;
    else
      return this.idTarefa == -1 ? 0 : this.idTarefa - a.idTarefa;
  }

  public String toString() {
    return String.format("%3d", this.idTarefa) + ";" + String.format("%-3d", this.idCategoria);
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idTarefa);
    dos.writeInt(this.idCategoria);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.idTarefa = dis.readInt();
    this.idCategoria = dis.readInt();
  }
}
