package com.eventticket.eventservice.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TicketType {
    
    @EqualsAndHashCode.Include
    private UUID ticketTypeId;
    
    private UUID eventId;
    private String typeName;
    private String description;
    private BigDecimal price;
    private String currency;
    private Integer quantityAvailable;
    private Integer quantitySold;
    private Integer maxPerPurchase;
    private Boolean isActive;
    private LocalDateTime saleStartDate;
    private LocalDateTime saleEndDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public boolean isAvailableForSale() {
        if (!isActive) return false;
        
        LocalDateTime now = LocalDateTime.now();
        
        if (saleStartDate != null && now.isBefore(saleStartDate)) return false;
        if (saleEndDate != null && now.isAfter(saleEndDate)) return false;
        
        return getAvailableQuantity() > 0;
    }
    
    public int getAvailableQuantity() {
        return quantityAvailable - quantitySold;
    }
    
    public boolean canPurchase(int quantity) {
        return isAvailableForSale() && 
               quantity <= getAvailableQuantity() && 
               quantity <= maxPerPurchase;
    }
    
    public void sellTickets(int quantity) {
        if (!canPurchase(quantity)) {
            throw new IllegalStateException("Cannot sell " + quantity + " tickets");
        }
        this.quantitySold += quantity;
    }
    
    public void refundTickets(int quantity) {
        if (quantity > quantitySold) {
            throw new IllegalStateException("Cannot refund more tickets than sold");
        }
        this.quantitySold -= quantity;
    }
} 