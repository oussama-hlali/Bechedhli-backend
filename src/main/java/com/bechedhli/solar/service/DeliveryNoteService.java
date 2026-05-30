package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.DeliveryNote;
import com.bechedhli.solar.entity.DeliveryNoteItem;
import com.bechedhli.solar.exception.ResourceNotFoundException;
import com.bechedhli.solar.repository.DeliveryNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryNoteService {

    private final DeliveryNoteRepository repository;

    public List<DeliveryNote> findAll() {
        return repository.findAll();
    }

    public DeliveryNote findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DeliveryNote not found with id: " + id));
    }

    @Transactional
    public DeliveryNote create(DeliveryNote note) {
        if (note.getItems() != null) {
            note.getItems().forEach(item -> item.setDeliveryNote(note));
        }
        return repository.save(note);
    }

    @Transactional
    public DeliveryNote update(String id, DeliveryNote updated) {
        DeliveryNote existing = findById(id);
        existing.setClientId(updated.getClientId());
        existing.setType(updated.getType());
        existing.setDate(updated.getDate());
        existing.setStatus(updated.getStatus());
        existing.setInvoiced(updated.getInvoiced());
        existing.setPuissance(updated.getPuissance());
        existing.setRefSteg(updated.getRefSteg());
        existing.setTransporteurName(updated.getTransporteurName());
        existing.setTransporteurMatricule(updated.getTransporteurMatricule());

        if (updated.getItems() != null) {
            existing.getItems().clear();
            updated.getItems().forEach(item -> {
                item.setDeliveryNote(existing);
                existing.getItems().add(item);
            });
        }
        return repository.save(existing);
    }

    @Transactional
    public boolean delete(String id) {
        findById(id);
        repository.deleteById(id);
        return true;
    }
}
