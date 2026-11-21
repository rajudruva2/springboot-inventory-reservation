package com.training.inventory;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.*;
public interface StockRepository extends JpaRepository<StockItem,Long>{Optional<StockItem> findBySkuAndWarehouse(String sku,String warehouse);}
