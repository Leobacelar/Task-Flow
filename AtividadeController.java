package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*") 
public class AtividadeController {

    @Autowired
    private AtividadeRepository repository;

    @GetMapping
    public List<Atividade> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Atividade criar(@RequestBody Atividade atividade) {
        return repository.save(atividade);
    }

    @PutMapping("/{id}")
    public Atividade atualizar(@PathVariable Long id, @RequestBody Atividade atividadeAtualizada) {
        return repository.findById(id).map(atividade -> {
            atividade.setDescricao(atividadeAtualizada.getDescricao());
            atividade.setStatus(atividadeAtualizada.getStatus());
            return repository.save(atividade);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}