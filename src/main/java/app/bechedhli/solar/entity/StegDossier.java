package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "steg_dossiers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StegDossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroDossier;
    private Long clientId;
    private String refSteg;
    private String client;
    private String adresse;
    private String puissance;
    private Double montant;
    private LocalDate dateDemande;
    private LocalDate dateTraitement;
    private LocalDate createdAt;
    private LocalDate submittedDate;
    private LocalDate approvedDate;

    @Builder.Default
    private String statut = "EN_ATTENTE";

    @Builder.Default
    private String status = "EN_ATTENTE";

    private String description;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String docsJson;
}