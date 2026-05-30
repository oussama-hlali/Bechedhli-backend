package com.bechedhli.solar.repository;

import com.bechedhli.solar.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
