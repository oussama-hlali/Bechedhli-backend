package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "factures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroFacture;
    private Long clientId;
    private Long dossierId;
    private String numBL;
    private LocalDate date;
    private LocalDate dateFacture;
    private LocalDate echeance;
    private String client;
    private Double montantHT;
    private Double montantTTC;

    @Builder.Default
    private String statut = "NON_PAYEE";

    private String description;

    @Builder.Default
    private String status = "NON_PAYEE";

    private Double tva;
    private Double remise;
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String paymentsJson;
}