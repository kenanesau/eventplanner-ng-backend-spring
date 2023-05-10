package com.psd.eventplanner.entity;

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
    private Long id;
    private String name;
    private Date startTime;
    private Date endTime;

    @OneToOne
    private Customer customer;

    @ManyToMany
    private List<Place> places;

}
