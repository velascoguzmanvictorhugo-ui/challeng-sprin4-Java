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

}

