package br.com.fiap.soulup.dominio;

public abstract class Desafio {
    private String titulo;
    private int pontuacao;

    public Desafio(String titulo, int pontuacao) {
        this.titulo = titulo;
        if (pontuacao <= 0) {
            throw new IllegalArgumentException("A pontuacao nao pode ser zero ou negativa.");
        }
        this.pontuacao = pontuacao;
    }

    public String lerTitulo() {
        return titulo; }
    public int lerPontuacao() {
        return pontuacao; }

    public abstract String tipoDesafio();
}
