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
        if (usuarioRepository.count() > 0) {
            return;
        }

        usuarioRepository.save(new Usuario("Ana Souza", "ana@taskflow.com", "123456"));
        usuarioRepository.save(new Usuario("Bruno Lima", "bruno@taskflow.com", "123456"));
        usuarioRepository.save(new Usuario("Carla Mendes", "carla@taskflow.com", "123456"));
    }
}
