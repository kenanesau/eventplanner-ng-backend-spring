package com.psd.eventplanner.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.entity.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userEventController")
@RequestMapping("user/events")
public class EventController {
    private final com.psd.eventplanner.controller.admin.EventController adminEventCtrl;

    @Autowired
    public EventController(com.psd.eventplanner.controller.admin.EventController adminEventCtlr) {
        this.adminEventCtrl = adminEventCtlr;
    }

    @GetMapping("/")
    @JsonView(Views.UserEventView.class)
    public List<Event> getAll() { return this.adminEventCtrl.getAll(); }

    @GetMapping("/ByPlace/{placeId}")
    @JsonView(Views.UserEventView.class)
    public List<Event> getAllByPlace(@PathVariable Long placeId) {
        return this.adminEventCtrl.getAllByPlace(placeId);
    }
}
