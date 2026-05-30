package com.bechedhli.solar.config;

import com.bechedhli.solar.entity.*;
import com.bechedhli.solar.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final StockItemRepository stockItemRepository;
    private final ClientRepository clientRepository;
    private final DeliveryNoteRepository deliveryNoteRepository;
    private final StegDossierRepository stegDossierRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void run(String... args) {
        if (employeeRepository.count() > 0) return;

        employeeRepository.saveAll(List.of(
            createEmp("Ahmed Benali", "Technicien Senior", "Installation", "active", "+216 555 123 456", "a.benali@bechedhli.tn", 95000, "2022-03-15"),
            createEmp("Fatima Zohra Mebarki", "Directrice Commerciale", "Direction", "active", "+216 555 234 567", "f.mebarki@bechedhli.tn", 180000, "2020-01-10"),
            createEmp("Karim Bouzid", "Chef de Projet", "Projets", "active", "+216 555 345 678", "k.bouzid@bechedhli.tn", 130000, "2021-06-22"),
            createEmp("Nadia Cherif", "Responsable RH", "Ressources Humaines", "active", "+216 555 456 789", "n.cherif@bechedhli.tn", 120000, "2021-02-08"),
            createEmp("Youcef Hamidi", "Technicien", "Installation", "active", "+216 555 567 890", "y.hamidi@bechedhli.tn", 75000, "2023-01-20"),
            createEmp("Sara Amrani", "Comptable", "Finance", "active", "+216 555 678 901", "s.amrani@bechedhli.tn", 85000, "2022-09-05"),
            createEmp("Mourad Taleb", "Magasinier", "Stock", "active", "+216 555 789 012", "m.taleb@bechedhli.tn", 65000, "2023-04-12"),
            createEmp("Amina Ziani", "Technicienne", "Maintenance", "leave", "+216 555 890 123", "a.ziani@bechedhli.tn", 80000, "2022-11-30"),
            createEmp("Rachid Khelil", "Livreur", "Logistique", "active", "+216 555 901 234", "r.khelil@bechedhli.tn", 60000, "2023-07-01"),
            createEmp("Leila Boudiaf", "Assistante Direction", "Direction", "active", "+216 555 012 345", "l.boudiaf@bechedhli.tn", 70000, "2022-05-18"),
            createEmp("Omar Fekhar", "Technicien", "Installation", "inactive", "+216 555 111 222", "o.fekhar@bechedhli.tn", 75000, "2021-08-14"),
            createEmp("Djamila Saadi", "Chargée de Clientèle", "Commercial", "active", "+216 555 333 444", "d.saadi@bechedhli.tn", 90000, "2023-02-28")
        ));

        stockItemRepository.saveAll(List.of(
            createStock("Panneau Solaire 400W Monocristallin", "Panneaux", 148, 20, 28500, "Jinko Solar", "Entrepôt A"),
            createStock("Panneau Solaire 550W Monocristallin", "Panneaux", 64, 15, 38500, "Longi Solar", "Entrepôt A"),
            createStock("Onduleur Hybride 5kW", "Onduleurs", 7, 10, 95000, "Growatt", "Entrepôt B"),
            createStock("Onduleur Hybride 8kW", "Onduleurs", 12, 8, 135000, "Huawei", "Entrepôt B"),
            createStock("Onduleur On-Grid 10kW", "Onduleurs", 5, 5, 110000, "SMA", "Entrepôt B"),
            createStock("Batterie Lithium 5.12kWh", "Batteries", 18, 10, 185000, "Pylontech", "Entrepôt C"),
            createStock("Batterie Lithium 10.24kWh", "Batteries", 4, 5, 340000, "Pylontech", "Entrepôt C"),
            createStock("Câble Solaire 4mm\u00B2 (100m)", "Câblage", 35, 10, 12500, "Loccab", "Entrepôt A"),
            createStock("Câble Solaire 6mm\u00B2 (100m)", "Câblage", 22, 8, 18000, "Loccab", "Entrepôt A"),
            createStock("Structure de Montage Toiture", "Accessoires", 30, 15, 8500, "Aluminium du Sud", "Entrepôt D"),
            createStock("Connecteur MC4 (paire)", "Accessoires", 3, 50, 350, "Stäubli", "Entrepôt D"),
            createStock("Disjoncteur DC 1000V 32A", "Accessoires", 45, 20, 2800, "Schneider", "Entrepôt D"),
            createStock("Parafoudre DC 1000V", "Accessoires", 2, 10, 6500, "Citel", "Entrepôt D"),
            createStock("Compteur d'\u00C9nergie Triphas\u00E9", "Accessoires", 15, 5, 12000, "Schneider", "Entrepôt B"),
            createStock("Panneau Solaire 330W Polycristallin", "Panneaux", 0, 10, 19500, "Canadian Solar", "Entrepôt A")
        ));

        Client c1 = clientRepository.save(createClient("Mohamed Benmerzoug", "123456789012", "+216 555 201 001", "Cit\u00E9 1000 Logements, Ouargla", "2024-01-15", List.of(
            createOrder(1124000, true, "2024-02-28", "2024-01-20", List.of("Panneau Solaire 400W Monocristallin x12", "Onduleur Hybride 5kW x1", "Batterie Lithium 5.12kWh x2"))
        )));
        Client c2 = clientRepository.save(createClient("A\u00EFcha Djelloul", "234567890123", "+216 555 302 002", "Rue des Fr\u00E8res Bouadou, Gharda\u00EFa", "2024-02-10", List.of(
            createOrder(2146000, false, null, "2024-02-15", List.of("Panneau Solaire 550W Monocristallin x16", "Onduleur Hybride 8kW x1", "Batterie Lithium 10.24kWh x2", "Parafoudre DC 1000V x3"))
        )));
        clientRepository.saveAll(List.of(c1, c2));

        deliveryNoteRepository.save(DeliveryNote.builder()
                .id("BL00040").clientId(c1.getId()).type("Mono").date("2024-05-15")
                .status("delivered").invoiced(true).puissance("3,240 kwc").refSteg("20081 289 0")
                .transporteurName("Aymen Bechedli").transporteurMatricule("213 Tunis 6198")
                .items(new ArrayList<>(List.of(
                    createBLItem(1, "Panneau Solaire 400W Monocristallin", "JKM-400M", "panneau", 12),
                    createBLItem(2, "Onduleur Hybride 5kW", "GW-5K-AL", "onduleur", 1),
                    createBLItem(3, "Batterie Lithium 5.12kWh", "US2000C", "accessoires", 2)
                )))
                .build());

        stegDossierRepository.save(StegDossier.builder()
                .clientId(c1.getId()).refSteg("20081 289 0").puissance("3,240 kwc")
                .status("approved").createdAt("2024-03-01").submittedDate("2024-03-10")
                .approvedDate("2024-04-15").notes("Dossier complet")
                .docs(new java.util.HashMap<>(Map.ofEntries(
                    Map.entry("demande", true), Map.entry("attestation_install", true),
                    Map.entry("rapport_essai", true), Map.entry("schema_uni", true),
                    Map.entry("plan_implant", true), Map.entry("photos_avant_apres", true),
                    Map.entry("conformite", true), Map.entry("fiche_technique", true),
                    Map.entry("cert_onduleur", true), Map.entry("mise_en_service", true),
                    Map.entry("contrat_signe", true), Map.entry("facture_finale", true)
                )))
                .build());

        Invoice inv = Invoice.builder()
                .id("FAC-2024-001").clientId(c1.getId()).dossierId(null).numBL("BL00040")
                .date("2024-03-01").echeance("2024-04-01").status("paid").tva(19.0).remise(5.0)
                .notes("Installation termin\u00E9e et valid\u00E9e STEG le 28/02/2024. Remise de 5% accord\u00E9e pour paiement anticip\u00E9.")
                .build();
        inv.setItems(new ArrayList<>(List.of(
            createInvItem("Installation panneaux solaires 3,240 kwc", 1, "forfait", 850000.0),
            createInvItem("Fourniture et pose structure aluminium toiture", 1, "forfait", 120000.0),
            createInvItem("Dossier de raccordement STEG complet", 1, "forfait", 45000.0),
            createInvItem("D\u00E9placement et main d'\u0153uvre technicien", 3, "jour", 15000.0)
        )));
        inv.setPayments(new ArrayList<>(List.of(
            createPayment("2024-03-15", 600000.0, "Virement bancaire", "VIR-2024-0891"),
            createPayment("2024-03-28", 453690.0, "Esp\u00E8ces", "")
        )));
        invoiceRepository.save(inv);
    }

    private Employee createEmp(String name, String role, String dept, String status, String phone, String email, double salary, String joinDate) {
        return Employee.builder().name(name).role(role).dept(dept).status(status).phone(phone).email(email).salary(salary).joinDate(joinDate).build();
    }

    private StockItem createStock(String name, String category, int qty, int minQty, double price, String supplier, String location) {
        return StockItem.builder().name(name).category(category).qty(qty).minQty(minQty).price(price).supplier(supplier).location(location).build();
    }

    private Client createClient(String name, String cin, String phone, String address, String createdAt, List<ClientOrder> orders) {
        Client c = Client.builder().name(name).cin(cin).phone(phone).address(address).createdAt(createdAt).build();
        orders.forEach(o -> o.setClient(c));
        c.setOrders(orders);
        return c;
    }

    private ClientOrder createOrder(double total, boolean received, String receivedDate, String orderDate, List<String> items) {
        return ClientOrder.builder().total(total).received(received).receivedDate(receivedDate).orderDate(orderDate).items(items).build();
    }

    private DeliveryNoteItem createBLItem(int n, String des, String marque, String cat, int qty) {
        return DeliveryNoteItem.builder().n(n).des(des).marque(marque).cat(cat).qty(qty).build();
    }

    private InvoiceItem createInvItem(String desc, int qty, String unit, double prix) {
        return InvoiceItem.builder().desc(desc).qty(qty).unit(unit).prix(prix).build();
    }

    private Payment createPayment(String date, double montant, String mode, String ref) {
        return Payment.builder().date(date).montant(montant).mode(mode).ref(ref).build();
    }
}
