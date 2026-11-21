package com.training.inventory;
import org.springframework.stereotype.*;import org.springframework.transaction.annotation.*;
@Service public class StockService{
 private final StockRepository r;public StockService(StockRepository r){this.r=r;}
 @Transactional public StockItem create(StockRequest x){if(r.findBySkuAndWarehouse(x.sku(),x.warehouse()).isPresent())throw new IllegalStateException("SKU already registered in warehouse");return r.save(new StockItem(x.sku(),x.warehouse(),x.openingStock(),x.reorderLevel()));}
 @Transactional(readOnly=true) public StockItem get(long id){return r.findById(id).orElseThrow(()->new IllegalArgumentException("Stock item not found"));}
 @Transactional public StockItem reserve(long id,int q){var x=get(id);x.reserve(q);return x;}
 @Transactional public StockItem release(long id,int q){var x=get(id);x.release(q);return x;}
 @Transactional public StockItem adjust(long id,int d){var x=get(id);x.adjust(d);return x;}
}
