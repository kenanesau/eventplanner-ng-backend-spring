package com.psd.eventplanner.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.entity.Views;
import com.psd.eventplanner.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userEventController")
@RequestMapping("user/events")
public class EventController {
    private final com.psd.eventplanner.controller.admin.EventController adminEventCtrl;
    private final PlaceRepository placeRepository;

    @Autowired
    public EventController(com.psd.eventplanner.controller.admin.EventController adminEventCtlr, PlaceRepository placeRepository) {
        this.adminEventCtrl = adminEventCtlr;
        this.placeRepository = placeRepository;
    }

    @GetMapping("/")
    @JsonView(Views.UserEventView.class)
    public List<Event> getAll() { return this.adminEventCtrl.getAll(); }

    @GetMapping("/{id}")
    @JsonView(Views.UserEventView.class)
    public Event getSingleEvent(@PathVariable long id) { return this.adminEventCtrl.getSingleEvent(id); }

    @GetMapping("/ByPlace/{placeId}")
    @JsonView(Views.UserEventView.class)
    public List<Event> getAllByPlace(@PathVariable Long placeId) {
        return this.adminEventCtrl.getAllByPlace(placeId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody Event event) {
        adminEventCtrl.createEvent(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editEvent(@PathVariable long id, @RequestBody Event event) {
        adminEventCtrl.editEvent(id, event);
    }
}
