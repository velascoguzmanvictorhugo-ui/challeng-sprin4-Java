package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.service.RecompensaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recompensas")
public class RecompensaController {

    private RecompensaService recompensaService = new RecompensaService();

    @PostMapping("/resgatar")
    public ResponseEntity<String> resgatar(
            @RequestParam int idUsuario,
            @RequestParam int custo) {

        try {
            recompensaService.resgatar(idUsuario, custo);
            return ResponseEntity.ok("Recompensa resgatada! Pontos descontados.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}