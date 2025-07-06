package com.eventticket.eventservice.domain.port;

import com.eventticket.eventservice.domain.model.TicketType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketTypeRepository {
    
    TicketType save(TicketType ticketType);
    
    Optional<TicketType> findById(UUID ticketTypeId);
    
    List<TicketType> findByEventId(UUID eventId);
    
    List<TicketType> findActiveByEventId(UUID eventId);
    
    void deleteById(UUID ticketTypeId);
    
    boolean existsById(UUID ticketTypeId);
} 