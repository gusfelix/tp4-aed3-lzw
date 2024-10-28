import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParCategoriaId implements aed3.RegistroArvoreBMais<ParCategoriaId> {

  private int id;
  private int idCategoria;
  private short TAMANHO = 8;

  public ParCategoriaId() {
    this(-1, -1);
  }

  public ParCategoriaId(int id) {
    this(id, -1);
  }

  public ParCategoriaId(int id, int idCategoria) {
    try {
      this.id = id;
      this.idCategoria = idCategoria;
    } catch (Exception ec) {
      ec.printStackTrace();
    }
  }

  public int getId() {
    return this.id;
  }

  public int getIdCategoria() {
    return this.idCategoria;
  } 

  @Override
  public ParCategoriaId clone() {
    return new ParCategoriaId(this.id, this.idCategoria);
  }

  public short size() {
    return this.TAMANHO;
  }

  public int compareTo(ParCategoriaId a) {
    if (this.id == -1 || a.id == -1) {
        return this.idCategoria == -1 ? 0 : this.idCategoria - a.idCategoria;
    }
    if (this.id != a.id) {
        return this.id - a.id;
    } else {
        return this.idCategoria - a.idCategoria;
    }
  }

  public String toString() {
    return String.format("%3d", this.id) + ";" + String.format("%-3d", this.idCategoria);
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.id);
    dos.writeInt(this.idCategoria);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    this.id = dis.readInt();
    this.idCategoria = dis.readInt();
  }

}