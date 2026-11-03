package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.Validador;
import java.sql.*;

public class AccountDAO {
    public record Credential(Validador account, String hash) {}

    public Validador register(String name, String email, String hash) {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);
            try {
                try (Statement lock = connection.createStatement()) { lock.execute("LOCK TABLE tb_usuario IN EXCLUSIVE MODE"); }
                try (PreparedStatement check = connection.prepareStatement("SELECT id_usuario FROM tb_usuario WHERE LOWER(email) = ?")) {
                    check.setString(1, email);
                    try (ResultSet result = check.executeQuery()) { if (result.next()) throw new IllegalArgumentException("E-mail ja cadastrado."); }
                }
                int id;
                try (Statement query = connection.createStatement(); ResultSet result = query.executeQuery("SELECT NVL(MAX(id_usuario), 0) + 1 FROM tb_usuario")) { result.next(); id = result.getInt(1); }
                try (PreparedStatement insert = connection.prepareStatement("INSERT INTO tb_usuario (id_usuario,nome,email) VALUES (?,?,?)")) {
                    insert.setInt(1,id); insert.setString(2,name); insert.setString(3,email); insert.executeUpdate();
                }
                try (PreparedStatement insert = connection.prepareStatement("INSERT INTO tb_soulup_credencial (id_usuario,senha_hash) VALUES (?,?)")) {
                    insert.setInt(1,id); insert.setString(2,hash); insert.executeUpdate();
                }
                connection.commit();
                return new Validador(id, name, email);
            } catch (Exception e) { connection.rollback(); throw e; }
        } catch (SQLException e) { throw new IllegalStateException("Cadastro indisponivel. Verifique a migracao do banco de dados.", e); }
    }

    public Credential findByEmail(String email) {
        String sql = "SELECT u.id_usuario,u.nome,u.email,c.senha_hash FROM tb_usuario u JOIN tb_soulup_credencial c ON c.id_usuario=u.id_usuario WHERE LOWER(u.email)=?";
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement query = connection.prepareStatement(sql)) {
            query.setString(1,email);
            try (ResultSet result = query.executeQuery()) {
                if (!result.next()) return null;
                return new Credential(new Validador(result.getInt(1),result.getString(2),result.getString(3)),result.getString(4));
            }
        } catch (SQLException e) { throw new IllegalStateException("Autenticacao indisponivel. Verifique a migracao do banco de dados.", e); }
    }

    public Validador findById(int id) {
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement query = connection.prepareStatement("SELECT id_usuario,nome,email,NVL(pontos_acumulados,0) FROM tb_usuario WHERE id_usuario=?")) {
            query.setInt(1,id);
            try (ResultSet result = query.executeQuery()) {
                if (!result.next()) return null;
                Validador account = new Validador(result.getInt(1),result.getString(2),result.getString(3));
                account.adicionarPontos(result.getInt(4));
                return account;
            }
        } catch (SQLException e) { throw new IllegalStateException("Consulta da conta indisponivel.", e); }
    }
}
