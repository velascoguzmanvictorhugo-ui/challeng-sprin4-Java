package br.com.fiap.soulup.service;

import br.com.fiap.soulup.dao.AcaoPublicadaDAO;
import br.com.fiap.soulup.dominio.RepositorioAcoes;

public class AcaoService {

    private final RepositorioAcoes repositorioAcoes;

    public AcaoService() {
        this.repositorioAcoes = new AcaoPublicadaDAO();
    }

    public void adicionar(int idUsuario, int idDesafio, int pontos) {
        if (idUsuario <= 0 || idDesafio <= 0) {
            throw new IllegalArgumentException("Os IDs de usuario ou desafio sao invalidos.");
        }
        if (pontos <= 0) {
            throw new IllegalArgumentException("Os pontos ganhos devem ser maiores que zero.");
        }

        repositorioAcoes.registrarAcao(idUsuario, idDesafio, pontos);
    }
}