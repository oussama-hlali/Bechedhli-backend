package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.BonLivraison;
import app.bechedhli.solar.repository.BonLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bls")
public class BlController {

    private final BonLivraisonRepository repository;

    @Autowired
    public BlController(BonLivraisonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BonLivraison> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BonLivraison getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("BL not found: " + id));
    }

    @PostMapping
    public BonLivraison create(@RequestBody BonLivraison bl) {
        return repository.save(bl);
    }

    @PutMapping("/{id}")
    public BonLivraison update(@PathVariable Long id, @RequestBody BonLivraison bl) {
        return repository.findById(id)
                .map(existing -> {
                    if (bl.getStatus() != null) existing.setStatus(bl.getStatus());
                    if (bl.getInvoiced() != null) existing.setInvoiced(bl.getInvoiced());
                    if (bl.getItemsJson() != null) existing.setItemsJson(bl.getItemsJson());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("BL not found: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}