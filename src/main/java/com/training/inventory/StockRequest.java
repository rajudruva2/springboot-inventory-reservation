package com.training.inventory;
import jakarta.validation.constraints.*;
public record StockRequest(@NotBlank String sku,@NotBlank String warehouse,@Min(0) int openingStock,@Min(0) int reorderLevel){}
