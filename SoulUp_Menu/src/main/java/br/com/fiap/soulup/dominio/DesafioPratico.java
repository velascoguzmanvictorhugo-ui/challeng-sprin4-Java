package br.com.fiap.soulup.dominio;

public class DesafioPratico extends Desafio {

    public DesafioPratico() {
        super();
    }

    // Constructor completo con los 5 atributos
    public DesafioPratico(int id, String titulo, String descricao, int pontos, String categoria) {
        super(id, titulo, descricao, pontos, categoria);
    }

    // Sobrecarga de conveniencia que autocompleta los 5 atributos si solo se envían título y puntos
    public DesafioPratico(String titulo, int pontos) {
        super(0, titulo, "Sem descricao", pontos, "Geral");
    }

    @Override
    public String tipoDesafio() {
        return "Pratico";
    }
}