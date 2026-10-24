package br.com.fiap.soulup.dominio;

import java.util.ArrayList;

public interface RepositorioUsuarios {
    void adicionar(Validador usuario);
    ArrayList<Validador> listarTodos();
}
