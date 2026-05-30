package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.*;
import com.bechedhli.solar.exception.BusinessException;
import com.bechedhli.solar.exception.ResourceNotFoundException;
import com.bechedhli.solar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessOperationService {

    private final DeliveryNoteRepository deliveryNoteRepository;
    private final StockItemRepository stockItemRepository;
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public DeliveryNote deliverBL(String blId) {
        DeliveryNote bl = deliveryNoteRepository.findById(blId)
                .orElseThrow(() -> new ResourceNotFoundException("DeliveryNote not found: " + blId));

        if ("delivered".equals(bl.getStatus())) {
            throw new BusinessException("BL " + blId + " is already delivered");
        }

        bl.setStatus("delivered");

        if (bl.getItems() != null) {
            for (DeliveryNoteItem item : bl.getItems()) {
                String desc = item.getDes() != null ? item.getDes().toLowerCase() : "";
                int qty = item.getQty() != null ? item.getQty() : 1;

                List<StockItem> matchedStock = stockItemRepository.findAll().stream()
                        .filter(si -> si.getName() != null && si.getName().toLowerCase().contains(desc))
                        .toList();

                for (StockItem si : matchedStock) {
                    int decrement = Math.min(qty, si.getQty() != null ? si.getQty() : 0);
                    if (decrement > 0) {
                        si.setQty(si.getQty() - decrement);
                        stockItemRepository.save(si);
                        qty -= decrement;
                    }
                }
            }
        }

        return deliveryNoteRepository.save(bl);
    }

    @Transactional
    public Invoice generateInvoiceFromBL(String blId) {
        DeliveryNote bl = deliveryNoteRepository.findById(blId)
                .orElseThrow(() -> new ResourceNotFoundException("DeliveryNote not found: " + blId));

        if (bl.getInvoiced() != null && bl.getInvoiced()) {
            throw new BusinessException("Invoice already generated for BL " + blId);
        }

        String year = String.valueOf(LocalDate.now().getYear());
        Optional<Invoice> lastInvoice = invoiceRepository.findAll().stream()
                .filter(i -> i.getId() != null && i.getId().startsWith("FAC-" + year))
                .max((a, b) -> a.getId().compareTo(b.getId()));

        int nextNum = 1;
        if (lastInvoice.isPresent()) {
            String lastId = lastInvoice.get().getId();
            String numStr = lastId.substring(lastId.lastIndexOf('-') + 1);
            try {
                nextNum = Integer.parseInt(numStr) + 1;
            } catch (NumberFormatException ignored) {}
        }

        String invoiceId = "FAC-" + year + "-" + String.format("%03d", nextNum);
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String echeance = LocalDate.now().plusDays(30).format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        if (bl.getItems() != null) {
            for (DeliveryNoteItem dit : bl.getItems()) {
                InvoiceItem ii = InvoiceItem.builder()
                        .desc(dit.getDes())
                        .qty(1)
                        .unit("forfait")
                        .prix(0.0)
                        .build();
                invoiceItems.add(ii);
            }
        }

        if (invoiceItems.isEmpty()) {
            invoiceItems.add(InvoiceItem.builder()
                    .desc("Installation panneaux solaires " + (bl.getPuissance() != null ? bl.getPuissance() : ""))
                    .qty(1).unit("forfait").prix(0.0).build());
        }

        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .clientId(bl.getClientId())
                .numBL(bl.getId())
                .date(today)
                .echeance(echeance)
                .status("draft")
                .items(invoiceItems)
                .tva(19.0)
                .remise(0.0)
                .payments(new ArrayList<>())
                .notes("Generated from " + bl.getId() + " on " + today)
                .build();

        invoice.getItems().forEach(item -> item.setInvoice(invoice));

        Invoice saved = invoiceRepository.save(invoice);

        bl.setInvoiced(true);
        deliveryNoteRepository.save(bl);

        return saved;
    }
}
