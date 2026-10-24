package br.com.fiap.soulup.dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Grupo {

    private int id;
    private String nome;
    private LocalDate dataCriacao;
    private int pontosAcumulados;
    private ArrayList<Validador> membros;

    public Grupo() {
        this.membros = new ArrayList<>();
        this.dataCriacao = LocalDate.now();
        this.pontosAcumulados = 0;
    }

    public Grupo(String nome) {
        this.nome = nome;
        this.dataCriacao = LocalDate.now();
        this.pontosAcumulados = 0;
        this.membros = new ArrayList<>();
    }

    public Grupo(int id, String nome, LocalDate dataCriacao, int pontosAcumulados) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = (dataCriacao != null) ? dataCriacao : LocalDate.now();
        this.pontosAcumulados = pontosAcumulados;
        this.membros = new ArrayList<>();
    }


    public void adicionarPontos(int pontos) {
        if (pontos > 0) {
            this.pontosAcumulados += pontos;
        }
    }

    public int lerPontos() {
        return this.pontosAcumulados;
    }

    public String lerNome() {
        return this.nome;
    }

    public String classificarNivel() {
        if (this.pontosAcumulados >= 6000) {
            return "Lider Verde";
        } else if (this.pontosAcumulados >= 3000) {
            return "Defensor";
        } else if (this.pontosAcumulados >= 1000) {
            return "Guardiao";
        } else {
            return "Broto Verde";
        }
    }

    public String exibirResumo() {
        return "Grupo: " + this.nome + " | Saldo de Pontos: " + this.pontosAcumulados + " | Nivel: " + classificarNivel();
    }

    public void adicionarMembro(Validador usuario) {
        if (usuario != null && !this.membros.contains(usuario)) {
            this.membros.add(usuario);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getPontosAcumulados() {
        return pontosAcumulados;
    }

    public void setPontosAcumulados(int pontosAcumulados) {
        this.pontosAcumulados = pontosAcumulados;
    }

    public ArrayList<Validador> getMembros() {
        return membros;
    }

    public void setMembros(ArrayList<Validador> membros) {
        this.membros = membros;
    }

    public String getNivel() {
        return classificarNivel();
    }
}