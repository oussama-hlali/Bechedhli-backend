package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.Facture;
import app.bechedhli.solar.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureRepository repository;

    @Autowired
    public FactureController(FactureRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Facture> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Facture getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found: " + id));
    }

    @PostMapping
    public Facture create(@RequestBody Facture facture) {
        return repository.save(facture);
    }

    @PutMapping("/{id}")
    public Facture update(@PathVariable Long id, @RequestBody Facture facture) {
        return repository.findById(id)
                .map(existing -> {
                    if (facture.getStatus() != null) existing.setStatus(facture.getStatus());
                    if (facture.getPaymentsJson() != null) existing.setPaymentsJson(facture.getPaymentsJson());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Facture not found: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}