package backend.backend.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.dto.LoginDTORequest;
import backend.backend.dto.UsuarioDTOResponse;
import backend.backend.model.Usuario;
import backend.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTOResponse> login(@RequestBody LoginDTORequest dados) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(dados.email());

        if (usuario.isEmpty() || !usuario.get().getSenha().equals(dados.senha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(UsuarioDTOResponse.converter(usuario.get()));
    }
}
