package com.eventticket.eventservice.application.service;

import com.eventticket.eventservice.application.dto.CreateEventRequest;
import com.eventticket.eventservice.application.dto.EventResponse;
import com.eventticket.eventservice.application.dto.UpdateEventRequest;
import com.eventticket.eventservice.domain.model.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    
    EventResponse createEvent(CreateEventRequest request, UUID organizerId);
    
    EventResponse getEventById(UUID eventId);
    
    List<EventResponse> getAllEvents(int page, int size);
    
    List<EventResponse> getEventsByOrganizer(UUID organizerId, int page, int size);
    
    List<EventResponse> getEventsByCategory(UUID categoryId, int page, int size);
    
    List<EventResponse> getFeaturedEvents();
    
    List<EventResponse> getUpcomingEvents();
    
    EventResponse updateEvent(UUID eventId, UpdateEventRequest request, UUID organizerId);
    
    void deleteEvent(UUID eventId, UUID organizerId);
    
    void approveEvent(UUID eventId);
    
    void rejectEvent(UUID eventId);
    
    void publishEvent(UUID eventId, UUID organizerId);
    
    void cancelEvent(UUID eventId, UUID organizerId);
    
    void markEventAsFeatured(UUID eventId);
    
    void removeEventFromFeatured(UUID eventId);
} 