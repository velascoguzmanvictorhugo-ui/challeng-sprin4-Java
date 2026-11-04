package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.Grupo;
import br.com.fiap.soulup.dominio.RepositorioGrupos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GrupoDAO implements RepositorioGrupos {

    public GrupoDAO() {
    }

    @Override
    public void adicionar(Grupo grupo) {
        String sql = "INSERT INTO tb_grupo (id_grupo, nome, pontos, data_criacao) " +
                "VALUES ((SELECT NVL(MAX(id_grupo), 0) + 1 FROM tb_grupo), ?, ?, ?)";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, grupo.getNome());
            stmt.setInt(2, 0); // Puntos iniciales
            LocalDate data = (grupo.getDataCriacao() != null) ? grupo.getDataCriacao() : LocalDate.now();
            stmt.setDate(3, Date.valueOf(data));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao adicionar grupo: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Grupo> listarTodos() {
        ArrayList<Grupo> lista = new ArrayList<>();
        String sql = "SELECT id_grupo, nome, pontos, data_criacao FROM tb_grupo ORDER BY id_grupo ASC";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_grupo");
                String nome = rs.getString("nome");
                int pontos = rs.getInt("pontos");
                Date dataSql = rs.getDate("data_criacao");
                LocalDate data = (dataSql != null) ? dataSql.toLocalDate() : LocalDate.now();

                Grupo grupo = new Grupo(id, nome, data, pontos);
                lista.add(grupo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar grupos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void atualizar(Grupo grupo) {
        String sql = "UPDATE tb_grupo SET nome = ?, pontos = ? WHERE id_grupo = ?";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, grupo.getNome());
            // CORRIGIDO AQUÍ ABAJO 👇
            stmt.setInt(2, grupo.getPontosAcumulados());
            stmt.setInt(3, grupo.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar grupo: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM tb_grupo WHERE id_grupo = ?";

        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir grupo: " + e.getMessage());
        }
    }
}