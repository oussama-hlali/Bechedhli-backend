package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pieces")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String description;

    @Builder.Default
    private Integer quantite = 0;

    @Column(name = "prix_achat")
    private BigDecimal prixAchat;

    @Column(name = "prix_vente")
    private BigDecimal prixVente;

    @Column(name = "stock_min")
    @Builder.Default
    private Integer stockMin = 10;

    private String reference;

    private String categorie;

    private String name;
    private String category;
    @Builder.Default
    private Integer qty = 0;
    @Builder.Default
    private Integer minQty = 10;
    private Double price;
    private String supplier;
    private String location;
}