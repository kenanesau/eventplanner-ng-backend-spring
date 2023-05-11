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
    @Id
    Long id;

    @JsonView(Views.UserEventView.class)
    private String name;

    private Boolean locked;

    private String lockedComment;

    @JsonView(Views.UserEventView.class)
    private byte[] image;
}
