package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.dao.UsuarioDAO;
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

    @PostMapping
    public ResponseEntity salvar(@RequestBody Validador usuario) {
        try {
            usuarioService.adicionar(usuario);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usuario);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

        @PutMapping
        public ResponseEntity<Validador> atualizar(@RequestBody Validador usuario) {
            usuarioService.atualizar(usuario);
            return ResponseEntity.ok(usuario);
        }


        @DeleteMapping
        public ResponseEntity<Void> excluir(int id) {
            usuarioService.excluir(id);
            return ResponseEntity.noContent().build();
        }
    }
