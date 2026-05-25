package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.BonLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonLivraisonRepository extends JpaRepository<BonLivraison, Long> {
    List<BonLivraison> findByClient(String client);
    List<BonLivraison> findByStatus(String status);
}