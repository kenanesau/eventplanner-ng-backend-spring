package com.psd.eventplanner.repository;

import com.psd.eventplanner.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    public abstract Place findPlaceById(Long id);
}
