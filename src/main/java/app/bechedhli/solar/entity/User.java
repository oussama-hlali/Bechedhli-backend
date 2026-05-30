package app.bechedhli.solar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nom;

    private String prenom;

    private String username;

    private String password;

    @Column(nullable = false)
    private String email;

    private String telephone;
    private String phone;

    @Builder.Default
    private boolean actif = true;

    @Builder.Default
    private String role = "EMPLOYE";

    private String dept;

    @Builder.Default
    private String status = "active";

    private Double salary;

    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @PrePersist
    protected void onCreate() {
        if (nom == null) nom = name;
        if (prenom == null) prenom = "";
        dateCreation = LocalDate.now();
    }

    public enum Role {
        ADMIN, TECHNICIEN, CLIENT, EMPLOYE
    }
}