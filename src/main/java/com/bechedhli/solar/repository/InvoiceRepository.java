package com.bechedhli.solar.repository;

import com.bechedhli.solar.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
