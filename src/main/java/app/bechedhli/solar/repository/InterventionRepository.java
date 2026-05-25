package app.bechedhli.solar.repository;

import app.bechedhli.solar.entity.Intervention;
import app.bechedhli.solar.entity.StatutIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByClientId(Long clientId);
    List<Intervention> findByTechnicianId(Long technicianId);
    List<Intervention> findByStatut(StatutIntervention statut);
}