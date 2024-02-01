package com.psd.eventplanner.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Place {
    @JsonView(Views.UserEventView.class)
    @Id
    Long id;

    @JsonView(Views.UserEventView.class)
    private String name;

    @JsonView(Views.UserEventView.class)
    private Boolean locked;

    @JsonView(Views.UserEventView.class)
    private String lockedComment;
}
