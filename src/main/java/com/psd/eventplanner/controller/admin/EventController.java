package com.psd.eventplanner.controller.admin;

import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.repository.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("adminEventController")
@RequestMapping("admin/events")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/")
    public List<Event> getAll() { return this.eventRepository.findAll(); }

    @GetMapping("/ByPlace/{placeId}")
    public List<Event> getAllByPlace(@PathVariable Long placeId) {
        return this.eventRepository.findByPlaces_Id(placeId);
    }
}
