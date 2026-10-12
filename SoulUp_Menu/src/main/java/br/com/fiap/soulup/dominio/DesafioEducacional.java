package br.com.fiap.soulup.dominio;

public class DesafioEducacional extends Desafio {
    public DesafioEducacional(String titulo, int pontuacao) {
        super(titulo, pontuacao);
    }

    @Override
    public String tipoDesafio() {
        return "Desafio Educacional / Conscientizacao (Online)";
    }
}
