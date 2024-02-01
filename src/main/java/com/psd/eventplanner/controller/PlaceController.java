package com.psd.eventplanner.controller;

import com.psd.eventplanner.entity.Place;
import com.psd.eventplanner.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("places")
public class PlaceController {

    PlaceRepository placeRepo;

    @Autowired
    public PlaceController(PlaceRepository placeRepo) {
        this.placeRepo = placeRepo;
    }

    @GetMapping(value = "/")
    public List<Place> listAllPlaces() {
        return placeRepo.findAll();
    }
}
