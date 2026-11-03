package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.RepositorioRecompensas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecompensaDAO implements RepositorioRecompensas {

    public RecompensaDAO() {
    }

    public void gastarPontos(int idUsuario, int custo) {

        String sql = "UPDATE tb_usuario SET pontos_acumulados = pontos_acumulados - ? WHERE id_usuario = ?";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, custo);
            comando.setInt(2, idUsuario);
            comando.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}