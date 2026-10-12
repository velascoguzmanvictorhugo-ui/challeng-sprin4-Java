package br.com.fiap.soulup.test;

import br.com.fiap.soulup.dao.GrupoDAO;
import br.com.fiap.soulup.dominio.Grupo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GrupoDAOTest {

    @Test
    void testarInserirEConsultarGrupo() {
        try {
            GrupoDAO grupoDAO = new GrupoDAO();
            Grupo novoGrupo = new Grupo("Grupo Sustentavel JUnit");
            novoGrupo.adicionarPontos(300);

            assertDoesNotThrow(() -> grupoDAO.adicionar(novoGrupo), "Erro ao inserir grupo no banco");

            ArrayList<Grupo> listaGrupos = grupoDAO.obterTodosGrupos();
            assertNotNull(listaGrupos, "A lista de grupos nao deve ser nula");
            assertFalse(listaGrupos.isEmpty(), "A tabela deve conter ao menos um registro");

            assertDoesNotThrow(() -> grupoDAO.desconectar(), "Erro ao fechar conexao do GrupoDAO");

        } catch (Exception e) {
            fail("Falha no teste de persistencia de GrupoDAO: " + e.getMessage());
        }
    }
}