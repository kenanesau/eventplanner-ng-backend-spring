package com.psd.eventplanner.repository;

import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByPlaces_Id(Long PlacesId);

    List<Event> findByPlacesInAndEndTimeBetween(List<Place> places, Date startTime, Date endTime);

    List<Event> findByPlacesInAndStartTimeBetween(List<Place> places, Date startTime, Date endTime);
}
