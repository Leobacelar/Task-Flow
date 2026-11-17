package backend.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.model.Atividade;
import backend.backend.repository.AtividadeRepository;

@RestController
@RequestMapping("/api/atividades")
@CrossOrigin(origins = "http://localhost:4200")
public class AtividadeController {

    private final AtividadeRepository atividadeRepository;

    AtividadeController(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    @GetMapping
    public List<Atividade> listar() {
        return atividadeRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Atividade> criar(@RequestBody Atividade atividade) {
        // regra: toda atividade nova nasce em "A fazer"
        if (!"a-fazer".equals(atividade.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        atividade.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeRepository.save(atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizar(@PathVariable Long id, @RequestBody Atividade dados) {
        return atividadeRepository.findById(id).map(atividade -> {
            // regra: de "A fazer" nao pode ir direto para "Concluido"
            if ("a-fazer".equals(atividade.getStatus()) && "finalizado".equals(dados.getStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).<Atividade>build();
            }
            atividade.setTitulo(dados.getTitulo());
            atividade.setDescricao(dados.getDescricao());
            atividade.setStatus(dados.getStatus());
            return ResponseEntity.ok(atividadeRepository.save(atividade));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!atividadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        atividadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
