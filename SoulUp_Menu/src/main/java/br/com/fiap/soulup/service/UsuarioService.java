package br.com.fiap.soulup.service;

import br.com.fiap.soulup.dominio.RepositorioUsuarios;
import br.com.fiap.soulup.dominio.Validador;
import java.util.ArrayList;

public class UsuarioService {

    private RepositorioUsuarios repositorioUsuarios;

    public UsuarioService(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public ArrayList<Validador> listar() {
        return repositorioUsuarios.listarTodos();
    }

    public void adicionar(Validador usuario) {
        repositorioUsuarios.adicionar(usuario);
    }
}