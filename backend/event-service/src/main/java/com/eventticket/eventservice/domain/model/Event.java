package com.eventticket.eventservice.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
    
    @EqualsAndHashCode.Include
    private UUID eventId;
    
    private String eventTitle;
    private String description;
    private UUID categoryId;
    private UUID venueId;
    private UUID organizerId;
    private LocalDateTime eventDate;
    private LocalDateTime eventEndDate;
    private LocalDateTime doorsOpenTime;
    private String eventImageUrl;
    private String eventBannerUrl;
    private Integer maxTickets;
    private Integer minAgeRequirement;
    private Boolean isFeatured;
    private EventStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @Builder.Default
    private List<TicketType> ticketTypes = new ArrayList<>();
    
    // Business logic methods
    public boolean isPublished() {
        return EventStatus.PUBLISHED.equals(status);
    }
    
    public boolean isDraft() {
        return EventStatus.DRAFT.equals(status);
    }
    
    public boolean isPendingApproval() {
        return EventStatus.PENDING_APPROVAL.equals(status);
    }
    
    public boolean isCancelled() {
        return EventStatus.CANCELLED.equals(status);
    }
    
    public boolean isEnded() {
        return eventEndDate != null && eventEndDate.isBefore(LocalDateTime.now());
    }
    
    public boolean isUpcoming() {
        return eventDate.isAfter(LocalDateTime.now());
    }
    
    public boolean isOngoing() {
        LocalDateTime now = LocalDateTime.now();
        return eventDate.isBefore(now) && 
               (eventEndDate == null || eventEndDate.isAfter(now));
    }
    
    public int getTotalTicketsSold() {
        return ticketTypes.stream()
                .mapToInt(TicketType::getQuantitySold)
                .sum();
    }
    
    public int getTotalTicketsAvailable() {
        return ticketTypes.stream()
                .mapToInt(TicketType::getQuantityAvailable)
                .sum();
    }
    
    public boolean hasAvailableTickets() {
        return getTotalTicketsAvailable() > 0;
    }
    
    public BigDecimal getLowestTicketPrice() {
        return ticketTypes.stream()
                .filter(TicketType::getIsActive)
                .map(TicketType::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }
    
    public BigDecimal getHighestTicketPrice() {
        return ticketTypes.stream()
                .filter(TicketType::getIsActive)
                .map(TicketType::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }
    
    public void publish() {
        this.status = EventStatus.PUBLISHED;
    }
    
    public void approve() {
        this.status = EventStatus.PUBLISHED;
    }
    
    public void reject() {
        this.status = EventStatus.DRAFT;
    }
    
    public void cancel() {
        this.status = EventStatus.CANCELLED;
    }
    
    public void markAsFeatured() {
        this.isFeatured = true;
    }
    
    public void removeFromFeatured() {
        this.isFeatured = false;
    }
    
    public enum EventStatus {
        DRAFT, PENDING_APPROVAL, PUBLISHED, CANCELLED, ENDED
    }
} 