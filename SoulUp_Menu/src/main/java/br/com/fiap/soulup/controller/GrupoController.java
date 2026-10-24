package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.dao.GrupoDAO;
import br.com.fiap.soulup.dominio.Grupo;
import br.com.fiap.soulup.service.GrupoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private GrupoService grupoService;

    public GrupoController() {
        this.grupoService = new GrupoService(new GrupoDAO());
    }

    @GetMapping
    public ResponseEntity<ArrayList<Grupo>> listar() {
        ArrayList<Grupo> lista = grupoService.listar();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Grupo> cadastrar(@RequestBody Grupo grupo) {
        grupoService.salvar(grupo);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
    }

}