package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.RepositorioUsuarios;
import br.com.fiap.soulup.dominio.Validador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO implements RepositorioUsuarios {


public UsuarioDAO() {
}

    @Override
    public void adicionar(Validador usuario) {
        String sql = "INSERT INTO tb_usuario (id_usuario, nome, email) VALUES (?, ?, ?)";
        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, usuario.lerId());
            comando.setString(2, usuario.lerNome());
            comando.setString(3, usuario.lerEmail());
            comando.execute();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Validador> listarTodos() {
        ArrayList<Validador> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, nome, email FROM tb_usuario";
        try (Connection conexao = new ConnectionFactory().getConnection();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                lista.add(new Validador(id, nome, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}


