package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.StockItem;
import com.bechedhli.solar.exception.ResourceNotFoundException;
import com.bechedhli.solar.repository.StockItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockItemService {

    private final StockItemRepository repository;

    public List<StockItem> findAll() {
        return repository.findAll();
    }

    public StockItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockItem not found with id: " + id));
    }

    public StockItem create(StockItem item) {
        return repository.save(item);
    }

    public StockItem update(Long id, StockItem updated) {
        StockItem existing = findById(id);
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setQty(updated.getQty());
        existing.setMinQty(updated.getMinQty());
        existing.setPrice(updated.getPrice());
        existing.setSupplier(updated.getSupplier());
        existing.setLocation(updated.getLocation());
        return repository.save(existing);
    }

    public boolean delete(Long id) {
        findById(id);
        repository.deleteById(id);
        return true;
    }
}
