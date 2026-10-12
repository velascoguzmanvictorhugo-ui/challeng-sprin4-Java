package br.com.fiap.soulup.test;

import br.com.fiap.soulup.dao.UsuarioDAO;
import br.com.fiap.soulup.dominio.Validador;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    @Test
    void testarOperacoesUsuarioDAO() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Validador usuarioTeste = new Validador(999, "Usuario Teste JUnit", "teste.junit@fiap.com.br");

            assertDoesNotThrow(() -> usuarioDAO.inserir(usuarioTeste), "Erro ao inserir usuario na tb_usuario");


            ArrayList<Validador> listaUsuarios = usuarioDAO.listarTodos();
            assertNotNull(listaUsuarios, "A lista recuperada nao deve ser nula");
            assertFalse(listaUsuarios.isEmpty(), "A lista deve conter registros");

            boolean usuarioEncontrado = listaUsuarios.stream().anyMatch(u -> u.lerId() == 999);
            assertTrue(usuarioEncontrado, "O usuario inserido deve estar presente na listagem do banco");


            Validador usuarioAtualizado = new Validador(999, "Usuario Atualizado JUnit", "atualizado@fiap.com.br");
            assertDoesNotThrow(() -> usuarioDAO.atualizar(usuarioAtualizado), "Erro ao atualizar usuario");

            assertDoesNotThrow(() -> usuarioDAO.excluir(999), "Erro ao excluir usuario de teste");

        } catch (Exception e) {
            fail("Falha no teste unitario de UsuarioDAO: " + e.getMessage());
        }
    }
}
