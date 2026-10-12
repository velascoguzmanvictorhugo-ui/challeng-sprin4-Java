package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.Usuario;
import br.com.fiap.soulup.dominio.Validador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {


    public void inserir(Usuario usuario) {
        Connection conexao = new ConnectionFactory().getConnection();
        String sql = "INSERT INTO tb_usuario (id_usuario, nome, email) VALUES (?, ?, ?)";

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, usuario.lerId());
            comando.setString(2, usuario.lerNome());
            comando.setString(3, usuario.lerEmail());

            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Validador> listarTodos() {
        ArrayList<Validador> lista = new ArrayList<>();
        Connection conexao = new ConnectionFactory().getConnection();
        String sql = "SELECT id_usuario, nome, email FROM tb_usuario";

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                lista.add(new Validador(id, nome, email));
            }

            rs.close();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    // 3. UPDATE
    public void atualizar(Usuario usuario) {
        Connection conexao = new ConnectionFactory().getConnection();
        String sql = "UPDATE tb_usuario SET nome = ?, email = ? WHERE id_usuario = ?";

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, usuario.lerNome());
            comando.setString(2, usuario.lerEmail());
            comando.setInt(3, usuario.lerId());

            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 4. DELETE
    public void excluir(int id) {
        Connection conexao = new ConnectionFactory().getConnection();
        String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";

        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);

            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}