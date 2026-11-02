package br.com.fiap.soulup.service;

import br.com.fiap.soulup.dominio.DesafioPratico;
import br.com.fiap.soulup.dominio.RepositorioDesafios;

import java.util.ArrayList;

public class DesafioService {

    private final RepositorioDesafios repositorioDesafios;

    public DesafioService(RepositorioDesafios repositorioDesafios) {
        this.repositorioDesafios = repositorioDesafios;
    }

    public ArrayList<DesafioPratico> listar() {
        return repositorioDesafios.listarTodos();
    }

    public void adicionar(DesafioPratico desafio) {
        if (desafio.lerTitulo() == null || desafio.lerTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O titulo do desafio e obrigatorio.");
        }
        repositorioDesafios.adicionar(desafio);
    }

    public void atualizar(DesafioPratico desafio) {
        if (desafio.lerId() <= 0) {
            throw new IllegalArgumentException("ID invalido para atualizar desafio.");
        }
        repositorioDesafios.atualizar(desafio);
    }

    public void excluir(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido para exclusao.");
        }
        repositorioDesafios.excluir(id);
    }
}