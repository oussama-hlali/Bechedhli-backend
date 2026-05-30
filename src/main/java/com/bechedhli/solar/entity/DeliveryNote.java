package com.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_notes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DeliveryNote {

    @Id
    private String id;

    private Long clientId;
    private String type;
    private String date;
    private String status;
    private Boolean invoiced;
    private String puissance;
    private String refSteg;
    private String transporteurName;
    private String transporteurMatricule;

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<DeliveryNoteItem> items = new ArrayList<>();
}
