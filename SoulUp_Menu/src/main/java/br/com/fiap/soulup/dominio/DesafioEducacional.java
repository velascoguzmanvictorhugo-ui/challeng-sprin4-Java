package br.com.fiap.soulup.dominio;

public class DesafioEducacional extends Desafio {

    public DesafioEducacional() {
        super();
    }


    public DesafioEducacional(String titulo, int pontos) {
        super(0, titulo, "Desafio Educacional / Curso", pontos, "Educacao");
    }

    public DesafioEducacional(int id, String titulo, String descricao, int pontos, String categoria) {
        super(id, titulo, descricao, pontos, categoria);
    }

    @Override
    public String tipoDesafio() {
        return "Educacional";
    }
}