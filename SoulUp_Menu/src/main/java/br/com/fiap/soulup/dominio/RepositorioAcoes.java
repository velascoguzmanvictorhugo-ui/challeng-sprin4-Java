package br.com.fiap.soulup.dominio;

public interface RepositorioAcoes {
    void registrarAcao(int idUsuario, int idDesafio, int pontosGanhos);
}