package br.com.fiap.soulup.test;

import br.com.fiap.soulup.dao.ConnectionFactory;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionFactoryTest {

    @Test
    void testarConexao() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection conexao = connectionFactory.getConnection();

            assertNotNull(conexao, "A conexao nao deve ser nula");
            assertFalse(conexao.isClosed(), "A conexao deve estar aberta");

            conexao.close();
            assertTrue(conexao.isClosed(), "A conexao deve estar fechada apos o close()");
        } catch (SQLException e) {
            fail("Conexao nao foi criada com sucesso: " + e.getMessage());
        }
    }
}
