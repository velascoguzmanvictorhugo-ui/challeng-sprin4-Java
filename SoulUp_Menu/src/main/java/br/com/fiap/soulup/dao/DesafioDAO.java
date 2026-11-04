package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.DesafioPratico;
import br.com.fiap.soulup.dominio.RepositorioDesafios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DesafioDAO implements RepositorioDesafios {

    public DesafioDAO() {
    }

    @Override
    public void adicionar(DesafioPratico desafio) {
        String sql = "INSERT INTO tb_desafio (id_desafio, titulo, descricao, pontos, categoria) " +
                "VALUES ((SELECT NVL(MAX(id_desafio), 0) + 1 FROM tb_desafio), ?, ?, ?, ?)";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, desafio.lerTitulo());
            stmt.setString(2, desafio.lerDescricao());
            stmt.setInt(3, desafio.lerPontos());
            stmt.setString(4, desafio.lerCategoria());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao adicionar desafio: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<DesafioPratico> listarTodos() {
        ArrayList<DesafioPratico> lista = new ArrayList<>();
        String sql = "SELECT id_desafio, titulo, descricao, pontos, categoria FROM tb_desafio ORDER BY id_desafio ASC";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DesafioPratico desafio = new DesafioPratico(
                        rs.getInt("id_desafio"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("pontos"),
                        rs.getString("categoria")
                );
                lista.add(desafio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar desafios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void atualizar(DesafioPratico desafio) {
        String sql = "UPDATE tb_desafio SET titulo = ?, descricao = ?, pontos = ?, categoria = ? WHERE id_desafio = ?";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, desafio.lerTitulo());
            stmt.setString(2, desafio.lerDescricao());
            stmt.setInt(3, desafio.lerPontos());
            stmt.setString(4, desafio.lerCategoria());
            stmt.setInt(5, desafio.lerId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar desafio: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM tb_desafio WHERE id_desafio = ?";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir desafio: " + e.getMessage());
        }
    }
}