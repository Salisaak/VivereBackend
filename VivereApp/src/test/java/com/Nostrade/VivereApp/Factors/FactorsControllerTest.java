package com.Nostrade.VivereApp.Factors;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import com.Nostrade.VivereApp.factors.controllers.FactorsController;
import com.Nostrade.VivereApp.factors.models.Factors;
import com.Nostrade.VivereApp.factors.services.FactorsService;

@WebMvcTest(FactorsController.class)
public class FactorsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FactorsService factorsService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getAllFactors() throws Exception {
        List<Factors> factorsList = Arrays.asList(new Factors(), new Factors());
        when(factorsService.getAllFactors()).thenReturn(factorsList);

        mockMvc.perform(get("/vivere/factors"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(factorsList)));
    }

    @Test
    void getFactorById() throws Exception {
        Factors factor = new Factors();
        factor.setId(1L);
        when(factorsService.getFactorById(1L)).thenReturn(factor);

        mockMvc.perform(get("/vivere/factors/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(factor)));
    }

    @Test
    void createFactor() throws Exception {
        Factors newFactor = new Factors();
        Factors createdFactor = new Factors();
        createdFactor.setId(1L);
        when(factorsService.createFactor(newFactor)).thenReturn(createdFactor);

        mockMvc.perform(post("/vivere/factors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newFactor)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(createdFactor)));
    }

    @Test
    void updateFactor() throws Exception {
        Factors updatedFactor = new Factors();
        updatedFactor.setId(1L);
        when(factorsService.updateFactor(updatedFactor)).thenReturn(updatedFactor);

        mockMvc.perform(put("/vivere/factors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedFactor)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updatedFactor)));
    }

    @Test
    void deleteFactor() throws Exception {
        mockMvc.perform(delete("/vivere/factors/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Factor deleted successfully"));
    }

}