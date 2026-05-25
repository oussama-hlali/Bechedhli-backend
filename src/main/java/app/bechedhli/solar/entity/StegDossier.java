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
    private String client;
    private String adresse;
    private Double montant;
    private LocalDate dateDemande;
    private LocalDate dateTraitement;

    @Builder.Default
    private String statut = "EN_ATTENTE";

    private String description;

    @Builder.Default
    private String status = "EN_ATTENTE";

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String docsJson;
}