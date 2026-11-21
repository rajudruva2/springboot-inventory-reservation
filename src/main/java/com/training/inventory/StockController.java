package com.training.inventory;
import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;
@RestController @RequestMapping("/api/v1/stock") public class StockController{
 private final StockService s;public StockController(StockService s){this.s=s;}
 @PostMapping public ResponseEntity<StockItem> create(@Valid @RequestBody StockRequest r){return ResponseEntity.status(201).body(s.create(r));}
 @GetMapping("/{id}") public StockItem get(@PathVariable long id){return s.get(id);}
 @PostMapping("/{id}/reserve") public StockItem reserve(@PathVariable long id,@Valid @RequestBody QuantityRequest q){return s.reserve(id,q.quantity());}
 @PostMapping("/{id}/release") public StockItem release(@PathVariable long id,@Valid @RequestBody QuantityRequest q){return s.release(id,q.quantity());}
 @PatchMapping("/{id}/adjust") public StockItem adjust(@PathVariable long id,@RequestParam int delta){return s.adjust(id,delta);}
}
