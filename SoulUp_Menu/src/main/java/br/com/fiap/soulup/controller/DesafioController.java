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

    private DesafioService desafioService;

    public DesafioController() {
        this.desafioService = new DesafioService(new DesafioDAO());
    }

    // 1. READ (GET)
    @GetMapping
    public ResponseEntity<ArrayList<DesafioPratico>> listar() {
        ArrayList<DesafioPratico> lista = desafioService.listar();
        return ResponseEntity.ok(lista);
    }

    // 2. CREATE (POST)
    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody DesafioPratico desafio) {
        try {
            desafioService.adicionar(desafio);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(desafio);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // 3. UPDATE (PUT)
    @PutMapping
    public ResponseEntity<DesafioPratico> atualizar(@RequestBody DesafioPratico desafio) {
        desafioService.atualizar(desafio);
        return ResponseEntity.ok(desafio);
    }

    // 4. DELETE (DELETE) - ?id=X
    @DeleteMapping
    public ResponseEntity<Void> excluir(int id) {
        desafioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}