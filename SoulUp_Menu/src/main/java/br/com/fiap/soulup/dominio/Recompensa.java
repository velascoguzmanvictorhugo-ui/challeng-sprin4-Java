package br.com.fiap.soulup.dominio;

public abstract class Recompensa {
    private int id;
    private String descricao; // Agregado para coincidir con la base de datos
    private int custoPontos;

    public Recompensa() {
    }

    public Recompensa(int id, String descricao, int custoPontos) {
        this.id = id;
        this.descricao = descricao;
        this.custoPontos = custoPontos;
    }


    public int lerId() {
        return id;
    }

    public String lerDescricao() {
        return descricao;
    }

    public int lerCusto() {
        return custoPontos;
    }

    public abstract String aplicarRecompensa();
}