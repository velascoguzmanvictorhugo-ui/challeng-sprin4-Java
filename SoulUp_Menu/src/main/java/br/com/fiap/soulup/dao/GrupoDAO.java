package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GrupoDAO {

    private Connection conexao;

    public GrupoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionar(Grupo grupo) {
        String sql = "INSERT INTO tb_grupo (nome, pontos) VALUES (?, ?)";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, grupo.lerNome());
            ps.setInt(2, grupo.lerPontos());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Grupo> obterTodosGrupos() {
        ArrayList<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT nome, pontos FROM tb_grupo";

        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                grupos.add(new Grupo(nome));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return grupos;
    }

    public void desconectar() {
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}