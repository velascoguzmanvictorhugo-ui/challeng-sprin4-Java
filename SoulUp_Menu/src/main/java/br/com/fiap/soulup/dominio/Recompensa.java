package br.com.fiap.soulup.dominio;

public abstract class Recompensa {
    private int custoPontos;

    public Recompensa(int custoPontos) {
        this.custoPontos = custoPontos;
    }

    public int lerCusto() {
        return custoPontos; }

    public abstract String aplicarRecompensa();
}
