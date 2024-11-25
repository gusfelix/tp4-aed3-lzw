package model;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import aed3.Registro;

public class Rotulo implements Registro {

    public int id;
    public String rotulo;

    public Rotulo() {
        this(-1, "");
    }

    public Rotulo(String n) {
        this(-1, n);
    }

    public Rotulo(int i, String n) {
        this.id = i;
        this.rotulo = n;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }

    public String toString() {
        return "\nID..: " + this.id +
                "\nRotulo: " + this.rotulo;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.rotulo);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.rotulo = dis.readUTF();
    }
}
