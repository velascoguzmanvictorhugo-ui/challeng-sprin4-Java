package br.com.fiap.soulup.dominio;

public class DesafioPratico extends Desafio {
    public DesafioPratico(String titulo, int pontuacao) {
        super(titulo, pontuacao);
    }

    @Override
    public String tipoDesafio() {
        return "Desafio Pratico de Impacto Direto (Presencial)";
    }
}
