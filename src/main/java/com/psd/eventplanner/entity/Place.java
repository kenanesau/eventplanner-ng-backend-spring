package com.psd.eventplanner.entity;

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

    private String name;

    private Boolean locked;

    private String lockedComment;

    private byte[] image;
}
