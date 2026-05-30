package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.StegDossier;
import com.bechedhli.solar.exception.ResourceNotFoundException;
import com.bechedhli.solar.repository.StegDossierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StegDossierService {

    private final StegDossierRepository repository;

    public List<StegDossier> findAll() {
        return repository.findAll();
    }

    public StegDossier findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StegDossier not found with id: " + id));
    }

    public StegDossier create(StegDossier dossier) {
        return repository.save(dossier);
    }

    public StegDossier update(Long id, StegDossier updated) {
        StegDossier existing = findById(id);
        existing.setClientId(updated.getClientId());
        existing.setRefSteg(updated.getRefSteg());
        existing.setPuissance(updated.getPuissance());
        existing.setStatus(updated.getStatus());
        existing.setCreatedAt(updated.getCreatedAt());
        existing.setSubmittedDate(updated.getSubmittedDate());
        existing.setApprovedDate(updated.getApprovedDate());
        existing.setNotes(updated.getNotes());
        existing.setDocs(updated.getDocs());
        return repository.save(existing);
    }

    public boolean delete(Long id) {
        findById(id);
        repository.deleteById(id);
        return true;
    }
}
