package com.psd.eventplanner.controller.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.entity.Views;
import com.psd.eventplanner.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminEventController")
@RequestMapping("admin/events")
public class EventController {
    public static final String ROOT = "/";

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping(ROOT)
    public List<Event> getAll() { return this.eventRepository.findAll(); }

    @GetMapping("/{id}")
    @JsonView(Views.UserEventView.class)
    public Event getSingleEvent(@PathVariable long id) {
        return this.eventRepository
                .findById(id)
                .orElseThrow( () -> new RuntimeException("Cannot find Event with id: " + id));
    }

    @GetMapping("/ByPlace/{placeId}")
    public List<Event> getAllByPlace(@PathVariable Long placeId) {
        return this.eventRepository.findByPlaces_Id(placeId);
    }

    @PostMapping(ROOT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody Event event) {

        eventRepository.save(event);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editEvent(@PathVariable long id, @RequestBody Event event) {
        Event ev = eventRepository
                .findById(id)
                .orElseThrow( () -> new RuntimeException("No Event with that id") );
        event.setId(id);
        event.setPlaces(ev.getPlaces());
        eventRepository.save(event);
    }


}
