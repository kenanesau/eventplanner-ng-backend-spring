package com.psd.eventplanner.WebMvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psd.eventplanner.controller.user.EventController;
import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.entity.Place;
import com.psd.eventplanner.repository.EventRepository;
import com.psd.eventplanner.repository.PlaceRepository;
import com.psd.eventplanner.service.CollisionDetectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {com.psd.eventplanner.controller.admin.EventController.class, com.psd.eventplanner.controller.user.EventController.class})
public class UserEventControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    PlaceRepository placeRepository;


    @Value("classpath:testdata/events.json")
    private Resource eventsResource;

    @Value("classpath:testdata/places.json")
    private Resource placesResource;


    @Test
    public void shouldGetAllEvents(ApplicationContext context) throws Exception {
        JsonParser parser = objectMapper.createParser(placesResource.getFile());
        List<Place> places = objectMapper.readValue(parser, new TypeReference<List<Place>>(){});

        parser = objectMapper.createParser(eventsResource.getFile());
        List<Event> events = objectMapper.readValue(parser, new TypeReference<List<Event>>(){});

        context.getApplicationName();

        given(eventRepository.findAll())
                .willReturn(events);

        mockMvc.perform(get("/user/events"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @TestConfiguration
    static class Config {
        @Bean
        CollisionDetectionService collisionDetectionService(EventRepository eventRepository) {
            return new CollisionDetectionService(eventRepository);
        }
    }

}
