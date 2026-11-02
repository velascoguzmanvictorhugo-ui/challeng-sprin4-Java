package br.com.fiap.soulup.dominio;

public abstract class Desafio {
    private int id;
    private String titulo;
    private String descricao;
    private int pontos;
    private String categoria;

    public Desafio() {
    }

    public Desafio(int id, String titulo, String descricao, int pontos, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontos = pontos;
        this.categoria = categoria;
    }


    public int lerId() {
        return id;
    }

    public String lerTitulo() {
        return titulo;
    }

    public String lerDescricao() {
        return descricao;
    }

    public int lerPontos() {
        return pontos;
    }

    public String lerCategoria() {
        return categoria;
    }

    // Getters y Setters para Spring Boot / Jackson JSON
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public abstract String tipoDesafio();
}