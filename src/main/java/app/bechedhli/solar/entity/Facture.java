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
    private LocalDate dateFacture;
    private String client;
    private Double montantHT;
    private Double montantTTC;

    @Builder.Default
    private String statut = "NON_PAYEE";

    private String description;

    @Builder.Default
    private String status = "NON_PAYEE";

    @Column(columnDefinition = "TEXT")
    private String paymentsJson;
}