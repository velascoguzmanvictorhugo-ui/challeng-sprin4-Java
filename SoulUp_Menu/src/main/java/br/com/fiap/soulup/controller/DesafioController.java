package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.dao.DesafioDAO;
import br.com.fiap.soulup.dominio.DesafioPratico;
import br.com.fiap.soulup.service.DesafioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/desafios")
public class DesafioController {

    private final DesafioService desafioService;

    public DesafioController() {
        this.desafioService = new DesafioService(new DesafioDAO());
    }

    @GetMapping
    public ResponseEntity<ArrayList<DesafioPratico>> listar() {
        return ResponseEntity.ok(desafioService.listar());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody DesafioPratico desafio) {
        try {
            desafioService.adicionar(desafio);
            return ResponseEntity.status(HttpStatus.CREATED).body(desafio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<DesafioPratico> atualizar(@RequestBody DesafioPratico desafio) {
        desafioService.atualizar(desafio);
        return ResponseEntity.ok(desafio);
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(@RequestParam int id) {
        desafioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}