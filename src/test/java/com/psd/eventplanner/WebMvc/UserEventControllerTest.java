package com.psd.eventplanner.WebMvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psd.eventplanner.controller.AdminEventController;
import com.psd.eventplanner.controller.UserEventController;
import com.psd.eventplanner.entity.Event;
import com.psd.eventplanner.repository.EventRepository;
import com.psd.eventplanner.repository.PlaceRepository;
import com.psd.eventplanner.service.CollisionDetectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserEventController.class, AdminEventController.class})
@TestPropertySource(properties = "logging.level.org.springframework=DEBUG")
public class UserEventControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    PlaceRepository placeRepository;

    @MockBean
    CollisionDetectionService collisionDetectionService;

    @Value("classpath:testdata/events.json")
    private Resource eventsResource;

    @Autowired
    ApplicationContext context;

    @Test
    public void shouldGetAllEvents() throws Exception {
        JsonParser parser =  objectMapper.createParser(eventsResource.getFile());
        List<Event> events = objectMapper.readValue(parser, new TypeReference<List<Event>>(){});

        context.getApplicationName();
        when(eventRepository.findAll())
                .thenReturn(events);

        mockMvc.perform(get("/user/events/"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
