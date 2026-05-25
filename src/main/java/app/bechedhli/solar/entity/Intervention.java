package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private User client;

    @ManyToOne
    @JoinColumn(name = "id_technicien")
    private User technician;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutIntervention statut = StatutIntervention.EN_ATTENTE;

    private LocalDate datePrevue;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @Column(length = 1000)
    private String description;

    private String adresse;
    private Double prix;

    @PrePersist
    protected void onCreate() {
        if (statut == null) statut = StatutIntervention.EN_ATTENTE;
    }
}