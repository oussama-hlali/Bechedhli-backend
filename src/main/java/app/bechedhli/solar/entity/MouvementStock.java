package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Piece piece;

    @ManyToOne
    private User utilisateur;

    private Integer quantite;

    @Enumerated(EnumType.STRING)
    private TypeMouvement type;

    private LocalDateTime date;
    private String motif;

    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
}