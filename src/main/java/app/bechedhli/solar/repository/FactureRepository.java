package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByClient(String client);
    List<Facture> findByStatus(String status);
}