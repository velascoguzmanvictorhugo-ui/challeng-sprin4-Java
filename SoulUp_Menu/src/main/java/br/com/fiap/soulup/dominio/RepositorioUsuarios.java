package br.com.fiap.soulup.dominio;

import java.util.ArrayList;

public interface RepositorioUsuarios {
    void adicionar(Validador usuario);
    ArrayList<Validador> listarTodos();
    void atualizar(Validador usuario);
    void excluir(int id);
}
