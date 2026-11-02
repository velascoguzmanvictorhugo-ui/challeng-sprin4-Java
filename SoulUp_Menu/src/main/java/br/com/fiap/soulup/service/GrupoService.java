package br.com.fiap.soulup.service;

import br.com.fiap.soulup.dominio.Grupo;
import br.com.fiap.soulup.dominio.RepositorioGrupos;

import java.util.ArrayList;

public class GrupoService {

    private RepositorioGrupos repositorioGrupos;

    public GrupoService(RepositorioGrupos repositorioGrupos) {
        this.repositorioGrupos = repositorioGrupos;
    }

    public ArrayList<Grupo> listar() {
        return repositorioGrupos.listarTodos();
    }

    public void salvar(Grupo grupo) {
        if (grupo.getNome() == null || grupo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do grupo e obrigatorio.");
        }
        repositorioGrupos.adicionar(grupo);
    }
    public void atualizar(Grupo grupo) {
        if (grupo.getNome() == null || grupo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do grupo e obrigatorio para atualizar.");
        }
        repositorioGrupos.atualizar(grupo);
    }

    // Método para eliminar grupo por ID
    public void excluir(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido para exclusao.");
        }
        repositorioGrupos.excluir(id);
    }

}


