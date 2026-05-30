package com.bechedhli.solar.repository;

import com.bechedhli.solar.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
}
