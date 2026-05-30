package com.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "steg_dossiers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StegDossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private String refSteg;
    private String puissance;
    private String status;
    private String createdAt;
    private String submittedDate;
    private String approvedDate;
    private String notes;

    @ElementCollection
    @CollectionTable(name = "steg_dossier_docs", joinColumns = @JoinColumn(name = "dossier_id"))
    @MapKeyColumn(name = "doc_key")
    @Column(name = "doc_value")
    @Builder.Default
    private Map<String, Boolean> docs = new HashMap<>();
}
