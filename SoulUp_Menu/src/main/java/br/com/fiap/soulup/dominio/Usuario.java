package br.com.fiap.soulup.dominio;

public abstract class Usuario {
    private int id;
    private String nome;
    private String email;

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int lerId() {
        return id;
    }

    public String lerNome() {
        return nome;
    }

    public String lerEmail() {
        return email;
    }

    public abstract String exibirPerfil();
}