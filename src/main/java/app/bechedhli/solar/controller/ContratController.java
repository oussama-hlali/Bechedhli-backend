package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.Contrat;
import app.bechedhli.solar.exceptions.ContratNotFoundException;
import app.bechedhli.solar.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    private final ContratRepository contratRepository;

    @Autowired
    public ContratController(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @GetMapping("/allContrats")
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @GetMapping("/contrats/{id}")
    public Contrat getContratById(@PathVariable Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ContratNotFoundException(id));
    }

    @PostMapping("/contrat")
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @PutMapping("/updateContrat")
    public Contrat updateContrat(@RequestBody Contrat contrat) {
        Contrat existing = contratRepository.findById(contrat.getId())
                .orElseThrow(() -> new ContratNotFoundException(contrat.getId()));
        existing.setTypeContrat(contrat.getTypeContrat());
        existing.setDateDebut(contrat.getDateDebut());
        existing.setDateFin(contrat.getDateFin());
        existing.setSalaireBrut(contrat.getSalaireBrut());
        existing.setDescription(contrat.getDescription());
        return contratRepository.save(existing);
    }

    @DeleteMapping("/contrats/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratRepository.deleteById(id);
    }
}