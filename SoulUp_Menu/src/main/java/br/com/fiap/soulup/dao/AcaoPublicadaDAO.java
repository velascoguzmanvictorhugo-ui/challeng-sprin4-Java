package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.RepositorioAcoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcaoPublicadaDAO implements RepositorioAcoes {

    public AcaoPublicadaDAO() {
    }

    public void registrarAcao(int idUsuario, int idDesafio, int pontosGanhos) {

        String sqlAcao = "INSERT INTO tb_acao_realizada (id_acao, id_grupo, id_desafio, id_avatar) " +
                "VALUES ((SELECT NVL(MAX(id_acao), 0) + 1 FROM tb_acao_realizada), 1, ?, 1)";


        String sqlPontos = "UPDATE tb_usuario SET pontos_acumulados = pontos_acumulados + ? WHERE id_usuario = ?";

        try (Connection conexao = new ConnectionFactory().getConnection()) {

            try (PreparedStatement comandoAcao = conexao.prepareStatement(sqlAcao)) {
                comandoAcao.setInt(1, idDesafio);
                comandoAcao.execute();
            }

            try (PreparedStatement comandoPontos = conexao.prepareStatement(sqlPontos)) {
                comandoPontos.setInt(1, pontosGanhos);
                comandoPontos.setInt(2, idUsuario);
                comandoPontos.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
