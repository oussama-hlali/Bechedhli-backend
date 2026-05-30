package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.*;
import com.bechedhli.solar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final StockItemRepository stockItemRepository;
    private final ClientRepository clientRepository;
    private final DeliveryNoteRepository deliveryNoteRepository;
    private final StegDossierRepository stegDossierRepository;
    private final InvoiceRepository invoiceRepository;

    public DashboardMetrics computeMetrics() {
        List<Employee> employees = employeeRepository.findAll();
        List<StockItem> stockItems = stockItemRepository.findAll();
        List<DeliveryNote> deliveryNotes = deliveryNoteRepository.findAll();
        List<StegDossier> stegDossiers = stegDossierRepository.findAll();
        List<Invoice> invoices = invoiceRepository.findAll();

        int activeEmp = (int) employees.stream().filter(e -> "active".equals(e.getStatus())).count();
        double totalStockValue = stockItems.stream().mapToDouble(i -> (i.getQty() != null ? i.getQty() : 0) * (i.getPrice() != null ? i.getPrice() : 0)).sum();
        int lowStockCount = (int) stockItems.stream().filter(i -> {
            int qty = i.getQty() != null ? i.getQty() : 0;
            int min = i.getMinQty() != null ? i.getMinQty() : 0;
            return qty == 0 || qty <= min;
        }).count();
        int deliveredBL = (int) deliveryNotes.stream().filter(b -> "delivered".equals(b.getStatus())).count();
        int stegApproved = (int) stegDossiers.stream().filter(d -> "approved".equals(d.getStatus())).count();

        double totalBilled = 0;
        double totalCollected = 0;
        for (Invoice f : invoices) {
            double ht = f.getItems().stream().mapToDouble(i -> (i.getQty() != null ? i.getQty() : 0) * (i.getPrix() != null ? i.getPrix() : 0)).sum();
            double remiseVal = f.getRemise() != null ? f.getRemise() : 0;
            double htNet = ht - ht * (remiseVal / 100);
            double tvaVal = f.getTva() != null ? f.getTva() : 0;
            double ttc = htNet + htNet * (tvaVal / 100);
            totalBilled += ttc;
            totalCollected += f.getPayments().stream().mapToDouble(p -> p.getMontant() != null ? p.getMontant() : 0).sum();
        }

        return DashboardMetrics.builder()
                .totalEmployees(employees.size())
                .activeEmployees(activeEmp)
                .totalStockValue(totalStockValue)
                .lowStockCount(lowStockCount)
                .totalDeliveryNotes(deliveryNotes.size())
                .deliveredDeliveryNotes(deliveredBL)
                .stegTotal(stegDossiers.size())
                .stegApproved(stegApproved)
                .totalBilled(totalBilled)
                .totalCollected(totalCollected)
                .totalOutstanding(Math.max(0, totalBilled - totalCollected))
                .build();
    }
}
