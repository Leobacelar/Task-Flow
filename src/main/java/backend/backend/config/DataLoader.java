package backend.backend.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import backend.backend.model.Usuario;
import backend.backend.repository.UsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;

    DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
    }

    private void criarUsuario(String nome, String email) {
        if (usuarioRepository.findByEmail(email).isEmpty()) {
            usuarioRepository.save(new Usuario(nome, email, "123456"));
        }
    }
}
