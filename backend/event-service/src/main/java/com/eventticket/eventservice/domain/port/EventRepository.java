package com.eventticket.eventservice.domain.port;

import com.eventticket.eventservice.domain.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    
    Event save(Event event);
    
    Optional<Event> findById(UUID eventId);
    
    List<Event> findAll();
    
    List<Event> findByOrganizerId(UUID organizerId);
    
    List<Event> findByCategoryId(UUID categoryId);
    
    List<Event> findByStatus(Event.EventStatus status);
    
    List<Event> findFeaturedEvents();
    
    List<Event> findUpcomingEvents();
    
    List<Event> findByVenueId(UUID venueId);
    
    void deleteById(UUID eventId);
    
    boolean existsById(UUID eventId);
} 