package com.psd.eventplanner.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity @Table(name="event_table")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Event {
    @Id
    @JsonView(Views.UserEventView.class)
    private Long id;
    @JsonView(Views.UserEventView.class)
    private String name;
    @JsonView(Views.UserEventView.class)
    private Date startTime;
    @JsonView(Views.UserEventView.class)
    private Date endTime;

    @OneToOne
    private Customer customer;

    @ManyToMany
    @JsonView(Views.UserEventView.class)
    private List<Place> places;

}
