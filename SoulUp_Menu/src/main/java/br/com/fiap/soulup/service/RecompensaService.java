package br.com.fiap.soulup.service;

import br.com.fiap.soulup.dao.RecompensaDAO;
import br.com.fiap.soulup.dominio.RepositorioRecompensas;

public class RecompensaService {

    private final RepositorioRecompensas repositorioRecompensas;

    public RecompensaService() {
        this.repositorioRecompensas = new RecompensaDAO();
    }

    public void resgatar(int idUsuario, int custo) {
        if (idUsuario <= 0) {
            throw new IllegalArgumentException("O ID do usuario e obrigatorio.");
        }
        if (custo <= 0) {
            throw new IllegalArgumentException("O custo da recompensa e invalido.");
        }

        repositorioRecompensas.gastarPontos(idUsuario, custo);
    }
}