package br.com.fiap.soulup.dominio;

public class DesafioPratico extends Desafio {
    private int id;
    private String descricao;
    private String categoria;

    public DesafioPratico(int id, String titulo, String descricao, int pontuacao, String categoria) {
        super(titulo, pontuacao);
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public int lerId() {
        return id;
    }

    public String lerDescricao() {
        return descricao;
    }

    public String lerCategoria() {
        return categoria;
    }

    // Getters para el Front-End (JSON)
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return lerTitulo();
    }

    public int getPontos() {
        return lerPontuacao();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String tipoDesafio() {
        return "Pratico";
    }
}