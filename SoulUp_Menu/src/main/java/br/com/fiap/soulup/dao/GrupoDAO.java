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

    private Connection conexao;

    public GrupoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public void adicionar(Grupo grupo) {
        String sql = "INSERT INTO tb_grupo (id_grupo, nome, data_criacao) " +
                "SELECT NVL(MAX(id_grupo), 0) + 1, ?, ? FROM tb_grupo";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, grupo.getNome());
            LocalDate data = (grupo.getDataCriacao() != null) ? grupo.getDataCriacao() : LocalDate.now();
            stmt.setDate(2, Date.valueOf(data));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar grupo: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Grupo> listarTodos() {
        ArrayList<Grupo> lista = new ArrayList<>();
        String sql = "SELECT id_grupo, nome, data_criacao FROM tb_grupo ORDER BY id_grupo ASC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_grupo");
                String nome = rs.getString("nome");
                Date dataSql = rs.getDate("data_criacao");
                LocalDate data = (dataSql != null) ? dataSql.toLocalDate() : LocalDate.now();

                Grupo grupo = new Grupo(id, nome, data, 0);
                lista.add(grupo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar grupos: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Grupo> obterTodosGrupos() {
        return listarTodos();
    }

    public void fecharConexao() {
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
