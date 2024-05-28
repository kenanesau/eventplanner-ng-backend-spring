package com.psd.eventplanner.service;

import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CollisionDetectionService {

    private final EventRepository eventRepository;

    public CollisionDetectionService(EventRepository eventRepository) {
        this.eventRepository  = eventRepository;
    }

    public List<Event> detectCollissions(Event event) {
        List<Event> eventsEnding = eventRepository.findByPlacesInAndEndTimeBetween(event.getPlaces(), event.getStartTime(), event.getEndTime());
        List<Event> eventsStarting = eventRepository.findByPlacesInAndStartTimeBetween(event.getPlaces(), event.getStartTime(), event.getEndTime());

        return Stream.concat(eventsStarting.stream(), eventsEnding.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
