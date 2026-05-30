package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.Invoice;
import com.bechedhli.solar.entity.InvoiceItem;
import com.bechedhli.solar.entity.Payment;
import com.bechedhli.solar.exception.ResourceNotFoundException;
import com.bechedhli.solar.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;

    public List<Invoice> findAll() {
        return repository.findAll();
    }

    public Invoice findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }

    @Transactional
    public Invoice create(Invoice invoice) {
        if (invoice.getItems() != null) {
            invoice.getItems().forEach(item -> item.setInvoice(invoice));
        }
        if (invoice.getPayments() != null) {
            invoice.getPayments().forEach(payment -> payment.setInvoice(invoice));
        }
        return repository.save(invoice);
    }

    @Transactional
    public Invoice update(String id, Invoice updated) {
        Invoice existing = findById(id);
        existing.setClientId(updated.getClientId());
        existing.setDossierId(updated.getDossierId());
        existing.setNumBL(updated.getNumBL());
        existing.setDate(updated.getDate());
        existing.setEcheance(updated.getEcheance());
        existing.setStatus(updated.getStatus());
        existing.setTva(updated.getTva());
        existing.setRemise(updated.getRemise());
        existing.setNotes(updated.getNotes());

        if (updated.getItems() != null) {
            existing.getItems().clear();
            updated.getItems().forEach(item -> {
                item.setInvoice(existing);
                existing.getItems().add(item);
            });
        }
        if (updated.getPayments() != null) {
            existing.getPayments().clear();
            updated.getPayments().forEach(payment -> {
                payment.setInvoice(existing);
                existing.getPayments().add(payment);
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
