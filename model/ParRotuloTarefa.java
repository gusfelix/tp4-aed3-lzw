package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParRotuloTarefa implements aed3.RegistroArvoreBMais<ParRotuloTarefa> {
    private int idRotulo;
    private int idTarefa;
    private short TAMANHO = 8;

    public ParRotuloTarefa() {
        this(-1, -1);
    }

    public ParRotuloTarefa(int idRotulo) {
        this(idRotulo, -1);
    }

    public ParRotuloTarefa(int idRotulo, int idTarefa) {
        try {
            this.idRotulo = idRotulo;
            this.idTarefa = idTarefa;
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    public int getRotulo() {
        return this.idRotulo;
    }

    public int getTarefa() {
        return this.idTarefa;
    }

    @Override
    public ParRotuloTarefa clone() {
        return new ParRotuloTarefa(this.idRotulo, this.idTarefa);
    }

    public short size() {
        return this.TAMANHO;
    }

    public int compareTo(ParRotuloTarefa a) {
        if (this.idRotulo != a.idRotulo)
            return this.idRotulo - a.idRotulo;
        else
            return this.idTarefa == -1 ? 0 : this.idTarefa - a.idTarefa;
    }

    public String toString() {
        return String.format("%3d", this.idRotulo) + ";" + String.format("%-3d", this.idTarefa);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idRotulo);
        dos.writeInt(this.idTarefa);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idRotulo = dis.readInt();
        this.idTarefa = dis.readInt();
    }
}
