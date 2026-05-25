package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.StegDossier;
import app.bechedhli.solar.repository.StegDossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steg")
public class StegController {

    private final StegDossierRepository repository;

    @Autowired
    public StegController(StegDossierRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<StegDossier> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public StegDossier getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("STEG dossier not found: " + id));
    }

    @PostMapping
    public StegDossier create(@RequestBody StegDossier dossier) {
        return repository.save(dossier);
    }

    @PutMapping("/{id}")
    public StegDossier update(@PathVariable Long id, @RequestBody StegDossier dossier) {
        return repository.findById(id)
                .map(existing -> {
                    if (dossier.getStatus() != null) existing.setStatus(dossier.getStatus());
                    if (dossier.getNotes() != null) existing.setNotes(dossier.getNotes());
                    if (dossier.getDocsJson() != null) existing.setDocsJson(dossier.getDocsJson());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("STEG dossier not found: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}