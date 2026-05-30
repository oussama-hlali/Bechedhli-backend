package app.bechedhli.solar.graphql;

import app.bechedhli.solar.entity.Intervention;
import app.bechedhli.solar.entity.Piece;
import app.bechedhli.solar.entity.User;
import app.bechedhli.solar.entity.StatutIntervention;
import app.bechedhli.solar.repository.InterventionRepository;
import app.bechedhli.solar.repository.PieceRepository;
import app.bechedhli.solar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private InterventionRepository interventionRepository;

    // === User queries ===
    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public User userByUsername(@Argument String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @MutationMapping
    public User createUser(@Argument String nom, @Argument String prenom,
                           @Argument String username, @Argument String password,
                           @Argument String email, @Argument String telephone,
                           @Argument String role) {
        User user = User.builder()
                .nom(nom)
                .prenom(prenom)
                .username(username)
                .password(password)
                .email(email)
                .telephone(telephone)
                .role(role != null ? role : "EMPLOYE")
                .actif(true)
                .build();
        return userRepository.save(user);
    }

    // === Piece queries ===
    @QueryMapping
    public List<Piece> allPieces() {
        return pieceRepository.findAll();
    }

    @QueryMapping
    public Piece pieceById(@Argument Long id) {
        return pieceRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Piece createPiece(@Argument String nom, @Argument String description,
                             @Argument Integer quantite, @Argument Double prixAchat,
                             @Argument Double prixVente, @Argument Integer stockMin,
                             @Argument String reference, @Argument String categorie) {
        Piece piece = Piece.builder()
                .nom(nom)
                .description(description)
                .quantite(quantite != null ? quantite : 0)
                .prixAchat(prixAchat != null ? java.math.BigDecimal.valueOf(prixAchat) : null)
                .prixVente(prixVente != null ? java.math.BigDecimal.valueOf(prixVente) : null)
                .stockMin(stockMin != null ? stockMin : 10)
                .reference(reference)
                .categorie(categorie)
                .build();
        return pieceRepository.save(piece);
    }

    // === Intervention queries ===
    @QueryMapping
    public List<Intervention> allInterventions() {
        return interventionRepository.findAll();
    }

    @QueryMapping
    public Intervention interventionById(@Argument Long id) {
        return interventionRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Intervention createIntervention(@Argument Long clientId, @Argument Long technicienId,
                                            @Argument String description, @Argument String adresse,
                                            @Argument Double prix, @Argument String statut) {
        User client = userRepository.findById(clientId).orElse(null);
        User technicien = technicienId != null ? userRepository.findById(technicienId).orElse(null) : null;
        Intervention intervention = Intervention.builder()
                .client(client)
                .technician(technicien)
                .description(description)
                .adresse(adresse)
                .prix(prix)
                .statut(statut != null ? StatutIntervention.valueOf(statut) : StatutIntervention.EN_ATTENTE)
                .build();
        return interventionRepository.save(intervention);
    }
}