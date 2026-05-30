package com.bechedhli.solar.repository;

import com.bechedhli.solar.entity.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, String> {
}
