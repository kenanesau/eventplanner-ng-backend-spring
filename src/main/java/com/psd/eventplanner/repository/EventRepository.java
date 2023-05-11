package com.psd.eventplanner.repository;

import com.psd.eventplanner.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByPlaces_Id(Long PlacesId);
}
