package com.psd.eventplanner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Customer {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
}
