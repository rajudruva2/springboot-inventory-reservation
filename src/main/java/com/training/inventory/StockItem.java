package com.training.inventory;
import jakarta.persistence.*;import java.time.*;
@Entity @Table(name="stock",uniqueConstraints=@UniqueConstraint(columnNames={"sku","warehouse"}))
public class StockItem{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String sku,warehouse;
 public int onHand,reserved,reorderLevel;@Version public Long version;public Instant updatedAt=Instant.now();
 protected StockItem(){} public StockItem(String s,String w,int q,int r){sku=s;warehouse=w;onHand=q;reorderLevel=r;}
 public int available(){return onHand-reserved;}
 public void reserve(int q){if(q<1||available()<q)throw new IllegalStateException("Insufficient available stock");reserved+=q;updatedAt=Instant.now();}
 public void release(int q){if(q<1||reserved<q)throw new IllegalStateException("Invalid release quantity");reserved-=q;updatedAt=Instant.now();}
 public void adjust(int d){if(onHand+d<reserved)throw new IllegalStateException("Cannot reduce below reserved stock");onHand+=d;updatedAt=Instant.now();}
}
