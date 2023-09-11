package com.Nostrade.VivereApp.SavedExpectancy;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Nostrade.VivereApp.savedExpectancy.controllers.SavedExpectancyController;
import com.Nostrade.VivereApp.savedExpectancy.models.SavedExpectancy;
import com.Nostrade.VivereApp.savedExpectancy.services.SavedExpectancyService;

@WebMvcTest(SavedExpectancyController.class)
public class SavedExpectancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavedExpectancyService savedExpectancyService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllSavedExpectancy() throws Exception {
        List<SavedExpectancy> savedExpectancies = Arrays.asList(
                new SavedExpectancy(), new SavedExpectancy());

        when(savedExpectancyService.getAllSavedExpectancy())
                .thenReturn(savedExpectancies);

        mockMvc.perform(get("/vivere/saved-expectancies"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(savedExpectancies)));
    }

    @Test
    void getSavedExpectancyById() throws Exception {
        SavedExpectancy savedExpectancy = new SavedExpectancy();
        savedExpectancy.setId(1L);

        when(savedExpectancyService.getSavedExpectancyById(1L))
                .thenReturn(savedExpectancy);

        mockMvc.perform(get("/vivere/saved-expectancies/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(savedExpectancy)));
    }

    @Test
    void createSavedExpectancy() throws Exception {
        SavedExpectancy newExpectancy = new SavedExpectancy();
        SavedExpectancy createdExpectancy = new SavedExpectancy();
        createdExpectancy.setId(1L);

        when(savedExpectancyService.createSavedExpectancy(newExpectancy))
                .thenReturn(createdExpectancy);

        mockMvc.perform(post("/vivere/saved-expectancies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newExpectancy)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(createdExpectancy)));
    }

    @Test
    void updateSavedExpectancy() throws Exception {
        SavedExpectancy updatedExpectancy = new SavedExpectancy();
        updatedExpectancy.setId(1L);

        when(savedExpectancyService.updateSavedExpectancy(updatedExpectancy))
                .thenReturn(updatedExpectancy);

        mockMvc.perform(put("/vivere/saved-expectancies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedExpectancy)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updatedExpectancy)));
    }

}