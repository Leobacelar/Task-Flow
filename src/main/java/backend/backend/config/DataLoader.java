package backend.backend.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import backend.backend.model.Usuario;
import backend.backend.repository.UsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    DataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        criarUsuario("Ana Souza", "ana@taskflow.com");
        criarUsuario("Bruno Lima", "bruno@taskflow.com");
        criarUsuario("Carla Mendes", "carla@taskflow.com");
        criarUsuario("Vinnicius Soares Nogueira", "vinnicius@taskflow.com");
        criarUsuario("Luciano Alves de Andrade Neto", "luciano@taskflow.com");
        criarUsuario("Leonardo Bacelar de Paula Barreto", "leonardo@taskflow.com");
        criarUsuario("Saimon Gomes de Oliveira", "saimon@taskflow.com");

        // criptografa senhas antigas que ainda estejam em texto simples no banco
        for (Usuario usuario : usuarioRepository.findAll()) {
            if (!usuario.getSenha().startsWith("$2")) {
                usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
                usuarioRepository.save(usuario);
            }
        }
    }

    private void criarUsuario(String nome, String email) {
        if (usuarioRepository.findByEmail(email).isEmpty()) {
            usuarioRepository.save(new Usuario(nome, email, passwordEncoder.encode("123456")));
        }
    }
}
