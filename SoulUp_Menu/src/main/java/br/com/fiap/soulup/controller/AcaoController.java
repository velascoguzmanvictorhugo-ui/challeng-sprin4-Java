package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.service.AcaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acoes")
public class AcaoController {

    private AcaoService acaoService = new AcaoService();

    @PostMapping("/concluir")
    public ResponseEntity<String> concluirDesafio(
            @RequestParam int idUsuario,
            @RequestParam int idDesafio,
            @RequestParam int pontos) {

        try {
            acaoService.adicionar(idUsuario, idDesafio, pontos);
            return ResponseEntity.status(201).body("Acao registrada e pontos adicionados com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}