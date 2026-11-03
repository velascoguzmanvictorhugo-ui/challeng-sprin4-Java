package br.com.fiap.soulup.dao;

import br.com.fiap.soulup.dominio.Grupo;
import br.com.fiap.soulup.dominio.Validador;
import br.com.fiap.soulup.dominio.RepositorioGrupos;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;

public class GrupoDAO implements RepositorioGrupos {
    @Override
    public void adicionar(Grupo grupo) {
        if (grupo.getNome() == null || grupo.getNome().trim().length() < 3 || grupo.getNome().length() > 100) throw new IllegalArgumentException("Nome do grupo invalido.");
        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);
            try {
                try (Statement lock = connection.createStatement()) { lock.execute("LOCK TABLE tb_grupo IN EXCLUSIVE MODE"); }
                int id;
                try (Statement query = connection.createStatement(); ResultSet result = query.executeQuery("SELECT NVL(MAX(id_grupo),0)+1 FROM tb_grupo")) { result.next(); id=result.getInt(1); }
                LocalDate date = grupo.getDataCriacao() == null ? LocalDate.now() : grupo.getDataCriacao();
                try (PreparedStatement insert = connection.prepareStatement("INSERT INTO tb_grupo (id_grupo,nome,data_criacao) VALUES (?,?,?)")) {
                    insert.setInt(1,id); insert.setString(2,grupo.getNome().trim()); insert.setDate(3,Date.valueOf(date)); insert.executeUpdate();
                }
                var ids = new HashSet<Integer>();
                if (grupo.getMembros() != null) for (Validador member : grupo.getMembros()) {
                    if (member == null || member.getId() <= 0 || !ids.add(member.getId())) throw new IllegalArgumentException("Membro invalido ou duplicado.");
                    try (PreparedStatement insert = connection.prepareStatement("INSERT INTO tb_soulup_grupo_membro (id_grupo,id_usuario) VALUES (?,?)")) {
                        insert.setInt(1,id); insert.setInt(2,member.getId()); insert.executeUpdate();
                    }
                }
                connection.commit(); grupo.setId(id); grupo.setDataCriacao(date);
            } catch (Exception e) { connection.rollback(); throw e; }
        } catch (SQLException e) { throw new IllegalStateException("Nao foi possivel cadastrar o grupo e seus membros.",e); }
    }

    @Override
    public ArrayList<Grupo> listarTodos() {
        var groups = new LinkedHashMap<Integer,Grupo>();
        String sql = "SELECT g.id_grupo,g.nome,g.data_criacao,u.id_usuario,u.nome,u.email FROM tb_grupo g LEFT JOIN tb_soulup_grupo_membro m ON m.id_grupo=g.id_grupo LEFT JOIN tb_usuario u ON u.id_usuario=m.id_usuario ORDER BY g.id_grupo,u.nome";
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement query = connection.prepareStatement(sql); ResultSet result = query.executeQuery()) {
            while (result.next()) {
                int id=result.getInt(1);
                Grupo group=groups.get(id);
                if (group==null) { Date date=result.getDate(3); group=new Grupo(id,result.getString(2),date==null ? null : date.toLocalDate(),0); groups.put(id,group); }
                int userId=result.getInt(4);
                if (!result.wasNull()) group.adicionarMembro(new Validador(userId,result.getString(5),result.getString(6)));
            }
            return new ArrayList<>(groups.values());
        } catch (SQLException e) { throw new IllegalStateException("Nao foi possivel consultar os grupos.",e); }
    }

    public void atualizar(Grupo grupo) {
        if (grupo.getId()<=0 || grupo.getNome()==null || grupo.getNome().trim().length()<3 || grupo.getNome().length()>100) throw new IllegalArgumentException("Grupo invalido.");
        try (Connection connection=new ConnectionFactory().getConnection(); PreparedStatement query=connection.prepareStatement("UPDATE tb_grupo SET nome=? WHERE id_grupo=?")) {
            query.setString(1,grupo.getNome().trim()); query.setInt(2,grupo.getId());
            if(query.executeUpdate()==0) throw new IllegalArgumentException("Grupo nao encontrado.");
        } catch(SQLException e) { throw new IllegalStateException("Nao foi possivel atualizar o grupo.",e); }
    }

    public void excluir(int id) {
        if(id<=0) throw new IllegalArgumentException("Identificador invalido.");
        try(Connection connection=new ConnectionFactory().getConnection(); PreparedStatement query=connection.prepareStatement("DELETE FROM tb_grupo WHERE id_grupo=?")) {
            query.setInt(1,id); if(query.executeUpdate()==0) throw new IllegalArgumentException("Grupo nao encontrado.");
        } catch(SQLException e) { throw new IllegalStateException("Nao foi possivel excluir o grupo. Verifique os registros vinculados.",e); }
    }
    public ArrayList<Grupo> obterTodosGrupos() { return listarTodos(); }
    public void fecharConexao() { /* Cada operacao fecha sua propria conexao. */ }
}
