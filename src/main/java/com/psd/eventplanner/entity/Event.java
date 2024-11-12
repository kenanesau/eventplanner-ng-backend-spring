package com.psd.eventplanner.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Table(name="event_table")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserEventView.class)
    private Long id;
    @JsonView(Views.UserEventView.class)
    private String name;
    @JsonView(Views.UserEventView.class)
    private LocalDateTime startTime;
    @JsonView(Views.UserEventView.class)
    private LocalDateTime endTime;

    @OneToOne
    private Customer customer;

    @ManyToMany
    @JsonView(Views.UserEventView.class)
    private List<Place> places;

}
