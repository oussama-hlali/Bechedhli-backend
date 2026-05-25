package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "bon_livraison")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroBL;
    private LocalDate dateBL;
    private Double montantHT;
    private Double montantTTC;
    private String client;
    private String description;

    @Builder.Default
    private String status = "EN_ATTENTE";

    private Boolean invoiced;

    @Column(columnDefinition = "TEXT")
    private String itemsJson;
}