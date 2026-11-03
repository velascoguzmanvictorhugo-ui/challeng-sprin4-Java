package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.dao.UsuarioDAO;
import br.com.fiap.soulup.dao.AccountDAO;
import br.com.fiap.soulup.service.PasswordHash;
import br.com.fiap.soulup.dominio.Validador;
import br.com.fiap.soulup.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService(new UsuarioDAO());
    }

    @GetMapping
    public ResponseEntity<ArrayList<Validador>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    public record RegisterRequest(String nome, String email, String senha) {}

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody RegisterRequest usuario) {
        try {
            if (usuario.nome() == null || usuario.nome().trim().length() < 2 || usuario.nome().length() > 100 || usuario.email() == null || usuario.email().length() > 254 || !usuario.email().trim().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) return ResponseEntity.badRequest().build();
            var account = new AccountDAO().register(usuario.nome().trim(), usuario.email().trim().toLowerCase(java.util.Locale.ROOT), PasswordHash.create(usuario.senha()));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(e.getMessage().contains("cadastrado") ? HttpStatus.CONFLICT : HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cadastro indisponivel no momento.");
        }
    }

        @PutMapping
        public ResponseEntity<Validador> atualizar(@RequestBody Validador usuario) {
            usuarioService.atualizar(usuario);
            return ResponseEntity.ok(usuario);
        }


        @DeleteMapping
        public ResponseEntity<Void> excluir(@RequestParam int id) {
            usuarioService.excluir(id);
            return ResponseEntity.noContent().build();
        }
    }
