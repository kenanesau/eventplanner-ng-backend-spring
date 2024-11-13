package com.psd.eventplanner.WebMvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psd.eventplanner.controller.PlaceController;
import com.psd.eventplanner.entity.Place;
import com.psd.eventplanner.repository.PlaceRepository;
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

@WebMvcTest(PlaceController.class)
@TestPropertySource(properties = "logging.level.org.springframework=DEBUG")
public class PlaceControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PlaceRepository placeRepository;

    @Value("classpath:testdata/places.json")
    private Resource placesResource;

    @Autowired
    ApplicationContext context;

    @Test
    public void shouldGetAllPlaces() throws Exception {
        JsonParser parser = objectMapper.createParser(placesResource.getFile());
        List<Place> places = objectMapper.readValue(parser, new TypeReference<List<Place>>(){});

        context.getApplicationName();

        when(placeRepository.findAll())
                .thenReturn(places);

        mockMvc.perform(get("/places/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
