package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParTarefaRotulo implements aed3.RegistroArvoreBMais<ParTarefaRotulo> {
    private int idTarefa;
    private int idRotulo;
    private short TAMANHO = 8;

    public ParTarefaRotulo() {
        this(-1, -1);
    }

    public ParTarefaRotulo(int idTarefa) {
        this(idTarefa, -1);
    }

    public ParTarefaRotulo(int idTarefa, int idRotulo) {
        try {
            this.idTarefa = idTarefa;
            this.idRotulo = idRotulo;
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    public int getTarefa() {
        return this.idTarefa;
    }

    public int getRotulo() {
        return this.idRotulo;
    }

    @Override
    public ParTarefaRotulo clone() {
        return new ParTarefaRotulo(this.idTarefa, this.idRotulo);
    }

    public short size() {
        return this.TAMANHO;
    }

    public int compareTo(ParTarefaRotulo a) {
        if (this.idTarefa != a.idTarefa)
            return this.idTarefa - a.idTarefa;
        else
            return this.idRotulo == -1 ? 0 : this.idRotulo - a.idRotulo;
    }

    public String toString() {
        return String.format("%3d", this.idTarefa) + ";" + String.format("%-3d", this.idRotulo);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idTarefa);
        dos.writeInt(this.idRotulo);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idTarefa = dis.readInt();
        this.idRotulo = dis.readInt();
    }
}
