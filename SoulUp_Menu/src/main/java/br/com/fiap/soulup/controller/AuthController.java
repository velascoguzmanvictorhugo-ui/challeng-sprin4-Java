package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.dao.AccountDAO;
import br.com.fiap.soulup.dominio.Validador;
import br.com.fiap.soulup.service.PasswordHash;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AccountDAO accounts;
    private static final String DUMMY_HASH = PasswordHash.create("invalid-account-placeholder");

    public AuthController() { this(new AccountDAO()); }
    AuthController(AccountDAO accounts) { this.accounts = accounts; }

    public record LoginRequest(String email, String senha) {}
    public record FuncionarioRequest(String senha) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest body, HttpServletRequest request) {
        HttpSession previous = request.getSession(false);
        if (previous != null) previous.invalidate();
        if (body.email() == null || body.senha() == null || body.email().length() > 254 || body.senha().length() > 72) return ResponseEntity.badRequest().build();
        AccountDAO.Credential credential = accounts.findByEmail(body.email().trim().toLowerCase(java.util.Locale.ROOT));
        boolean valid = PasswordHash.matches(body.senha(), credential == null ? DUMMY_HASH : credential.hash());
        if (credential == null || !valid) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        HttpSession session = request.getSession(true);
        session.setAttribute("soulupUserId", credential.account().getId());
        session.setMaxInactiveInterval(1800);
        return ResponseEntity.ok(credential.account());
    }

    // Nuevo endpoint para validar la contraseña fija de la sección "Acesso Funcionário"
    @PostMapping("/funcionario")
    public ResponseEntity<?> loginFuncionario(@RequestBody FuncionarioRequest body, HttpServletRequest request) {
        // Define aquí la contraseña fija que usarán los funcionarios
        String senhaFixa = "123456789";

        if (body.senha() == null || !body.senha().equals(senhaFixa)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpSession previous = request.getSession(false);
        if (previous != null) previous.invalidate();

        HttpSession session = request.getSession(true);
        session.setAttribute("soulupFuncionarioAuth", true);
        session.setMaxInactiveInterval(1800);

        return ResponseEntity.ok().body("Acesso autorizado com sucesso.");
    }

    @GetMapping("/me")
    public ResponseEntity<Validador> current(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || !(session.getAttribute("soulupUserId") instanceof Integer id)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        Validador account = accounts.findById(id);
        if (account == null) { session.invalidate(); return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
        return ResponseEntity.ok(account);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return ResponseEntity.noContent().build();
    }
}