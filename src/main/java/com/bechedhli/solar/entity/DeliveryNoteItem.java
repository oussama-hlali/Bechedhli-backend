package com.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_note_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DeliveryNoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer n;
    private String des;
    private String marque;
    private String cat;
    private Integer qty;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_note_id")
    private DeliveryNote deliveryNote;
}
