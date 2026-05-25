package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.StegDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StegDossierRepository extends JpaRepository<StegDossier, Long> {
    List<StegDossier> findByClient(String client);
    List<StegDossier> findByStatus(String status);
}