package br.com.fiap.soulup.dominio;

import java.util.ArrayList;

public interface RepositorioDesafios {
    ArrayList<DesafioPratico> listarTodos();
    void adicionar(DesafioPratico desafio);
    void atualizar(DesafioPratico desafio);
    void excluir(int id);
}