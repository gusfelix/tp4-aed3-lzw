package model;
import aed3.Registro;
import java.time.LocalDate;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class Tarefa implements Registro {

    // Atributos da classe
    public int id;
    public int idCategoria;
    public String nome;
    public LocalDate criacao;
    public LocalDate conclusao;
    public short status;
    public short prioridade;

    // Construtores
    public Tarefa() {
        this(-1, -1, "", LocalDate.now(), null, (short) 0, (short) 0);
    }

    public Tarefa(int idCategoria, String nome, LocalDate criacao, short status, short prioridade) {
        this(-1, idCategoria, nome, criacao, null, status, prioridade);
    }

    public Tarefa(int id, int idCategoria, String nome, LocalDate criacao, LocalDate conclusao, short status, short prioridade) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.criacao = criacao;
        this.conclusao = conclusao;
        this.status = status;
        this.prioridade = prioridade;
    }

    // Métodos Getter e Setter
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCriacao(LocalDate criacao) {
        this.criacao = criacao;
    }

    public LocalDate getCriacao() {
        return criacao;
    }

    public void setConclusao(LocalDate conclusao) {
        this.conclusao = conclusao;
    }

    public LocalDate getConclusao() {
        return conclusao;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getStatus() {
        return status;
    }

    public void setPrioridade(short prioridade) {
        this.prioridade = prioridade;
    }

    public short getPrioridade() {
        return prioridade;
    }

    // Método toString para exibir as informações da tarefa
    public String toString() {
        return "\nID.........: " + this.id +
               "\nID Categoria: " + this.idCategoria +
               "\nNome.......: " + this.nome +
               "\nCriação....: " + this.criacao +
               "\nConclusão..: " + (this.conclusao != null ? this.conclusao : "Ainda não concluída") +
               "\nStatus.....: " + this.status +
               "\nPrioridade.: " + this.prioridade;
    }

    // Serialização da classe Tarefa em um array de bytes
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeInt(this.idCategoria);
        dos.writeUTF(this.nome);
        dos.writeInt((int) this.criacao.toEpochDay());
        dos.writeInt(this.conclusao != null ? (int) this.conclusao.toEpochDay() : -1); // -1 indica que não foi concluída
        dos.writeShort(this.status);
        dos.writeShort(this.prioridade);
        return baos.toByteArray();
    }

    // Desserialização de um array de bytes para um objeto Tarefa
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);

        this.id = dis.readInt();
        this.idCategoria = dis.readInt();
        this.nome = dis.readUTF();
        this.criacao = LocalDate.ofEpochDay(dis.readInt());
        int conclusaoEpochDay = dis.readInt();
        this.conclusao = conclusaoEpochDay != -1 ? LocalDate.ofEpochDay(conclusaoEpochDay) : null; // null se não foi concluída
        this.status = dis.readShort();
        this.prioridade = dis.readShort();
    }
}
